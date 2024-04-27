package com.example.rollnori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2900 // Splash screen timeout in milliseconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Video Animation
        val videoView: VideoView = findViewById(R.id.videoView)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.ssanim}")
        videoView.setVideoURI(videoUri)
        videoView.start()

        // Fade Animation - Delay and start MainActivity
        android.os.Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out) // Apply fade-in/fade-out animation
            finish()
        }, SPLASH_TIME_OUT)
    }
}