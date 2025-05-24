package com.example.bankbranches.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bankbranches.R
import com.example.bankbranches.data.Branch

@Composable
fun BranchCard(
    branch: Branch,
    modifier: Modifier = Modifier,
    onClick: (Branch) -> Unit,
    onFavoriteClick: (Branch) -> Unit = {}
)   {

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick(branch) }, // upon clicking call the function and pass the branch
        // adding simple golden color for fav branch
        colors = if (branch.isFavorite) {
            cardColors(containerColor = Color(0xFFFFD700))
        } else {
            cardColors() // default color for regular branches
        }
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

            Column(
                modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // favorite button
                IconButton(
                    onClick = { onFavoriteClick(branch) }
                ) {
                    Icon(
                        imageVector = if (branch.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (branch.isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (branch.isFavorite) Color.Red else Color.Gray
                    )
                }
                // if it's the fav branch
                // add a star icon to indicate it's favorite branch
                if (branch.isFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Favorite Branch",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
