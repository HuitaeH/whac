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

        // Randomly choose an image (ant, grasshopper, or worm)
        val bug: ImageView = findViewById(R.id.ant) // Access the ImageView correctly
        val bugType = Random.nextInt(3) // 0, 1, or 2
        when (bugType) {
            0 -> bug.setImageResource(R.drawable.ant) // Set image to ant
            1 -> bug.setImageResource(R.drawable.grasshopper) // Set image to grasshopper
            2 -> bug.setImageResource(R.drawable.worm) // Set image to worm
        }
        // Get screen width
        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels

        // Set a random Y position for the bug
        val randomY = Random.nextInt(0, screenHeight - bug.layoutParams.height)
        bug.y = randomY.toFloat()

        // Animate the bug from right to left
        val animator = ObjectAnimator.ofFloat(bug, "translationX", screenWidth.toFloat(), -screenWidth.toFloat())
        animator.duration = 5000L // Duration in milliseconds (3 seconds)
        animator.start()
    }
}

