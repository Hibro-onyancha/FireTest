package com.example.firetest.presentation_layer.screens.notes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firetest.R
import com.example.firetest.data_layer.fire.other.Notes
import com.example.firetest.presentation_layer.contsants.CommonSearch
import com.example.firetest.presentation_layer.contsants.CommonText
import com.example.firetest.presentation_layer.contsants.Header
import com.example.firetest.presentation_layer.contsants.NotesColumnComp
import com.example.firetest.presentation_layer.contsants.NotesRowComp
import com.example.firetest.presentation_layer.screens.destinations.AddNotesScreenDestination
import com.example.firetest.presentation_layer.screens.destinations.SettingsScreenDestination
import com.example.firetest.presentation_layer.screens.destinations.SignUpScreenDestination
import com.example.firetest.presentation_layer.theme.smallShape
import com.example.firetest.utils.getCurrentDate
import com.example.firetest.utils.getGreeting
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Destination
@Composable
fun NotesScreen(
    navigator: DestinationsNavigator
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
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(smallShape)
                    .clickable {
                        navigator.navigate(AddNotesScreenDestination) {
                            launchSingleTop = true
                            popUpTo(AddNotesScreenDestination.route)
                        }
                    }
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(isTrailingPresent = true, trailer = {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = smallShape
                        )
                        .padding(5.dp)
                        .clickable {
                            navigator.navigate(SignUpScreenDestination) {
                                launchSingleTop = true
                                popUpTo(SignUpScreenDestination.route)
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    CommonText(
                        text = "signIn",
                        textSize = 2,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }, onLeadClick = {
                navigator.navigate(SettingsScreenDestination) {
                    launchSingleTop = true
                    popUpTo(SettingsScreenDestination.route)
                }
            }, leadingIcon = R.drawable.settings, text = "Notes")
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "${getGreeting(context)}\nToday is on ${getCurrentDate()}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        textAlign = TextAlign.Start,
                        lineHeight = 60.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Cursive,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CommonSearch(
                        onSearch = { /*TODO*/ },
                        value = "",
                        onValueChange = {},
                        hint = "search note"
                    )

                }
                item {
                    NotesRowComp(notes = notes)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NotesColumnComp(notes = notes, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(10.dp))
                        NotesColumnComp(notes = notes, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NotesColumnComp(notes = notes, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(10.dp))
                        NotesColumnComp(notes = notes, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                item {
                    NotesRowComp(notes = notes)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

    }
}