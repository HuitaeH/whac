package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility





private const val TAG = "MainActivity"


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

        KakaoSdk.init(
            this,
            "29bf44d6e98091ea7f4a4282acf857ed"
        ) // Replace with your actual Kakao app key

        // Get and log the key hash
        val keyHash = Utility.getKeyHash(this)
        Log.d(TAG, "Key Hash: $keyHash") // Log the key hash for verification


        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }

        findViewById<Button>(R.id.startButton).setOnClickListener {
            // Show dialog on successful login
            val dialog = SelectDialog()
            dialog.show(supportFragmentManager, "SelectDialog")
        }

//        findViewById<Button>(R.id.startButton).setOnClickListener {
//            // Check if KakaoTalk is installed and login accordingly
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
//                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
//                    if (error != null) {
//                        Log.e(TAG, "카카오톡으로 로그인 실패", error)
//
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            return@loginWithKakaoTalk
//                        }
//
//                        // Attempt login with Kakao account
//                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
//                    } else if (token != null) {
//                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
//
//                        // Show dialog on successful login
//                        val dialog = SelectDialog()
//                        dialog.show(supportFragmentManager, "SelectDialog")
//                    }
//                }
//            } else {
//                // Attempt login with Kakao account directly
//                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
//            }
//        }

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



