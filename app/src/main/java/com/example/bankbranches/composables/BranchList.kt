package com.example.bankbranches.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankbranches.R
import com.example.bankbranches.data.Branch
import com.example.bankbranches.repository.BranchRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchListScreen(
    repository: BranchRepository,
    navController: NavController
) {

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
                    branch = branch
                ) {
                    // navigate using id
                    navController.navigate("branch_detail/${branch.id}")
                }
            }
        }
    }
}