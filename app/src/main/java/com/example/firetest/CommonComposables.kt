package com.example.firetest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.firetest.ui.theme.iconStandard
import com.example.firetest.ui.theme.mediumLargeText
import com.example.firetest.ui.theme.smallShape
import com.example.firetest.ui.theme.smallText

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

