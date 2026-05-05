package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R
import kotlinx.coroutines.delay

@Composable
fun QrisBarcodeScreen(
    onBackClick: () -> Unit,
    onCheckStatusClick: () -> Unit,
    onLogoutClick: () -> Unit,
    userName: String
) {
    var remainingSeconds by remember { mutableStateOf(23 * 60 + 59) }
    var showMenu by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (remainingSeconds > 0) {
            delay(1000)
            remainingSeconds--
        }
    }

    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timeText = "%02d:%02d".format(minutes, seconds)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E3D0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderPayment(userName = userName)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Halaman Instruksi pembayaran",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .width(190.dp)
                    .height(38.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFFFFE8C8), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "waktu tersisa :$timeText",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Image(
                painter = painterResource(R.drawable.qris_barcode),
                contentDescription = "QRIS Barcode",
                modifier = Modifier
                    .size(265.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(22.dp))

            Box(
                modifier = Modifier
                    .width(230.dp)
                    .height(54.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFF8EB0CF), RoundedCornerShape(8.dp))
                    .clickable { onCheckStatusClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cek Status Pembayaran",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BottomPaymentBarWithMenu(
                onBackClick = onBackClick,
                onMenuClick = { showMenu = !showMenu }
            )
        }

        BottomDropdownMenu(
            showMenu = showMenu,
            onLogoutClick = {
                showMenu = false
                onLogoutClick()
            }
        )
    }
}