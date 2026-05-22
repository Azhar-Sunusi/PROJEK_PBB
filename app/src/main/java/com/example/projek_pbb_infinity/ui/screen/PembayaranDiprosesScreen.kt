package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R
import com.example.projek_pbb_infinity.ui.component.BottomMenuDropdown
import kotlinx.coroutines.delay

private val Cream = Color(0xFFF3E3D0)
private val HeaderBlue = Color(0xFF8DB4D1)

@Composable
fun PembayaranDiprosesScreen(
    serviceTitle: String,
    userName: String,
    totalPayment: Int,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSelesaiClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    var showMenu by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        onSelesaiClick()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .background(HeaderBlue)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            Color(0xFFB8DAE5),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.profil_kiri_atas),
                        contentDescription = null,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        "Hai..!",
                        color = Color.White,
                        fontSize = 17.sp
                    )

                    Text(
                        userName,
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "User",
                    color = Color.White,
                    fontSize = 17.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    "~Status Transaksi~",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            CircularProgressIndicator(
                color = Color(0xFF8EB0CF),
                strokeWidth = 6.dp,
                modifier = Modifier
                    .size(58.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "SEDANG DIPROSES",
                color = Color(0xFF4CAF50),
                fontSize = 26.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(26.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(18.dp)
                    )
                    .padding(18.dp)
            ) {

                Column {

                    Text(
                        "ID Transaksi: #TRX-${
                            System.currentTimeMillis()
                                .toString()
                                .takeLast(8)
                        }"
                    )

                    Text(
                        "Waktu: 24 April 2026, 13.30 WIB"
                    )

                    Text(
                        "Produk: $serviceTitle"
                    )

                    Text(
                        "Total Pembayaran: Rp $totalPayment"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.White)
            ) {

                // BACK
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterStart)
                        .offset(x = 12.dp)
                        .background(
                            Color(0xFFB8DAE5),
                            CircleShape
                        )
                        .clickable {
                            onBackClick()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.add_file_here2),
                        contentDescription = "Back",
                        modifier = Modifier.size(34.dp)
                    )
                }

                // HOME
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.Center)
                        .background(
                            Color(0xFFB8DAE5),
                            CircleShape
                        )
                        .clickable {
                            onHomeClick()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.tombol_home),
                        contentDescription = "Home",
                        modifier = Modifier.size(36.dp)
                    )
                }

                // MENU GARIS 3
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = (-12).dp)
                        .background(
                            Color(0xFFB8DAE5),
                            RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            showMenu = !showMenu
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.tombol_riwayat_transaksi),
                        contentDescription = "Menu",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        BottomMenuDropdown(
            showMenu = showMenu,
            onRiwayatClick = {
                showMenu = false
            },
            onLogoutClick = {
                showMenu = false
                onLogoutClick()
            }
        )
    }
}