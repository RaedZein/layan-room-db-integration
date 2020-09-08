package com.example.preferencestorage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.registration.*

class MainActivity : AppCompatActivity() {
   lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db= Room.databaseBuilder(applicationContext,UserDatabase::class.java,"UserDB").build()

        sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("logged", "").toString().equals("logged")) {


           val thread = Thread {
           val users = db.userDao().getUsers()
           val user = users[users.size - 1]

           val intent = Intent(this, DitailesActivity::class.java).apply {
                                putExtra("firstname", user.firstName)
                                putExtra("lastname", user.lastName)
                                putExtra("email", user.email)
                                putExtra("dateofbirth",user.dateOfBirth)


                            }
                            startActivity(intent)

            }
            thread.start()
        } else {
            setContentView(R.layout.registration)
            Login.setOnClickListener {
                val thread = Thread {
                    val userEntity = User(
                        FirstName.text.toString(),
                        LastName.text.toString(),
                        Email.text.toString(),
                        DateOfBirth.text.toString()
                    )
                    db.userDao().insert(userEntity)
                    sharedPreferences.edit().putString("logged", "logged").apply()
                    val intent = Intent(this, DitailesActivity::class.java).apply {
                        putExtra("firstname", userEntity.firstName)
                        putExtra("lastname", userEntity.lastName)
                        putExtra("email", userEntity.email)
                        putExtra("dateofbirth", userEntity.dateOfBirth)

                    }
                    startActivity(intent)


                }
                thread.start()

            }


            }

        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        val picker: MaterialDatePicker<*> = builder.build()
        builder.setTitleText("select a date")

        DateOfBirth.setOnClickListener {
            picker.show(supportFragmentManager, picker.toString())
        }
        picker.addOnPositiveButtonClickListener {
            DateOfBirth.setText(picker.headerText)
        }

    }






}
