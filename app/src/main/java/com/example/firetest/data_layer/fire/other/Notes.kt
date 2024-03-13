package com.example.firetest.data_layer.fire.other

import android.graphics.Bitmap

data class Notes(
    val userName: String?,
    val userImage: Bitmap?,
    val note: String?,
    val date: String,
    val title: String?,
    val noteImage: Bitmap?
)
