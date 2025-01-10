package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GameStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start)

        val centerCharacter = findViewById<ImageView>(R.id.centerCharacter)
        val plusCharacter = findViewById<ImageView>(R.id.plusCharacter)
        val divideCharacter = findViewById<ImageView>(R.id.divideCharacter)
        val exclamationCharacter = findViewById<ImageView>(R.id.exclamationCharacter)

        centerCharacter.visibility = View.INVISIBLE
        plusCharacter.visibility = View.INVISIBLE
        divideCharacter.visibility = View.INVISIBLE
        exclamationCharacter.visibility = View.INVISIBLE

        // 애니메이션 시작
        Handler(Looper.getMainLooper()).postDelayed({
            popUpAnimation(centerCharacter, 0)
            popUpAnimation(plusCharacter, 200)
            popUpAnimation(divideCharacter, 400)
            popUpAnimation(exclamationCharacter, 600)
        }, 500) // 시작 딜레이

        findViewById<Button>(R.id.startButton).setOnClickListener {
            // Start your game activity
            startActivity(Intent(this, GameActivity::class.java))
        }
    }

    private fun popUpAnimation(view: View, delay: Long) {
        view.visibility = View.VISIBLE

        // XML에 지정된 위치에서 100dp 아래에서 시작
        val startTranslationY = 100f
        view.translationY = startTranslationY

        // 원래 위치(0)로 돌아오기
        ObjectAnimator.ofFloat(view, "translationY", startTranslationY, 0f).apply {
            duration = 500
            startDelay = delay
            interpolator = OvershootInterpolator(1.5f)
            start()
        }
    }

}