package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
fun LandingScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E3D0))
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            // TITLE
            Text(
                text = "INKFINITY",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // LOGO UTAMA
            Box(
                modifier = Modifier.size(260.dp),
                contentAlignment = Alignment.Center
            ) {

                // BULATAN
                Box(
                    modifier = Modifier
                        .size(230.dp)
                        .background(
                            color = Color(0xFFD5BFA6),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo_kucing4),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize(0.72f),
                        contentScale = ContentScale.Fit
                    )
                }

                // KUCING KANAN (BESAR)
                Image(
                    painter = painterResource(R.drawable.kucing_1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-30).dp, y = 42.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // LOGIN
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .width(165.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Log In",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SIGN UP
            Button(
                onClick = onSignUpClick,
                modifier = Modifier
                    .width(165.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Sign Up",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // 2 KUCING BAWAH
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // KUCING KIRI
                Image(
                    painter = painterResource(R.drawable.kucing_2),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )

                // KUCING KANAN
                Image(
                    painter = painterResource(R.drawable.kucing_3),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }
        }
    }
}