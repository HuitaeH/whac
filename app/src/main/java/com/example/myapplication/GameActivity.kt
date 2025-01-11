package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast



class GameActivity : AppCompatActivity() {

    private lateinit var moleGrid: LinearLayout
    private val gridSize = 4 // Size of the grid (4x4 grid)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        moleGrid = findViewById(R.id.moleGrid) // Find the LinearLayout for the grid
        setupTemporaryGrid()
    }

    // Set up a temporary grid using buttons as placeholders

    // Set up a temporary grid using buttons as placeholders
    private fun setupTemporaryGrid() {
        val gridLayout = LinearLayout(this)
        gridLayout.orientation = LinearLayout.VERTICAL

        val firstGridNum = (1..25).toList().shuffled() // Create a shuffled list from 1 to 25
        val secondGridNum = (26..50).toList().shuffled() // Create the shuffled list from 26 to 50

        println(firstGridNum) // Print the shuffled list to verify the result

        for (i in 0 until 5) {
            val rowLayout = LinearLayout(this)
            rowLayout.orientation = LinearLayout.HORIZONTAL

            for (j in 0 until 5) {
                val index = i * gridSize + j // Calculate the 1D index for the shuffled list
                val button = Button(this)
                button.text = "${firstGridNum[index]}" // Initial button text

                // Set click listener to handle the button click
                button.setOnClickListener { moleClicked(i, j, button, index, secondGridNum, isFromSecondGrid = false) }
                rowLayout.addView(button)
            }

            gridLayout.addView(rowLayout)
        }

        moleGrid.addView(gridLayout)
    }

    // Handle the click event on a button (mole hole)
    private fun moleClicked(i: Int, j: Int, oldButton: Button, index: Int, secondGridNum: List<Int>, isFromSecondGrid: Boolean) {
        val rowLayout = oldButton.parent as LinearLayout // Get the row layout
        rowLayout.removeView(oldButton) // Remove the old button

        // If the button is from the second grid, remove it completely
        if (isFromSecondGrid) {
            Toast.makeText(this, "You removed hole $index", Toast.LENGTH_SHORT).show()
            return // Don't regenerate the button if it's from the second grid
        }

        // Otherwise, replace the button with a new one from the second grid
        val newButton = Button(this)
        newButton.text = "${secondGridNum[index]}" // Set new button text from the second list
        newButton.setOnClickListener { moleClicked(i, j, newButton, index, secondGridNum, isFromSecondGrid = true) } // Set the click listener for the second grid
        rowLayout.addView(newButton) // Add the new button to the row
        Toast.makeText(this, "You dug hole $index", Toast.LENGTH_SHORT).show()
    }
}
