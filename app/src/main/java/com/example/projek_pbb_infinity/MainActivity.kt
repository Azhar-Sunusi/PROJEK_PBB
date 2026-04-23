package com.example.projek_pbb_infinity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projek_pbb_infinity.ui.screen.LoginUserScreen
import com.example.projek_pbb_infinity.ui.theme.ProjekPbbInfinityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjekPbbInfinityTheme {
                LoginUserScreen(
                    onLoginClick = {},
                    onSignUpClick = {}
                )
            }
        }
    }
}