package com.example.hydercontactapp.ui_layer.screen

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hydercontactapp.R
import com.example.hydercontactapp.ui_layer.state.ContactState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenUI( navController: NavController, state : ContactState,
                     onEvent:() -> Unit) {

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        if (it != null){
            val inputStream = context.contentResolver.openInputStream(it)
            val byte = inputStream?.readBytes()
            state.image.value = byte
        }
    }

    Scaffold(
        topBar = {
            TopAppBar( title = {}, actions = {
                IconButton(onClick ={
                    onEvent.invoke()
                    navController.navigateUp()
                } ) { if(state.id.value !=null){
                    Text("Edit",
                        fontSize = 17.sp,
                    )
                }
                else{
                    Text("Add",
                        fontSize = 17.sp,
                    )

                }
                }
            },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.DarkGray,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
                    ,
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()})
                    { if(state.id.value != null)
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "close")
                    else {Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "back")}}
                }
            )
        }
    ) {innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(Modifier.fillMaxWidth()
                .padding(top = 20.dp),
                contentAlignment = Alignment.Center) {
                if (state.image.value == null){
                    Image(painter = painterResource(id = R.drawable.add_user), contentDescription = null
                        , modifier = Modifier
                            .size(90.dp, 90.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            })
                }else {
                    Image(bitmap = BitmapFactory.decodeByteArray(
                        state.image.value,
                        0,
                        state.image.value!!.size
                    ).asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            .size(90.dp, 90.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop)
                }

            }
            Spacer(Modifier.height(40.dp))
            TextField(value = state.name.value , onValueChange = { newValue ->
                state.name.value = newValue
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                label = { Text(text = "Name") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "person_name")
                })

            Spacer(Modifier.height(15.dp))
            TextField(value = state.number.value , onValueChange = { newValue ->
                state.number.value = newValue
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = { Text("Number") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Phone, contentDescription = "phone_number")
                })

            Spacer(Modifier.height(15.dp))
            TextField(value = state.email.value , onValueChange = { newValue ->
                state.email.value = newValue
            } , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                label = { Text("Email") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Email, contentDescription = "email")
                })



        }
    }
}
