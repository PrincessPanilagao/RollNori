package com.example.rollnori

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start Cooking Button
        val startCookingButton = findViewById<ImageButton>(R.id.startCookingButton)

        // Initialize MediaPlayer with the click sound
        mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick)

        // Set OnClickListener to ImageButton
        startCookingButton.setOnClickListener {
            // Play the sound when the ImageButton is clicked
            mediaPlayer.start()

            // Move to CookingScreen1 activity
            val intent = Intent(this, CookingScreen1::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer when the activity is destroyed
        mediaPlayer.release()
    }
}