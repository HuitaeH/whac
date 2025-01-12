package com.example.myapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import kotlin.random.Random
import android.os.Handler
import android.os.Looper
import android.widget.RelativeLayout

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        // Function to create and animate a bug
        fun createAndAnimateBug() {
            // Create ImageView and set its size
            val bug = ImageView(this).apply {
                val dpSize = 80  // size in dp
                val pixels = (dpSize * resources.displayMetrics.density).toInt()
                layoutParams = RelativeLayout.LayoutParams(pixels, pixels)
            }

            // Set random bug image
            val bugType = Random.nextInt(5)
            bug.setImageResource(when (bugType) {
                0 -> R.drawable.ant
                1 -> R.drawable.grasshopper
                2 -> R.drawable.worm
                3 -> R.drawable.snail
                else -> R.drawable.frog
            })

            // Add the bug to the layout
            val layout = findViewById<RelativeLayout>(R.id.game_layout)
            layout.addView(bug)

            // Get screen dimensions
            val screenWidth = resources.displayMetrics.widthPixels
            val screenHeight = resources.displayMetrics.heightPixels

            // Set Y position
            val fixedY = (screenHeight * 0.70).toInt()
            bug.y = fixedY.toFloat()

            // Animate
            val animator = ObjectAnimator.ofFloat(bug, "translationX", screenWidth.toFloat()*2, -screenWidth.toFloat())
            animator.duration = 13000L
            animator.start()

            // Create next bug after delay
            Handler(Looper.getMainLooper()).postDelayed({
                createAndAnimateBug()
            }, Random.nextLong(500, 2000))
        }

        // Start creating bugs
        createAndAnimateBug()
    }
}
