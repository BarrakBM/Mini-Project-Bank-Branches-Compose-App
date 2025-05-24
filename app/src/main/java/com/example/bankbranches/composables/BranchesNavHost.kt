package com.example.bankbranches.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankbranches.repository.BranchRepository
import com.example.bankbranches.composables.BranchDetailsScreen

@Composable
fun BranchNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController // pass the
) {
    val repository = BranchRepository()

    // this will ensure both branchList and Branch Details use the same state
    var favoriteBranchId by remember { mutableStateOf<Int?>(null) }

    NavHost(
        navController = navController,
        startDestination = "branch_list"  // First screen to show
    ){
        // branch list route
        composable("branch_list") {
            BranchListScreen(
                repository = repository,
                navController = navController,
                // pass the shared state
                favoriteBranchId = favoriteBranchId,
                onFavoriteButton = { branchId ->
                    // updated the shared state when it's changed
                    favoriteBranchId = if (favoriteBranchId == branchId) {
                        null // Remove favorite
                    } else {
                        branchId // Set as favorite
                    }
                }
            )
        }

        // branch details route
        composable("branch_detail/{branchId}") { it ->
            // extract branch Id from the navigation argument
            val branchId = it.arguments?.getString("branchId")?.toIntOrNull()

            // find the branch using Id
            val branch = branchId?.let { repository.getBranchById(it) }

            // show details screen if branch existed
            branch?.let { originalBranch ->
                BranchDetailsScreen(
                    // CHANGE: Pass branch with updated favorite status from shared state
                    branch = originalBranch.copy(isFavorite = favoriteBranchId == originalBranch.id),
                    navController = navController,
                    // pass a call back to notify a change in shared state
                    onFavoriteButton = { changedBranchId ->
                        favoriteBranchId = if (favoriteBranchId == changedBranchId) {
                            null // Remove favorite
                        } else {
                            changedBranchId // Set as favorite
                        }
                    }
                )
            }
        }
    }
}