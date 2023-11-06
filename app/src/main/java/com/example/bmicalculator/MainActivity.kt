package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText=findViewById<EditText>(R.id.etWeight)
        val hightText=findViewById<EditText>(R.id.etHight)
        val calcButton=findViewById<Button>(R.id.btnCalc)
        calcButton.setOnClickListener {
            val weight=weightText.text.toString()
            val hight=hightText.text.toString()
            if (validatInput(weight,hight)){
            val bodyBmi=(weight.toFloat()/hight.toFloat()/hight.toFloat())*10000
            //todisplay bmi in 2 decimal points
            val bmi2D= String.format("%.2f",bodyBmi).toFloat()
            bmiDisplay(bmi2D)}
        }

    }
    //validate Input weight,hight is null??
    private fun validatInput ( weight:String?,hight:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"please enter your weight",Toast.LENGTH_LONG).show()
                return false
            }
            hight.isNullOrEmpty() ->{
                Toast.makeText(this,"please enter your hight",Toast.LENGTH_LONG).show()
                return false
            }else->{
                return true
            }


        }
    }
    private fun bmiDisplay(bmiParameter:Float){
        val bmiResult=findViewById<TextView>(R.id.tvIndex)
        val resultDes=findViewById<TextView>(R.id.tvResult)
        val normalRange=findViewById<TextView>(R.id.tvRange)
        bmiResult.text=bmiParameter.toString()
        normalRange.text="(Normal Range is 18.5-24.9)"
        var resultText=""
        var color=0
        when{
            bmiParameter <18.50 ->{
                resultText="under weight"
                color=R.color.under_weight
            }

                bmiParameter in (18.50..24.9)->{
                    resultText="Healthy"
                    color=R.color.normal
                }

                    bmiParameter in 25.00..29.99->{
                        resultText="over weight"
                        color=R.color.over_weight
                    }

                        bmiParameter > 29.99 ->{
                            resultText="abese"
                            color=R.color.abese
                        }



        }
        resultDes.setTextColor(ContextCompat.getColor(this,color))
        resultDes.text=resultText

    }
}
