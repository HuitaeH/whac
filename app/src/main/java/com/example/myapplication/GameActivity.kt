package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import kotlin.random.Random
import android.os.Handler
import android.os.Looper
import android.widget.RelativeLayout
import android.widget.TextView


sealed class GameCondition(val description: String, val checkNumber: (Int) -> Boolean) {
    object Even : GameCondition("짝수만 잡아보세요!", { it % 2 == 0 })
    object RemainderOf7is3 : GameCondition("7로 나눈 나머지가 3인 수를 잡아보세요!", { it % 7 == 3 })
}

class GameActivity : AppCompatActivity() {
    private val conditions = listOf(
        GameCondition.Even,
        GameCondition.RemainderOf7is3
    )

    private lateinit var currentCondition: GameCondition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        // 랜덤으로 조건 선택
        currentCondition = conditions.random()

        // 조건 텍스트뷰 설정
        val conditionText = TextView(this).apply {
            text = currentCondition.description
            textSize = 24f
            setTextColor(Color.BLACK)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                addRule(RelativeLayout.ALIGN_PARENT_TOP)
                addRule(RelativeLayout.CENTER_HORIZONTAL)
                topMargin = 50
            }
        }
        findViewById<RelativeLayout>(R.id.game_layout).addView(conditionText)

        fun createAndAnimateBug() {
            val randomNumber = Random.nextInt(100, 201)

            // 벌레와 숫자를 포함할 컨테이너 레이아웃
            val container = RelativeLayout(this).apply {
                val dpSize = 100  // 컨테이너 크기
                val pixels = (dpSize * resources.displayMetrics.density).toInt()
                layoutParams = RelativeLayout.LayoutParams(pixels, pixels)
            }

            // 벌레 이미지뷰
            val bug = ImageView(this).apply {
                val dpSize = 82
                val pixels = (dpSize * resources.displayMetrics.density).toInt()
                layoutParams = RelativeLayout.LayoutParams(pixels, pixels).apply {
                    addRule(RelativeLayout.CENTER_IN_PARENT)
                }
            }

            // 숫자 텍스트뷰
            val numberText = TextView(this).apply {
                text = randomNumber.toString()
                textSize = 20f
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.WHITE)
                setPadding(6, 5, 6, 5)
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.CENTER_HORIZONTAL)
                    addRule(RelativeLayout.ALIGN_PARENT_TOP)
                }
            }

            // 벌레 이미지 설정
            val bugType = Random.nextInt(5)
            bug.setImageResource(when (bugType) {
                0 -> R.drawable.ant
                1 -> R.drawable.grasshopper
                2 -> R.drawable.worm
                3 -> R.drawable.snail
                else -> R.drawable.frog
            })

            // 컨테이너에 벌레와 숫자 추가
            container.addView(bug)
            container.addView(numberText)

            // 클릭 이벤트 설정
            container.setOnClickListener {
                if (currentCondition.checkNumber(randomNumber)) {
                    // 정답일 때
                    container.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .withEndAction {
                            findViewById<RelativeLayout>(R.id.game_layout).removeView(container)
                        }
                } else {
                    // 오답일 때의 처리
                }
            }

            // 메인 레이아웃에 컨테이너 추가
            val layout = findViewById<RelativeLayout>(R.id.game_layout)
            layout.addView(container)

            // 화면 크기 얻기
            val screenWidth = resources.displayMetrics.widthPixels
            val screenHeight = resources.displayMetrics.heightPixels

            // Y 위치 설정
            val fixedY = (screenHeight * 0.68).toInt()
            container.y = fixedY.toFloat()

            // 애니메이션
            val animator = ObjectAnimator.ofFloat(
                container,
                "translationX",
                screenWidth.toFloat()*2,
                -screenWidth.toFloat()
            ).apply {
                duration = 15000L
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        layout.removeView(container)
                    }
                })
            }
            animator.start()

            // 다음 벌레 생성
            Handler(Looper.getMainLooper()).postDelayed({
                createAndAnimateBug()
            }, Random.nextLong(500, 2000))
        }

        // 시작
        createAndAnimateBug()
    }
}
