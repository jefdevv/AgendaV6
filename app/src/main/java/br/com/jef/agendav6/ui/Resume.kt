package br.com.jef.agendav6.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.jef.agendav6.databinding.ActivityResumeBinding
import br.com.jef.agendav6.ui.model.User
import br.com.jef.agendav6.ui.utils.Constants
import br.com.jef.agendav6.ui.utils.listener.GetUser

class Resume : AppCompatActivity() {
    private lateinit var binding: ActivityResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResumeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getUser()

    }

    private fun getUser() {
        val name = intent.getStringExtra(Constants.NAME)
        val tytpeTel = intent.getStringExtra(Constants.TELEPHONE_TYPE)
        val tel = intent.getStringExtra(Constants.TELEPHONE)
        val birth = intent.getStringExtra(Constants.BIRTH_DATE)
        val gender = intent.getStringExtra(Constants.GENDER)

        binding.txtNameResume.text = name
        binding.txtTytpeTelResume.text = tytpeTel
        binding.txtTelResume.text = tel
        binding.txtBirthDateResume.text = birth
        binding.txtGenderResume.text = gender


    }


}