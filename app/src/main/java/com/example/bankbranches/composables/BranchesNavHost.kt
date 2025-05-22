package com.example.bankbranches.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankbranches.repository.BranchRepository


@Composable
fun BranchNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController // pass the
) {
    val repository = BranchRepository()

    NavHost(
        navController = navController,
        startDestination = "branch_list"  // First screen to show
    ){
        // branch list route
        composable("branch_list") {
            BranchListScreen(
                repository = repository,
                navController = navController
            )
        }

        // branch details route
        composable("branch_detail/{branchId}") { it ->
            // extract branch Id from the navigation argument
            val branchId = it.arguments?.getString("branchId")?.toIntOrNull()

            // find the branch using Id
            val branch = branchId?.let { repository.getBranchById(it) }

            // show details screen if branch exsited
            branch?.let {
                BranchDetailsScreen(
                    branch = it,
                    navController = navController
                )
            }
        }
    }

}