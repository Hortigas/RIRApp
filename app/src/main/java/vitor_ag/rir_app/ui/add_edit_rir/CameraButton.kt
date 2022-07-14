package vitor_ag.rir_app.ui.add_edit_rir

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import vitor_ag.rir_app.ComposeFileProvider

@Composable
fun CameraButton(
    onClick: (Uri) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onClick(imageUri!!)
            }
        }
    )


    Button(
        modifier = modifier,
        onClick = {
            imageUri = ComposeFileProvider.getImageUri(context)
            cameraLauncher.launch(imageUri)
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSecondary,
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = "ADICIONAR FOTO")
    }
}