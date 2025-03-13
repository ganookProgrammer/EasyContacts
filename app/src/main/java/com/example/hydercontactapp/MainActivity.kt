package com.example.hydercontactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hydercontactapp.ui.theme.HyderContactAppTheme
import com.example.hydercontactapp.ui_layer.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            HyderContactAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation()
//                    GetPermission()
                }
            }
        }
    }
}


//@Composable
//fun GetPermission() {
//    val permission = android.Manifest.permission.CALL_PHONE
//
//    var isGranted = remember {
//        mutableStateOf(false)
//    }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = {result ->
//            isGranted.value =  result
//        }
//    )
//
//    if (isGranted.value){
//        Text(text = "Permission Granted")
//    }else{
//        Button(onClick ={launcher.launch(permission)} ) { Text(text = "Permission") }
//    }
//
//
//}