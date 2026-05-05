package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PembayaranBerhasilScreen(
    serviceTitle: String,
    totalPayment: Int,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    userName: String
) {
    var showMenu by remember { mutableStateOf(false) }

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
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "~Status Transaksi~",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFF1598C7), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "PEMBAYARAN BERHASIL!!!",
                color = Color(0xFF4CAF50),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Box(
                modifier = Modifier
                    .width(230.dp)
                    .height(130.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White, RoundedCornerShape(14.dp))
                    .padding(18.dp)
            ) {
                Text(
                    text = """
ID Transaksi:#TRX-202504123
Waktu: 24 April 2026, 13.30 WIB
Produk: $serviceTitle
Pembayaran: Qris
Total Pembayaran: ${formatRupiah(totalPayment)}
                    """.trimIndent(),
                    fontSize = 13.sp,
                    color = Color.Black,
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