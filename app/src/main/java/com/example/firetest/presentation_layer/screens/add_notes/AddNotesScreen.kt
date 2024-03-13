package com.example.firetest.presentation_layer.screens.add_notes

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firetest.R
import com.example.firetest.presentation_layer.contsants.CommonTextField
import com.example.firetest.presentation_layer.contsants.Header
import com.example.firetest.presentation_layer.contsants.RoundButton
import com.example.firetest.presentation_layer.theme.iconLarge
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.P)
@Destination
@Composable
fun AddNotesScreen(
    navigator: DestinationsNavigator,
    model: AddNotesModel = hiltViewModel()
) {
    val context = LocalContext.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> model.updateBitmap(uri, context) }
    )
    val uiState = model.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .weight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                isTrailingPresent = false,
                trailer = { /*TODO*/ },
                onLeadClick = { navigator.popBackStack() },
                text = "add notes",
                leadingIcon = R.drawable.cancel
            )
            /*add image*/
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)//call the activity to pick the image
                        )
                    }
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.currentBitmap == null) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(
                            iconLarge
                        )
                    )
                } else {
                    Image(
                        bitmap = uiState.currentBitmap.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            /*add title*/
            CommonTextField(
                hint = "title",
                value = model.title,
                onValueChange = { model.updateTitle(it) },
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.95f)
            )

        }
        /*add content*/
        CommonTextField(
            hint = "add note",
            value = model.note,
            onValueChange = { model.updateNote(it) },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.95f)
                .padding(bottom = 10.dp),
            singleLine = false,
            color = MaterialTheme.colorScheme.background
        )
        RoundButton(
            isPositive = true,
            text = "done",
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(0.1f)
        )
    }
}