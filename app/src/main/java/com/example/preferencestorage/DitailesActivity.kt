package com.example.preferencestorage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.ditailes.*

class DitailesActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ditailes)
        FirstNametv.text=intent.getStringExtra("firstname")
        LastNametv.text=intent.getStringExtra("lastname")
        Emailtv.text=intent.getStringExtra("email")
        DateOfBirthtv.text=intent.getStringExtra("dateofbirth")

        sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        logout.setOnClickListener {
            sharedPreferences.edit().putString("logged", "").apply()


            val intent = Intent(this, MainActivity::class.java)



            startActivity(intent)
        }

    }
}