package com.example.hydercontactapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.hydercontactapp.data.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact : Contact)

    @Query("SELECT * FROM contact_table")
    fun  getAllContact() : Flow<List<Contact>>
}