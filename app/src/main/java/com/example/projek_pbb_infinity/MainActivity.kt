package com.example.projek_pbb_infinity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.projek_pbb_infinity.ui.screen.LandingScreen
import com.example.projek_pbb_infinity.ui.screen.UserLoginFormScreen
import com.example.projek_pbb_infinity.ui.screen.UserSignUpFormScreen
import com.example.projek_pbb_infinity.ui.theme.ProjekPbbInfinityTheme

enum class Screen {
    LANDING,
    LOGIN_FORM,
    SIGN_UP_FORM
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjekPbbInfinityTheme {
                var currentScreen by remember { mutableStateOf(Screen.LANDING) }

                when (currentScreen) {
                    Screen.LANDING -> {
                        LandingScreen(
                            onLoginClick = { currentScreen = Screen.LOGIN_FORM },
                            onSignUpClick = { currentScreen = Screen.SIGN_UP_FORM }
                        )
                    }

                    Screen.LOGIN_FORM -> {
                        UserLoginFormScreen(
                            onBackClick = { currentScreen = Screen.LANDING },
                            onLoginSubmit = { email, password ->
                                // TODO: sambungkan ke Firebase Auth
                            }
                        )
                    }

                    Screen.SIGN_UP_FORM -> {
                        UserSignUpFormScreen(
                            onBackClick = { currentScreen = Screen.LANDING },
                            onNextClick = { name, phone, email, password ->
                                // TODO: lanjut ke step berikutnya / register Firebase
                            }
                        )
                    }
                }
            }
        }
    }
}