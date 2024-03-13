package com.example.firetest.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.IOException

//this will be responsible for converting a uri into a bitmap
@RequiresApi(Build.VERSION_CODES.P)
fun getBitmapFromUri(context: Context, imageUri: Uri): Bitmap? {
    return try {
        val source = ImageDecoder.createSource(context.contentResolver, imageUri)
        ImageDecoder.decodeBitmap(source)
    } catch (e: IOException) {
        e.printStackTrace()
        null // Return null if any error occurs
    }
}

fun byteToBitmap(byteArrayString: String): Bitmap? {
    // Decode Base64 string to ByteArray
    val byteArray = Base64.decode(byteArrayString, Base64.DEFAULT)

    // Decode ByteArray to Bitmap
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

fun bitmapToBytes(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        60,
        outputStream
    ) // Adjust compression quality as needed
    val byteArray = outputStream.toByteArray()

    // Encode as Base64 string
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

