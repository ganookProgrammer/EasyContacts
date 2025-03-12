package com.example.hydercontactapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hydercontactapp.data.dao.ContactDao
import com.example.hydercontactapp.data.tables.Contact

@Database(entities = [Contact::class] , version = 2)
abstract class ContactDatabase : RoomDatabase(){
    abstract  fun contactDao() : ContactDao
}