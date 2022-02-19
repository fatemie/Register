package com.example.register

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.register.databinding.ActivityMainBinding
import kotlin.math.E

const val NAME = "NAME"
const val USERNAME = "USERNAME"
const val PASS = "PASS"
const val EMAIL = "EMAIL"
const val GENDER = "GENDER"

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var passIsTrue = false
    lateinit var prefs : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setListener()

    }

    fun setListener(){
        binding.buttonRegister.setOnClickListener {
            register()
        }
        binding.buttonShowInfo.setOnClickListener {
            showInfo()
        }
        binding.buttonHideInfo.setOnClickListener {
            hideInfo()
        }
    }


    fun register(){
        val editor =  prefs.edit()
        editor.putString(NAME, binding.editTextPersonName.text.toString())
        editor.putString(USERNAME, binding.editTextPersonUserName.text.toString())
        editor.putString(EMAIL, binding.editTextEmailAddress.text.toString())
        val pass1 = binding.editTextPersonPassword.text.toString()
        val pass2 = binding.editTextPersonReTypePassword.text.toString()

        if(pass1==pass2){
            editor.putString(PASS, pass1)
            passIsTrue = true
        }else{
            Toast.makeText(this, "password retype is wrong", Toast.LENGTH_LONG).show()
        }

        val gender = when(binding.gender.checkedRadioButtonId){
            binding.female.id -> "Female"
            binding.male.id -> "Male"
            else -> "not choose!"
        }
        editor.putString(GENDER, gender)
        editor.apply()
        Toast.makeText(this, "infornation registered", Toast.LENGTH_SHORT).show()
    }

    fun showInfo(){
        if(passIsTrue){
            binding.textViewName.text= prefs.getString(NAME,"")
            binding.textViewUserName.text= prefs.getString(USERNAME,"")
            binding.textViewEmail.text=prefs.getString(EMAIL,"")
            binding.textViewPass.text=prefs.getString(PASS,"")
            binding.textViewGender.text=prefs.getString(GENDER,"")
            binding.showInfoLayout.visibility = View.VISIBLE
        }
    }

    fun hideInfo(){
        binding.showInfoLayout.visibility = View.GONE
    }
}

