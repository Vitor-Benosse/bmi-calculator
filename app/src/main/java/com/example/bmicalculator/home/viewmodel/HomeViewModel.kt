package com.example.bmicalculator.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    fun calculateBmi(weightInput: String, heightInput: String) : Int {
        val calculateBmi = weightInput.toFloat() / (heightInput.toFloat() * heightInput.toFloat())
        val bmi = calculateBmi * 10000
        return bmi.toInt()
    }
}