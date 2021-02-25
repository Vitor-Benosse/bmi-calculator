package com.example.bmicalculator

object Util {
    fun validateInputs(weightInput: String, heightInput: String) : Boolean {
            return when {
                weightInput.isEmpty() || heightInput.isEmpty() -> {
                    false
                }
                else -> true
            }
    }
}