package com.example.myapplication
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class SelectDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Game Mode")
            .setMessage("Select your game mode!")
            .setPositiveButton("Single") { dialog: DialogInterface?, which: Int ->
                // Launch GameActivity for single-player mode
                val intent = Intent(requireContext(), GameActivity::class.java)
                startActivity(intent)
                dialog?.dismiss() // Optionally dismiss the dialog
            }
            .setNegativeButton("Double") { dialog: DialogInterface?, which: Int ->
                // Launch FriendListActivity for double-player mode
                val intent = Intent(requireContext(), FriendListActivity::class.java)
                startActivity(intent)
                dialog?.dismiss() // Optionally dismiss the dialog
            }
            .create()
    }
}