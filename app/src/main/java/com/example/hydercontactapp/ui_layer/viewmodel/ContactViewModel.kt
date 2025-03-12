package com.example.hydercontactapp.ui_layer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydercontactapp.data.repo.ContactRepository
import com.example.hydercontactapp.data.tables.Contact
import com.example.hydercontactapp.ui_layer.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(val repository: ContactRepository) : ViewModel() {

    val contactList = repository.getAllContacts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())

    val state = combine(_state, contactList) { _state, contacts ->
        _state.copy(contactList = contacts)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun upsertContact() {
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            image = state.value.image.value,
        )
        viewModelScope.launch {
            repository.upsertContact(contact)
        }
    }

    fun deleteContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            image = state.value.image.value,
        )
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

}