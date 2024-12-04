package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

enum class Operator{
    ADD,
    SUB,
    MUL,
    DIV,
    NONE
}

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var button9: Button
    private lateinit var button8: Button
    private lateinit var button7: Button
    private lateinit var button6: Button
    private lateinit var button5: Button
    private lateinit var button4: Button
    private lateinit var button3: Button
    private lateinit var button2: Button
    private lateinit var button1: Button
    private lateinit var button0: Button
    private lateinit var buttonadd: Button
    private lateinit var buttonsubtract: Button
    private lateinit var buttonmultiply: Button
    private lateinit var buttondivide: Button
    private lateinit var buttonequal: Button
    private lateinit var buttondot: Button
    private lateinit var buttonclr: Button
    private lateinit var buttonsign: Button
    private var isOperatorClicked : Boolean = false
    private var operand1: Double=0.0
    private var  operand2 : Double=0.0
    private var result:Double = 0.0
    private var operator:Operator = Operator.NONE
    private lateinit var numbuttonlist : Array<Button> //this button list will contain the buttons that can simply be appended to the string-builder, like numbers
    private lateinit var operationbuttonlist : Array<Button> //this button list will contain the buttons that can simply be appended to the string-builder, like numbers
    private var dataLine = StringBuilder() //using a stringbuilder bcoz string is immutable in java and thus to save memory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initializeButtons()
        for (i in numbuttonlist){
            i.setOnClickListener{numberButtonClicked(i)}
        }
        for (i in operationbuttonlist){
            i.setOnClickListener{operationButtonClicked(i)}
        }
        buttonequal.setOnClickListener{operateEquals(operator)}
    }
    private fun initializeButtons() {
        display= findViewById(R.id.display)
        buttonadd = findViewById(R.id.buttonadd)
        buttonmultiply = findViewById(R.id.buttonmultiply)
        buttonsubtract = findViewById(R.id.buttonsubtract)
        buttondivide = findViewById(R.id.buttondivide)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttondot = findViewById(R.id.buttondot)
        buttonclr = findViewById(R.id.buttonclr)
        buttonequal = findViewById(R.id.buttonequal)
        buttonsign = findViewById(R.id.buttonsign)
        numbuttonlist = arrayOf(button0,
            button1,button2,button3,
            button4,button5,button6,
            button7,button8,button9,buttondot,buttonsign
        )
        operationbuttonlist = arrayOf(buttonadd,
            buttonsubtract,buttondivide,
            buttonmultiply,buttonclr,
            buttonequal)
    }
    private fun numberButtonClicked(btn: Button) {
        if(btn == buttonsign) //checks if the sign button has been clicked
        {
            if(dataLine.isEmpty() || dataLine[0]!='-')
                dataLine.insert(0,"-")
            else
                dataLine.deleteCharAt(0)
        }
        else
        {
            if (isOperatorClicked) {
                if (operand1 == 0.0)
                    operand1 = dataLine.toString().toDouble()
                dataLine.clear()
                display.text = ""
                isOperatorClicked = false

            }
            dataLine.append(btn.text)
        }
        display.text=dataLine
    }
    private fun operationButtonClicked(btn:Button) {

            isOperatorClicked = true
            if (operand1 == 0.0)
                operand1 = dataLine.toString().toDouble()
            else {
                operand2 = dataLine.toString().toDouble()
                operateEquals(operator)
                operand1 = 0.0
            }
            when (btn.text) {
                "+" -> operator = Operator.ADD
                "-" -> operator = Operator.SUB
                "*" -> operator = Operator.MUL
                "/" -> operator = Operator.DIV
                "CLEAR" -> {
                    dataLine.clear()
                    operand1 = 0.0
                    operand2 = 0.0
                    result = 0.0
                    display.text = ""
                    isOperatorClicked = false
                }
                else -> {
                    operator = Operator.NONE
                    isOperatorClicked = false
                }
            }
    }

    private fun operateEquals(operator: Operator) {
        operand2 = dataLine.toString().toDouble()
        result = when (operator) {
            Operator.ADD -> {
                operand1 + operand2
            }

            Operator.SUB -> {
                operand1 - operand2
            }

            Operator.MUL -> {
                operand1 * operand2
            }

            Operator.DIV -> {
                operand1 / operand2
            }

            Operator.NONE -> {
                operand1
            }
        }
        if(result%1 == 0.0){
            dataLine.replace(0,dataLine.length,result.toInt().toString())
        }
        else
            dataLine.replace(0,dataLine.length,result.toString())
        display.text = dataLine
    }


}