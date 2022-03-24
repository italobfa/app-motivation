package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    //o Contexto só vai existir depois do onCreate, por isso a instância da classe, passando o contexto, precisa ser depois
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        button_save.setOnClickListener(this)

        verifyName()
    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = edit_name.text.toString()
        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show()
        }
    }
}