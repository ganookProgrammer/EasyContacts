package com.example.hydercontactapp.ui_layer.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydercontactapp.ui_layer.screen.AddEditScreenUI
import com.example.hydercontactapp.ui_layer.screen.HomeScreenUI
import com.example.hydercontactapp.ui_layer.viewmodel.ContactViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavigation(viewModel: ContactViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()
    NavHost(navController = navController , startDestination = ContactShow) {
        composable<ContactShow> { HomeScreenUI(navController,state = state) }
        composable<ContactAdd> {
            AddEditScreenUI(navController,state = state,
                onEvent = {viewModel.upsertContact()})

        }
    }

}