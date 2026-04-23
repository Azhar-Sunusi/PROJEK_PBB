package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

@Composable
fun LoginUserScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E3D0))
    ) {

        // TITLE
        Text(
            text = "INKFINITY",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        // CIRCLE BACKGROUND
        Box(
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.TopCenter)
                .padding(top = 200.dp)
                .background(Color(0xFFD5BFA6), shape = CircleShape)
        )

        // LOGO KUCING
        Image(
            painter = painterResource(id = R.drawable.logo_kucing),
            contentDescription = "Logo Kucing",
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopCenter)
                .padding(top = 220.dp),
            contentScale = ContentScale.Fit
        )

        // BUTTON LOGIN
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
                .align(Alignment.TopCenter)
                .padding(top = 500.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81A6C6),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Log In",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // BUTTON SIGN UP
        Button(
            onClick = onSignUpClick,
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
                .align(Alignment.TopCenter)
                .padding(top = 580.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81A6C6),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}