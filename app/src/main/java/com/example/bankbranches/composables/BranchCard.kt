package com.example.bankbranches.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bankbranches.R
import com.example.bankbranches.data.Branch

@Composable
fun BranchCard(
    branch: Branch,
    modifier: Modifier = Modifier,
    onClick: (Branch) -> Unit

)   {

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick(branch) } // upon clicking call the function and pass the branch
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            val image = branch.imageUri ?: R.drawable.nbk_default
            Image(
                painter = painterResource(id = image),
                contentDescription = "Branch Image",
                modifier = modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            // display branch info
            Column {
                Text( //name of the branch
                    text = branch.name
                )
                Text( // branch type
                    text = branch.type.value
                )
                Text( // hours
                    text = branch.hours
                )

            }
        }
    }
}