package com.example.bankbranches.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankbranches.R
import com.example.bankbranches.data.Branch

@Composable
fun BranchDetailsScreen(
    branch: Branch,
    modifier: Modifier = Modifier,
    navController: NavController,
    onFavoriteButton: (Int) -> Unit = {} // to notify parent if it's set to favorite
) {

    // to provide access to system Uri handler (for example google maps)
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding() // handle the status bar padding :/
            .padding(16.dp)
    ) {

        // branch image
        val imageRes = branch.imageUri ?: R.drawable.nbk_default
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Branch Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // branch details
        Text(
            text = branch.name,
            modifier = modifier.padding(bottom = 8.dp)
        )

        BranchInfoRow(title = stringResource(R.string.type), value = branch.type.value)
        BranchInfoRow(title = stringResource(R.string.address), value = branch.address)
        BranchInfoRow(title = stringResource(R.string.phone), value = branch.phone)
        BranchInfoRow(title = stringResource(R.string.hours), value = branch.hours)

        // favorite button with special styling based on favorite status
        Button(
            onClick = {

                // here we will notify parent (the branch list compose that it's set to favorite)
                // his will updated the shared status on branchlist
                onFavoriteButton(branch.id)
            },
            enabled = !branch.isFavorite,  // will use isFav direclty from parent
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    // Star icon for favorite, heart outline for non-favorite
                    imageVector = if (branch.isFavorite) Icons.Filled.Star else Icons.Filled.FavoriteBorder,
                    contentDescription = if (branch.isFavorite) "Favorite Branch" else "Add to Favorite",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp)
                )
            }
        }

        // google map location button
        Button(
            onClick = { uriHandler.openUri(branch.location) },
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // google map image
                Image(
                    painter = painterResource(id = R.drawable.google_maps_logo),
                    contentDescription = stringResource(R.string.google_maps),
                    modifier = modifier
                        .size(25.dp)
                        .padding(end = 8.dp)
                )
                Text(stringResource(R.string.view_on_google_maps))
            }
        }
    }
}
@Composable
fun BranchInfoRow(title: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}