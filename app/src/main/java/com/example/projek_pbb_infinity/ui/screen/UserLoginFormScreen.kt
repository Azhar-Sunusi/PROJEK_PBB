package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

@Composable
fun UserLoginFormScreen(
    onBackClick: () -> Unit,
    onLoginSubmit: (String, String) -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E3D0))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(72.dp))

            // TITLE
            Text(
                text = "WELCOME!!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Login",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(52.dp))

            // KUCING + EMAIL
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(R.drawable.kucing_4),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.TopStart)
                        .offset(x = (-8).dp, y = (-62).dp),
                    contentScale = ContentScale.Fit
                )

                LoginField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Name/Email",
                    isPassword = false
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            // PASSWORD
            LoginField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(50.dp))

            // BUTTON LOGIN
            Button(
                onClick = { onLoginSubmit(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Log In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // 2 KUCING BAWAH
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 34.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.kucing_2),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )

                Image(
                    painter = painterResource(R.drawable.kucing_3),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onBackClick) {

                Text(
                    text = "Kembali",
                    color = Color(0xFF8EB0CF),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun LoginField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color(0xFF4A4A4A)
        ),
        placeholder = {

            Text(
                text = placeholder,
                fontSize = 16.sp,
                color = Color(0xFF5A5A5A)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) {
                KeyboardType.Password
            } else {
                KeyboardType.Email
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFEFEFEF),
            unfocusedContainerColor = Color(0xFFEFEFEF),
            focusedBorderColor = Color(0xFFB8B8B8),
            unfocusedBorderColor = Color(0xFFB8B8B8),
            focusedTextColor = Color(0xFF4A4A4A),
            unfocusedTextColor = Color(0xFF4A4A4A),
            cursorColor = Color.Black
        )
    )
}