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
        val peopleIcon = binding.peopleIcon

        if(weightInput.isNotEmpty() && heightInput.isNotEmpty()) {
            val calculateBmi = weightInput.toFloat() / (heightInput.toFloat() * heightInput.toFloat())
            val bmi = calculateBmi * 10000
            binding.apply {
                when {
                    bmi <= 18 -> peopleIcon.setColorFilter(getColor(R.color.orange))

                    bmi.toInt() in 19..24 -> peopleIcon.setColorFilter(getColor(R.color.green))

                    else -> peopleIcon.setColorFilter(getColor(R.color.red))
                }
            }
        } else {
            Toast.makeText(applicationContext, "Please enter all the values! ", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeColorTimestamp() {
        binding.apply {
            when (getTimestamp()) {
                0 -> peopleIcon.setColorFilter(getColor(R.color.white))
                1 -> peopleIcon.setColorFilter(getColor(R.color.black))
                2 -> peopleIcon.setColorFilter(getColor(R.color.blue))
                3 -> peopleIcon.setColorFilter(getColor(R.color.green))
                4 -> peopleIcon.setColorFilter(getColor(R.color.pink))
                5 -> peopleIcon.setColorFilter(getColor(R.color.red))
                6 -> peopleIcon.setColorFilter(getColor(R.color.purple_200))
                7 -> peopleIcon.setColorFilter(getColor(R.color.yellow))
                8 -> peopleIcon.setColorFilter(getColor(R.color.gray))
                9 -> peopleIcon.setColorFilter(getColor(R.color.lilac))
            }
        }
    }

    private fun getTimestamp(): Int {
        val timestamp = System.currentTimeMillis()/1000
        return timestamp.toString().takeLast(1).toInt()
    }
}
