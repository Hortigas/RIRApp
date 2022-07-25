package vitor_ag.rir_app.features.feature_rir.presentation.add_edit_rir.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vitor_ag.rir_app.R
import vitor_ag.rir_app.features.feature_rir.domain.model.Photo

@Composable
fun Gallery(
    photoGallery: SnapshotStateList<Photo>,
    OnRemovePhoto: (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement =
        if (photoGallery.size == 0)
            Arrangement.Center
        else Arrangement.spacedBy(8.dp)
    ) {
        if (photoGallery.size == 0)
            Image(
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.ic_empty_gallery),
                contentDescription = "empty gallery"
            )
        else
            photoGallery.forEachIndexed { index, item ->
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5))
                        .fillMaxHeight()
                        .width(160.dp),
                ) {
                    AsyncImage(
                        model = item.uri,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(bottom = 8.dp, end = 8.dp)
                            .fillMaxSize(0.3f),
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        onClick = { OnRemovePhoto(index) }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            contentDescription = "remove photo"
                        )
                    }
                }
            }
    }
}