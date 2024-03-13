package com.example.firetest.presentation_layer.screens.registry.sighnUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firetest.R
import com.example.firetest.presentation_layer.contsants.BasicEditTextComp
import com.example.firetest.presentation_layer.contsants.Header
import com.example.firetest.presentation_layer.contsants.LottieAnimationComp
import com.example.firetest.presentation_layer.contsants.RoundButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun SignUpScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                isTrailingPresent = false,
                trailer = { /*TODO*/ },
                onLeadClick = { navigator.popBackStack() },
                leadingIcon = R.drawable.cancel
            )
            LottieAnimationComp(
                animation = R.raw.login, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicEditTextComp(
                onDone = { /*nothing*/ },
                onValueChange = {},
                hint = "user name",
                value = "",
                onPassWordClick = { /*nothing*/ },
                leadingIcon = R.drawable.user_icon,
                spacer = 10
            )
            BasicEditTextComp(
                onDone = { /*nothing*/ },
                onValueChange = {},
                hint = "email",
                value = "",
                onPassWordClick = { /*nothing*/ },
                leadingIcon = R.drawable.user_icon,
                spacer = 10
            )
            BasicEditTextComp(
                onDone = { /*nothing*/ },
                onValueChange = {},
                hint = "password",
                value = "",
                onPassWordClick = { /*nothing*/ },
                leadingIcon = R.drawable.user_icon,
                spacer = 10
            )
        }
        RoundButton(isPositive = true, text = "SignUp", onClick = { /*TODO*/ })
    }
}