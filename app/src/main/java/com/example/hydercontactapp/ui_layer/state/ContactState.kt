package com.example.hydercontactapp.ui_layer.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.hydercontactapp.data.tables.Contact

data class ContactState(
    val contactList : List<Contact> = emptyList(),
    var id : MutableState<Int?> = mutableStateOf(null),
    val name : MutableState<String> = mutableStateOf(""),
    val number : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val image : MutableState<ByteArray?> = mutableStateOf(null)
)
