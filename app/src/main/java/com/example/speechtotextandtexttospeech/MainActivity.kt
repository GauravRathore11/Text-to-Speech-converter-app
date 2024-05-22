package com.example.speechtotextandtexttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enteredText=findViewById<EditText>(R.id.enteredText)
        val btnSpeak=findViewById<Button>(R.id.btnSpeak)

        textToSpeech = TextToSpeech(this){status->
            if(status==TextToSpeech.SUCCESS){
                val result=textToSpeech.setLanguage(Locale.getDefault())
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this,"Language not supoorted!",Toast.LENGTH_LONG).show()
                }
            }
        }

        btnSpeak.setOnClickListener{
            if(enteredText.text.toString().trim().isNotEmpty()){
                textToSpeech.speak(enteredText.text.toString().trim(),TextToSpeech.QUEUE_FLUSH,null,null)
            }
            else{
                Toast.makeText(this,"Required",Toast.LENGTH_LONG).show()
            }
        }
    }
}