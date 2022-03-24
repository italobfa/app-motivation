package com.example.motivation.infra

class MotivationConstants private constructor(){
    //Se precisar mudar a chave, muda somente em um lugar
    object KEY {
        const val PERSON_NAME = "name"
    }

    object PHRASEFILTER{
        const val ALL = 1
        const val HAPPY = 2
        const val MORNING = 3
    }
}