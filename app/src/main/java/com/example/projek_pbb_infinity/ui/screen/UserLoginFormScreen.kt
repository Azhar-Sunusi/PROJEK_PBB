package com.example.projek_pbb_infinity.ui.screen

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(70.dp))

            // TITLE
            Text(
                text = "WELCOME!!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Login",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(68.dp))

            // EMAIL
            LoginField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Name/Email",
                isPassword = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            // PASSWORD
            LoginField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(60.dp))

            // LOGIN BUTTON
            Button(
                onClick = { onLoginSubmit(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Log In",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // BACK
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
            .height(56.dp),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color(0xFF4A4A4A)
        ),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp,
                color = Color(0xFF5A5A5A)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE9E9E9),
            unfocusedContainerColor = Color(0xFFE9E9E9),
            focusedBorderColor = Color(0xFFB8B8B8),
            unfocusedBorderColor = Color(0xFFB8B8B8),
            focusedTextColor = Color(0xFF4A4A4A),
            unfocusedTextColor = Color(0xFF4A4A4A),
            cursorColor = Color.Black
        )
    )
}