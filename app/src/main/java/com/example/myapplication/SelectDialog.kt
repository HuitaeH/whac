package com.example.myapplication
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment


class SelectDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.select_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.singleButton).setOnClickListener {
            val intent = Intent(requireContext(), GameActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        view.findViewById<Button>(R.id.doubleButton).setOnClickListener {
            val intent = Intent(requireContext(), FriendListActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val displayMetrics = resources.displayMetrics
            val width = (displayMetrics.widthPixels * 0.7).toInt()
            val height = (displayMetrics.heightPixels * 0.6).toInt()

            setLayout(width, height)

            setBackgroundDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.dialog_background,
                    null
                )
            )
        }
    }
}

