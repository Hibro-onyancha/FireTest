package com.example.firetest.presentation_layer.screens.add_notes

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firetest.utils.getBitmapFromUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddNotesModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(AddNotesState())
    val uiState = _uiState.asStateFlow()

    var note by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set

    fun updateNote(input: String) {
        note = input
    }

    fun updateTitle(input: String) {
        title = input
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun updateBitmap(uri: Uri?, context: Context) {
        if (uri != null) {
            val bitmap = getBitmapFromUri(context, imageUri = uri)
            _uiState.update {
                it.copy(
                    currentBitmap = bitmap
                )
            }
        }
    }
}