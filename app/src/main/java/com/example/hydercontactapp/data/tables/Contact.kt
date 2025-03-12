package com.example.hydercontactapp.data.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val name : String,
    val number : String,
    val email : String ,
    val image : ByteArray? = null
)
