package com.example.firetest.presentation_layer.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firetest.R
import com.example.firetest.presentation_layer.contsants.Header
import com.example.firetest.presentation_layer.contsants.SettingsComp
import com.example.firetest.presentation_layer.screens.destinations.LoginScreenDestination
import com.example.firetest.presentation_layer.screens.destinations.SignUpScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            isTrailingPresent = false,
            trailer = {/*nothing*/ },
            onLeadClick = { navigator.popBackStack() },
            leadingIcon = R.drawable.arrow_left_24,
            text = "Settings"
        )
        Spacer(modifier = Modifier.height(30.dp))
        SettingsComp(text = "login", onClick = {
            navigator.navigate(LoginScreenDestination) {
                launchSingleTop = true
                popUpTo(LoginScreenDestination.route)
            }
        }, leadingIcon = R.drawable.add_user)
        Spacer(modifier = Modifier.height(30.dp))
        SettingsComp(text = "signIn", onClick = {
            navigator.navigate(SignUpScreenDestination) {
                launchSingleTop = true
                popUpTo(SignUpScreenDestination.route)
            }
        }, leadingIcon = R.drawable.user_icon)
    }
}