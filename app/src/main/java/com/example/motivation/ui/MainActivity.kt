package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mphraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        text_name.text = "Olá $name!"
        //Aplicando a categoria ALL como default e
        // fazendo a chamada de uma frase chamando logo a função assim que a activity é iniciada.
        image_all.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        button_new_phrase.setOnClickListener(this)
        image_all.setOnClickListener(this)
        image_happy.setOnClickListener(this)
        image_morning.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.image_all, R.id.image_happy, R.id.image_morning)

        if (id == R.id.button_new_phrase) {
            handleNewPhrase()

        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleNewPhrase() {
        val phrase = Mock().getPhrase(mphraseFilter)
        text_phrase.text = phrase
    }

    private fun handleFilter(id: Int) {

        image_all.setColorFilter(resources.getColor(R.color.white))
        image_happy.setColorFilter(resources.getColor(R.color.white))
        image_morning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.image_all -> {
                image_all.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                image_happy.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_morning -> {
                image_morning.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }
}