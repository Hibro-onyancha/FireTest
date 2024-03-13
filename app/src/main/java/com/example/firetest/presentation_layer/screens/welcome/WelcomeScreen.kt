package com.example.firetest.presentation_layer.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firetest.R
import com.example.firetest.presentation_layer.contsants.CommonText
import com.example.firetest.presentation_layer.contsants.Header
import com.example.firetest.presentation_layer.contsants.LottieAnimationComp
import com.example.firetest.presentation_layer.contsants.RoundButton
import com.example.firetest.presentation_layer.screens.destinations.NotesScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.system.exitProcess

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(
            isTrailingPresent = false,
            trailer = { /*do nothing*/ },
            onLeadClick = { exitProcess(0) },
            leadingIcon = R.drawable.cancel
        )
        LottieAnimationComp(animation = R.raw.fire, modifier = Modifier.fillMaxHeight(0.4f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonText(
                text = "Welcome,lets Discover fire",
                textSize = 5,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(20.dp))
            CommonText(
                text = "hey ,wanna explore how fire base works ,well then you are in the right place so lets dive in,write some notes and save them while at it its quite interesting if you ask me",
                textSize = 2,
                textAlign = 2,
                modifier = Modifier.fillMaxWidth(0.8f),
                maxLines = 10,
                isBold = false,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(30.dp))
            RoundButton(
                isPositive = true,
                text = "discover",
                onClick = {
                    navigator.navigate(NotesScreenDestination) {
                        launchSingleTop = true
                        popUpTo(NotesScreenDestination.route)
                    }
                })
        }
    }
}