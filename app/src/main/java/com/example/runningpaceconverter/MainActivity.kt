package com.example.runningpaceconverter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var buttonConvertPace: Button
    lateinit var editTextInsertPace: EditText
    lateinit var textMinResultPace: TextView
    lateinit var textSecResultPace: TextView
    lateinit var emptyDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonConvertPace = findViewById(R.id.button)
        editTextInsertPace = findViewById(R.id.editText1)
        textMinResultPace = findViewById(R.id.textView2)
        textSecResultPace = findViewById(R.id.textView4)

        buttonConvertPace.setOnClickListener(View.OnClickListener { v -> convertUnit(v) })
    }

    private fun convertUnit(v: View?) = if (editTextInsertPace.getText().toString().isEmpty()){
        emptyDate = "Insert all data"
        Toast.makeText(applicationContext, emptyDate, Toast.LENGTH_LONG).show()
    }else {
        emptyDate = "All ok"
        Toast.makeText(applicationContext, emptyDate, Toast.LENGTH_LONG).show()

        //Calculamos por una parte el editext de los minutos y por otra el editext de los segundos
        //Cuando el editext de los minutos es menor de 10 añadimos un 0 para completar. Ej. 03:09
        if((60 / (editTextInsertPace.text.toString().toDouble())) < 10){
            textMinResultPace.text = "0" + (60 / (editTextInsertPace.text.toString().toDouble())).toInt().toString()
        }else{
            textMinResultPace.text = (60 / (editTextInsertPace.text.toString().toDouble())).toInt().toString()
        }

        //Para el editext de los segundos sacamos la parte decimal con el módulo
        if(((60 / (editTextInsertPace.text.toString().toDouble())) % 1) >0){

            //Redondeamos sin más decimales la parte decimal y multiplicamos por 100 para coger 2 dígitos
            val rounded = String.format("%.0f", (((60 / (editTextInsertPace.text.toString().toDouble())) % 1) * 0.6) * 100)

                //Cuando el editext de los segundos es menor de 10, añadimos un 0 para completar. Ej. 03:09
                if(rounded.toInt() < 10){
                    textSecResultPace.text = "0" + rounded.toString()

                }else{
                    textSecResultPace.text = rounded.toString()
                }
        } else{
            //Si no hay parte decimal se añade 00 al editext de los segundos. Ej. 03:00
            textSecResultPace.text = "00"
        }
    }
}


