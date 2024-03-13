package com.example.firetest.presentation_layer.contsants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.firetest.R
import com.example.firetest.data_layer.fire.other.Notes
import com.example.firetest.presentation_layer.theme.extraLargeText
import com.example.firetest.presentation_layer.theme.iconLarge
import com.example.firetest.presentation_layer.theme.iconStandard
import com.example.firetest.presentation_layer.theme.largeShape
import com.example.firetest.presentation_layer.theme.largeText
import com.example.firetest.presentation_layer.theme.mediumLargeText
import com.example.firetest.presentation_layer.theme.mediumShape
import com.example.firetest.presentation_layer.theme.mediumText
import com.example.firetest.presentation_layer.theme.smallShape
import com.example.firetest.presentation_layer.theme.smallText
import com.example.firetest.presentation_layer.theme.standardHeight
import com.example.firetest.utils.NumberFormattingTransformation
import com.example.firetest.utils.shadow

@Composable/*basic text field for inserting data */
fun BasicEditTextComp(
    modifier: Modifier = Modifier,
    onDone: () -> Unit,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    isHelperTextEnabled: Boolean = false,
    helperText: String = "",
    spacer: Int = 5,
    isPassword: Boolean = false,
    isNumber: Boolean = false,
    hint: String,
    value: String,
    singleLine: Boolean = true,
    isMaxCharEnabled: Boolean = false,
    maxChar: Int = 50,
    leadingIcon: Int? = null,
    isPasswordVisible: Boolean = false,
    onPassWordClick: () -> Unit,
    isNumberToFormat: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxWidth(0.8f), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = { value ->
                if (isMaxCharEnabled) {
                    val updatedValue = value.take(maxChar) // Ensure maximum length is not exceeded
                    onValueChange(updatedValue)
                    if (isNumber) {
                        val filteredValue = updatedValue.filter { it.isDigit() }
                        if (filteredValue != value) {
                            // Non-numeric characters were entered, prevent onValueChange
                            return@TextField
                        }
                        onValueChange(value)
                    }
                } else if (isNumber) {
                    val filteredValue = value.filter { it.isDigit() }
                    if (filteredValue != value) {
                        // Non-numeric characters were entered, prevent onValueChange
                        return@TextField
                    }
                    onValueChange(value)
                } else {
                    onValueChange(value)
                }
            },
            singleLine = singleLine,
            placeholder = {
                Text(
                    text = hint,
                    color = if (!isError) MaterialTheme.colorScheme.onSurfaceVariant else Color.Red,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light,
                    fontSize = mediumLargeText,
                    maxLines = 1
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = stringResource(R.string.leading_icon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(iconStandard)
                    )
                }
            },
            trailingIcon = {
                // this will choose the right icon according to the current state of the text-field
                if (isPassword && isPasswordVisible) {
                    Icon(painter = painterResource(id = R.drawable.vissibillity_off),
                        contentDescription = stringResource(R.string.leading_icon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(iconStandard)
                            .clip(smallShape)
                            .clickable {
                                onPassWordClick()
                            })
                } else if (!isPasswordVisible && isPassword) {
                    Icon(painter = painterResource(id = R.drawable.vissible),
                        contentDescription = stringResource(R.string.leading_icon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(iconStandard)
                            .clip(smallShape)
                            .clickable {
                                onPassWordClick()
                            })
                } else if (isError) {
                    //this error icon should be shown only for 500ms then should disappear
                    Icon(
                        painter = painterResource(id = R.drawable.error_24),
                        contentDescription = stringResource(R.string.leading_icon),
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(iconStandard)
                    )
                }
            },
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                errorTextColor = MaterialTheme.colorScheme.error,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = smallShape,
            visualTransformation = if (!isPasswordVisible && isPassword) PasswordVisualTransformation() else if (isNumberToFormat && value != "") NumberFormattingTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onDone() })
        )
        AnimatedVisibility(isHelperTextEnabled) {
            Spacer(modifier = Modifier.height(10.dp))
            // this will be used to show some text not to be forgotten or some short error
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = helperText,
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
                    fontSize = smallText,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (isMaxCharEnabled) {
                    Text(
                        text = "${value.length}/${maxChar}",
                        color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
                        fontSize = smallText,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer.dp))
    }

}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    shape: Shape = smallShape,
    color: Color = MaterialTheme.colorScheme.inverseOnSurface
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = hint
            )
        },
        shape = shape,
        modifier = modifier.fillMaxWidth(0.7f),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = color,
            errorTextColor = MaterialTheme.colorScheme.error,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun CommonSearch(
    modifier: Modifier = Modifier,
    onSearch: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    spacer: Int = 5
) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = stringResource(id = R.string.search_user),
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(iconStandard)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onSearch() }),
            placeholder = {
                Text(text = hint, color = MaterialTheme.colorScheme.outline)
            },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                ),
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(0.97f)
        )
        Spacer(modifier = Modifier.height(spacer.dp))
    }
}


@Composable
fun Header(
    modifier: Modifier = Modifier,
    leadingIcon: Int? = null,
    text: String? = null,
    isTrailingPresent: Boolean,
    trailer: @Composable () -> Unit,
    onLeadClick: () -> Unit,
    badgeNumber: Int = 0
) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .padding(top = 5.dp),
    ) {
        if (text == null && !isTrailingPresent) {
            /*only the leading icon is present*/
            Row(
                modifier = modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.Start
            ) {
                leadingIcon?.let { painterResource(id = it) }
                    ?.let {
                        Box(
                            modifier = Modifier.size(iconLarge)
                        ) {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(R.string.back),
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .size(iconLarge)
                                    .padding(5.dp)
                                    .clip(smallShape)
                                    .align(Alignment.BottomStart)
                                    .clickable {
                                        onLeadClick()
                                    }
                            )
                            if (badgeNumber != 0) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(Color.Red)
                                        .clip(CircleShape)
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CommonText(text = badgeNumber.toString())
                                }
                            }

                        }
                    }
            }
        } else if (leadingIcon == null && !isTrailingPresent) {
            /*only the text is present*/
            Row(
                modifier = modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.Center
            ) {
                if (text != null) {
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = largeText,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        fontFamily = FontFamily.Cursive
                    )
                }
            }
        } else {
            /*when leading icon and text are present */
            Box(
                modifier = modifier.fillMaxWidth(0.95f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(
                        Alignment.CenterStart
                    )
                ) {
                    leadingIcon?.let { painterResource(id = it) }?.let {
                        Box(
                            modifier = Modifier.size(iconLarge)
                        ) {

                            Icon(
                                painter = it,
                                contentDescription = stringResource(R.string.back),
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .size(iconLarge)
                                    .padding(5.dp)
                                    .clip(smallShape)
                                    .align(Alignment.BottomStart)
                                    .clickable {
                                        onLeadClick()
                                    }
                            )
                            if (badgeNumber != 0) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CommonText(text = badgeNumber.toString())
                                }
                            }

                        }
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    if (text != null) {
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = largeText,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.width(150.dp),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
                /*lets now add the final item the trailing details*/
                if (isTrailingPresent) {
                    Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                        /*this trailer composable will hold additional icons{up-to 5}*/
                        /**important*//*don't forget to switch is trailing to true*/
                        trailer()
                    }
                } else {
                    Spacer(modifier = Modifier.size(30.dp))
                }
            }

        }
    }

}

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    textSize: Int = 1,
    textAlign: Int = 1,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isBold: Boolean = true,
    text: String,
    maxLines: Int = 1
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Light,
        fontSize = when (textSize) {
            1 -> smallText
            2 -> mediumText
            3 -> mediumLargeText
            4 -> largeText
            else -> extraLargeText
        },
        textAlign = when (textAlign) {
            1 -> TextAlign.Start
            2 -> TextAlign.Center
            else -> TextAlign.End
        }
    )
}

//lottie animation
@Composable
fun LottieAnimationComp(
    modifier: Modifier = Modifier,
    animation: Int
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))

    LottieAnimation(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    isPositive: Boolean,
    text: String,
    onClick: () -> Unit,
    spacer: Int = 5
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .shadow(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                borderRadius = 30.dp,
                blurRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 2.dp
            )
            .height(standardHeight)
            .width(220.dp)
            .clip(RoundedCornerShape(30.dp))
            .clickable {
                onClick()
            }
            .background(if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = if (isPositive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = mediumLargeText,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(spacer.dp))
    }
}

@Composable
fun NotesRowComp(
    notes: Notes,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 20.dp)
            .shadow(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                borderRadius = 3.dp,
                blurRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 2.dp
            )
            .fillMaxWidth(0.96f)
            .height(290.dp)

            .clip(mediumShape)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.eggimg),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.7f),
            contentScale = ContentScale.Crop
        )
        NotesBodyR(
            notes = notes, modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

@Composable
fun NotesColumnComp(
    notes: Notes,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .shadow(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                borderRadius = 3.dp,
                blurRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 2.dp
            )
            .height(290.dp)

            .clip(mediumShape)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.eggimg),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.7f),
            contentScale = ContentScale.Crop
        )
        NotesBodyC(
            notes = notes, modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

@Composable
private fun NotesBodyR(
    modifier: Modifier = Modifier,
    notes: Notes
) {
    Column(
        modifier = modifier.padding(start = 15.dp, end = 10.dp, top = 15.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CommonText(
                text = "note",
                textSize = 2,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            notes.title?.let {
                CommonText(
                    text = it,
                    textSize = 5,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            notes.note?.let {
                CommonText(
                    text = it,
                    textSize = 1,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.67f),
                    maxLines = 6
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (notes.userImage != null) {
                    Image(
                        bitmap = notes.userImage.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(30.dp)
                            ),

                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(30.dp)
                            ),

                        tint = MaterialTheme.colorScheme.outline
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
                Column(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = notes.userName ?: "unknown",
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        fontSize = 10.sp
                    )
                    Text(
                        text = notes.date,
                        fontSize = 5.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

            }
            Box(
                modifier = Modifier
                    .shadow(
                        color = MaterialTheme.colorScheme.outline,
                        borderRadius = 20.dp,
                        blurRadius = 10.dp,
                        offsetX = 0.dp,
                        offsetY = 0.dp,
                        spread = 2.dp
                    )
                    .size(30.dp)
                    .clip(largeShape)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_arrow_right_24),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
private fun NotesBodyC(
    modifier: Modifier = Modifier,
    notes: Notes
) {
    Column(
        modifier = modifier.padding(start = 15.dp, end = 10.dp, top = 15.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(3.dp))
            notes.title?.let {
                CommonText(
                    text = it,
                    textSize = 3,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            notes.note?.let {
                CommonText(
                    text = it,
                    textSize = 1,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.67f),
                    maxLines = 3
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (notes.userImage != null) {
                    Image(
                        bitmap = notes.userImage.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(30.dp)
                            ),

                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(
                                RoundedCornerShape(15.dp)
                            ),

                        tint = MaterialTheme.colorScheme.outline
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
                Column(
                    modifier = Modifier.height(40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = notes.userName ?: "unknown",
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        lineHeight = 10.sp
                    )
                    Text(
                        text = notes.date,
                        fontSize = 5.sp,
                        color = MaterialTheme.colorScheme.outline,
                        lineHeight = 10.sp
                    )
                }

            }
            Box(
                modifier = Modifier
                    .shadow(
                        color = MaterialTheme.colorScheme.outline,
                        borderRadius = 20.dp,
                        blurRadius = 10.dp,
                        offsetX = 0.dp,
                        offsetY = 0.dp,
                        spread = 2.dp
                    )
                    .size(20.dp)
                    .clip(largeShape)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_arrow_right_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
fun SettingsComp(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    leadingIcon: Int
) {
    Row(
        modifier = modifier
            .padding(bottom = 10.dp)
            .shadow(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                borderRadius = 10.dp,
                blurRadius = 7.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 2.dp
            )
            .fillMaxWidth(0.95f)
            .height(70.dp)
            .clip(smallShape)
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(iconLarge)
            )
            Spacer(modifier = Modifier.width(20.dp))
            CommonText(text = text, textSize = 3)
        }
        Icon(
            painter = painterResource(id = R.drawable.rounded_arrow_right_24),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(iconLarge)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommonCompPrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        val notes = Notes(
            userName = "Hibro Onyancha",
            userImage = null,
            note = "Before we talk about how to access Services, let’s remember how the data flow works in this application. We are following the unidirectional data flow, which means that the app has a one-way flow where the data can move in only one direction when being transferred between the different components of the software.\n" +
                    "\n" +
                    "Composable functions also follow the unidirectional data flow pattern, and this is achieved by the fact that composable functions shouldn’t know anything about the services and the business logic, they should just observe the state changes emitted by the ViewModel. That said, only the ViewModels will access the Services:",
            date = "24th Fri,oct 2023 /11:56pm",
            title = "FireTest",
            noteImage = null
        )
        NotesRowComp(notes = notes)
    }
}