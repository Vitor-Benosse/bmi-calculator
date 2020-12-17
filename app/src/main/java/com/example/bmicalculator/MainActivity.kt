package com.example.bmicalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.calculateButton.setOnClickListener {
            calculateBmi()
        }

        binding.go4allButton.setOnClickListener {
            changeColorTimestamp()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun calculateBmi() {
        val weightInput = binding.weightInput.text.toString()
        val heightInput = binding.heightInput.text.toString()

        if(weightInput.isNotEmpty() && heightInput.isNotEmpty()) {
            val calculateBmi = weightInput.toFloat() / (heightInput.toFloat() * heightInput.toFloat())
            val bmi = calculateBmi * 10000
            binding.peopleIcon.apply {
                when {
                    bmi <= 18.9 -> setColorFilter(getColor(R.color.orange))

                    bmi.toInt() in 19..24 -> setColorFilter(getColor(R.color.green))

                    else -> setColorFilter(getColor(R.color.red))
                }
            }
        } else {
            Toast.makeText(applicationContext, "Please enter all the values! ", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeColorTimestamp() {
        binding.peopleIcon.apply {
            when (getTimestamp()) {
                0 -> setColorFilter(getColor(R.color.white))
                1 -> setColorFilter(getColor(R.color.black))
                2 -> setColorFilter(getColor(R.color.blue))
                3 -> setColorFilter(getColor(R.color.green))
                4 -> setColorFilter(getColor(R.color.pink))
                5 -> setColorFilter(getColor(R.color.red))
                6 -> setColorFilter(getColor(R.color.purple_200))
                7 -> setColorFilter(getColor(R.color.yellow))
                8 -> setColorFilter(getColor(R.color.gray))
                9 -> setColorFilter(getColor(R.color.lilac))
            }
        }
    }

    private fun getTimestamp(): Int {
        val timestamp = System.currentTimeMillis()/1000
        return timestamp.toString().takeLast(1).toInt()
    }
}
