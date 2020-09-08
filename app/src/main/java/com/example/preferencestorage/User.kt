package com.example.preferencestorage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "useraccount")
 class User(@PrimaryKey @ColumnInfo(name="firstname") var firstName: String,
            @ColumnInfo(name="lastname") var lastName: String,
            @ColumnInfo(name="email") var email: String,
            @ColumnInfo(name="dateofbirth") var dateOfBirth: String
)
