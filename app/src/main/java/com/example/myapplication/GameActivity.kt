package com.example.myapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import kotlin.random.Random



class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Set the layout file where the ImageView is defined
        setContentView(R.layout.game_activity)

        val ant: ImageView = findViewById(R.id.ant)

        // Get screen width
        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels

        // Set a random Y position for the bug
        val randomY = Random.nextInt(0, screenHeight - ant.layoutParams.height)
        ant.y = randomY.toFloat()

        // Animate the bug from right to left
        val animator = ObjectAnimator.ofFloat(ant, "translationX", screenWidth.toFloat(), -screenWidth.toFloat())
        animator.duration = 5000L // Duration in milliseconds (3 seconds)
        animator.start()
    }
}

