
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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserSignUpFormScreen(
    onBackClick: () -> Unit,
    onNextClick: (String, String, String, String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
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
                .padding(horizontal = 20.dp),
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            // FIELD NAME
            SignUpField(name, { name = it }, "Name")

            Spacer(modifier = Modifier.height(14.dp))

            // FIELD PHONE
            SignUpField(phone, { phone = it }, "Phone number", KeyboardType.Phone)

            Spacer(modifier = Modifier.height(14.dp))

            // FIELD EMAIL
            SignUpField(email, { email = it }, "Email", KeyboardType.Email)

            Spacer(modifier = Modifier.height(14.dp))

            // FIELD PASSWORD
            SignUpField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            // BUTTON NEXT
            Button(
                onClick = {
                    onNextClick(name, phone, email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
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
private fun SignUpField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
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
                color = Color(0xFF5A5A5A)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE9E9E9),
            unfocusedContainerColor = Color(0xFFE9E9E9),
            focusedBorderColor = Color(0xFFB8B8B8),
            unfocusedBorderColor = Color(0xFFB8B8B8),
            cursorColor = Color.Black
        )
    )

}