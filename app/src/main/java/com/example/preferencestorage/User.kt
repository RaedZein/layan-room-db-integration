package com.example.preferencestorage

import androidx.room.*
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "useraccount")
@TypeConverters(AdminDataConverter::class,DateConverter::class)
 class User(@PrimaryKey @ColumnInfo(name="firstname") var firstName: String,
            @ColumnInfo(name="lastname") var lastName: String,
            @ColumnInfo(name="email") var email: String,
            @TypeConverters(DateConverter::class)
            @ColumnInfo(name="dateofbirth") var dateOfBirth: Date,
            @TypeConverters(AdminDataConverter::class)
            val adminData: AdminData
)
class DateConverter {
    @TypeConverter
    fun dateToString(value: Date?): String? {
        val string =SimpleDateFormat("MM - dd - yyyy").format(value)
        return string
    }

    @TypeConverter
    fun stringToDate(value: String?): Date? {
        val date = SimpleDateFormat("MM - dd - yyyy").parse(value)
        return date
    }

}
class AdminDataConverter {
    @TypeConverter
    fun adminDataToString(value: AdminData?): String? {
        val string = Gson().toJson(value)
        return string
    }

    @TypeConverter
    fun stringToAdmindata(value: String?): AdminData? {
        val adminData = Gson().fromJson(value,AdminData::class.java)
        return adminData
    }

}