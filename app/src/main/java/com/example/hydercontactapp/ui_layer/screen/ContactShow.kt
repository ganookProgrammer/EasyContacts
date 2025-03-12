package com.example.hydercontactapp.ui_layer.screen

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hydercontactapp.R
import com.example.hydercontactapp.ui_layer.navigation.ContactAdd
import com.example.hydercontactapp.ui_layer.state.ContactState
import com.example.hydercontactapp.ui_layer.viewmodel.ContactViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavHostController,
    state: ContactState,
    viewModel: ContactViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Contact App")
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color.White
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            state.id.value = null
            state.name.value = ""
            state.number.value = ""
            state.email.value = ""
            state.image.value = null
            navController.navigate(ContactAdd)
        }, containerColor = Color.DarkGray) { Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add",
            tint = Color.White)}
    }) { innerPadding ->

        Column(Modifier.padding(innerPadding)) {
            LazyColumn {
                items(state.contactList) { contact ->

                    Spacer(Modifier.height(5.dp))
                    Card(Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .combinedClickable(
                            onClick = {},
                            onDoubleClick = {
                                state.id.value = contact.id
                                state.name.value = contact.name
                                state.number.value = contact.number
                                state.email.value = contact.email
                                state.image.value = contact.image
                                navController.navigate(ContactAdd)
                            },
                            onLongClick = {}
                        )) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {


                            if (contact.image == null) {

                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_background),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Black, CircleShape)
                                )

                            } else {
                                Image(
                                    bitmap = BitmapFactory.decodeByteArray(
                                        contact.image,
                                        0,
                                        contact.image.size!!
                                    ).asImageBitmap(), contentDescription = null,
                                    modifier = Modifier
                                        .size(66.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Black, CircleShape)
                                )
                            }


                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .padding(horizontal = 8.dp)
                            ) {
                                Text(
                                    contact.name,
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    contact.number,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    contact.email,
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Column(Modifier.padding(end = 6.dp)) {
                                IconButton(
                                    onClick = {
                                        viewModel.state.value.id.value = contact.id
                                        viewModel.state.value.name.value = contact.name
                                        viewModel.state.value.number.value = contact.number
                                        viewModel.state.value.email.value = contact.email
                                        viewModel.state.value.image.value = contact.image
                                        viewModel.deleteContact()
                                    }) {
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        contentDescription = "delete",
                                        tint = MaterialTheme.colorScheme.error
                                    )

                                }

                                IconButton(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:${contact.number}")
                                        }
                                        context.startActivity(intent)

                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Call,
                                        contentDescription = "call",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}

