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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

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
            Spacer(modifier = Modifier.height(58.dp))

            Text(
                text = "WELCOME!!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(34.dp))

            SignUpField(name, { name = it }, "Name")

            Spacer(modifier = Modifier.height(18.dp))

            SignUpField(phone, { phone = it }, "Phone number", KeyboardType.Phone)

            Spacer(modifier = Modifier.height(18.dp))

            SignUpField(email, { email = it }, "Email", KeyboardType.Email)

            Spacer(modifier = Modifier.height(18.dp))

            SignUpField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { onNextClick(name, phone, email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8EB0CF),
                    contentColor = Color.White
                )
            ) {
                Text("Next", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(72.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
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
            .height(64.dp),
        textStyle = TextStyle(fontSize = 17.sp, color = Color(0xFF4A4A4A)),
        placeholder = {
            Text(placeholder, fontSize = 17.sp, color = Color(0xFF5A5A5A))
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF7F7F7),
            unfocusedContainerColor = Color(0xFFF7F7F7),
            focusedBorderColor = Color(0xFFB8B8B8),
            unfocusedBorderColor = Color(0xFFB8B8B8),
            cursorColor = Color.Black
        )
    )
}