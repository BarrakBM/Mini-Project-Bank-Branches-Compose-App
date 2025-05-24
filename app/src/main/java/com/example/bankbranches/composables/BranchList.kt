package com.example.bankbranches.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankbranches.R
import com.example.bankbranches.repository.BranchRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchListScreen(
    repository: BranchRepository,
    navController: NavController,
    // CHANGE: Added parameters to receive shared state from parent NavHost
    favoriteBranchId: Int? = null, // Shared favorite state from parent
    onFavoriteButton: (Int) -> Unit = {} // Callback to notify parent when favorite is toggled
) {

    // CHANGE: Removed local state - now using shared state from parent
    // This ensures favorite state is consistent between list and detail screens
    // var favoriteBranchId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // top navigation bar
        TopAppBar(
            title = { Text(stringResource(R.string.bank_branches))
            }
        )

        // scrollable list to scroll over the branches
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            // each Item will become clickable card
            items(repository.getBranches()){ branch ->
                BranchCard(
                    // branch will give us the original branch from the repo with isFavorite = false
                    // only one branch can be favorite, so we check branch.id == favoriteBranchId
                    branch = branch.copy(isFavorite = branch.id == favoriteBranchId),
                    // when user select anywhere beside the favorite button go to the selected branch
                    onClick = { selectBranch ->
                        navController.navigate("branch_detail/${selectBranch.id}")
                    },
                    onFavoriteClick = {favoriteBranch ->
                        // CHANGE: Instead of updating local state, use callback to notify parent
                        // This ensures the state change is reflected in both list and detail screens

                        // calling parent to notify of change in state
                        onFavoriteButton(favoriteBranch.id)
                    }
                )
            }
        }
    }
}
