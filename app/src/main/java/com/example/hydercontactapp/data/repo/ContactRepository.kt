package com.example.hydercontactapp.data.repo

import com.example.hydercontactapp.data.database.ContactDatabase
import com.example.hydercontactapp.data.tables.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ContactRepository @Inject constructor(private val database : ContactDatabase) {

   suspend fun upsertContact(contact: Contact){
        database.contactDao().upsertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        database.contactDao().deleteContact(contact)
    }

    fun getAllContacts() : Flow<List<Contact>>{
        return database.contactDao().getAllContact()
    }


}