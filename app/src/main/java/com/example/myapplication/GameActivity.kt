package com.example.myapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import kotlin.random.Random
import android.os.Handler
import android.os.Looper

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout file where the ImageView is defined
        setContentView(R.layout.game_activity)

        // Function to create and animate a bug
        fun createAndAnimateBug() {
            // Randomly choose a bug (ant, grasshopper, or worm)
            val bug = ImageView(this)
            val bugType = Random.nextInt(3) // 0, 1, or 2
            when (bugType) {
                0 -> bug.setImageResource(R.drawable.ant) // Set image to ant
                1 -> bug.setImageResource(R.drawable.grasshopper) // Set image to grasshopper
                2 -> bug.setImageResource(R.drawable.worm) // Set image to worm
            }

            // Add the bug to the layout
            val layout = findViewById<android.widget.RelativeLayout>(R.id.game_layout)
            layout.addView(bug)

            // Get screen width and height
            val screenWidth = resources.displayMetrics.widthPixels
            val screenHeight = resources.displayMetrics.heightPixels

            // Set a random Y position for the bug
            val fixedY = (screenHeight * 0.74).toInt()
            bug.y = fixedY.toFloat()

            // Animate the bug from right to left
            val animator = ObjectAnimator.ofFloat(bug, "translationX", screenWidth.toFloat(), -screenWidth.toFloat())
            animator.duration = 5000L // Duration in milliseconds (5 seconds)
            animator.start()

            // After 1 second, create a new bug
            Handler(Looper.getMainLooper()).postDelayed({
                createAndAnimateBug()
            }, 1000L) // 1000ms delay
        }

        // Start creating bugs on activity launch
        createAndAnimateBug()
    }
}
