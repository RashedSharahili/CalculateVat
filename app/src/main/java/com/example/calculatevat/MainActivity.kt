package com.example.calculatevat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatevat.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //TODO :- [1] Create binding Object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO :- [2] Layout View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        //TODO :- Button Binding And Calculate Vat
        binding.btnCalculateVat.setOnClickListener {

            calculateVat()
        }
    }

    private fun calculateVat() {

        //TODO :- Receive ans Save inputs

        val stringCost = binding.etCoast.text.toString()
        val cost = stringCost.toDouble()

        val selectedOption = binding.rgOptionPercentage.checkedRadioButtonId

        val vatPercentage = when(selectedOption) {

            binding.optionTen.id -> 0.10
            binding.optionFiften.id -> 0.15
            else -> 0.20
        }

        val switchRound = binding.roundTotalSwitch.isChecked

        val vat = vatPercentage * cost

        var total = vat + cost

        if(switchRound) {

            total = kotlin.math.ceil(total)
        }

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.tvTotal.text = getString(R.string.tv_total_vat, formattedTotal)
    }
}