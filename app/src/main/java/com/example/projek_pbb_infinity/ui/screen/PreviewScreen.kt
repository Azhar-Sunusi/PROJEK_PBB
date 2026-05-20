package com.example.projek_pbb_infinity.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PreviewScreen(
    fileUrl: String
) {

    val context = LocalContext.current

    val isImage =
        fileUrl.contains(".jpg") ||
                fileUrl.contains(".jpeg") ||
                fileUrl.contains(".png")

    val isPdf =
        fileUrl.contains(".pdf")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Preview File"
        )

        Spacer(modifier = Modifier.height(20.dp))

        when {

            isImage -> {

                AsyncImage(
                    model = fileUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                )
            }

            isPdf -> {

                Button(
                    onClick = {

                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(fileUrl)
                        )

                        context.startActivity(intent)
                    }
                ) {
                    Text("Buka PDF")
                }
            }

            else -> {

                Text("Preview tidak tersedia")
            }
        }
    }
}