package com.example.bmicalculator.home.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bmicalculator.R
import com.example.bmicalculator.Util
import com.example.bmicalculator.databinding.ActivityHomeBinding
import com.example.bmicalculator.home.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(
            HomeViewModel::class.java
        )
    }
    private lateinit var binding: ActivityHomeBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.calculateButton.setOnClickListener {
            val weightInput = binding.weightInput.text.toString()
            val heightInput = binding.heightInput.text.toString()

            when {
                Util.validateInputs(weightInput, heightInput) -> {
                    val bmi = viewModel.calculateBmi(weightInput, heightInput)
                    binding.bmiResult.apply {
                        text = bmi.toString()
                    }
                    binding.peopleIcon.apply {
                        when {
                            bmi <= 18 -> setColorFilter(getColor(R.color.orange))
                            bmi in 19..24 -> setColorFilter(getColor(R.color.green))
                            else -> setColorFilter(getColor(R.color.red))
                        }
                    }
                }
                else -> {
                    Snackbar.make(
                        binding.calculateButton,
                        "Please enter all the values!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
