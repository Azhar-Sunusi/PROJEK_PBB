package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

private val Cream = Color(0xFFF3E3D0)
private val HeaderBlue = Color(0xFF8DB4D1)
private val LightBlue = Color(0xFFAED3DF)
private val DarkGray = Color(0xFF8B949C)
private val TextGray = Color(0xFF707070)

@Composable
fun AdminHomeScreen(
    onLogoutClick: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AdminHeader()

            SalesSummary()

            AdminMenuCard()

            Spacer(modifier = Modifier.weight(1f))

            AdminBottomBar(
                onMenuClick = {
                    showMenu = !showMenu
                }
            )
        }

        AdminLogoutDropdown(
            showMenu = showMenu,
            onLogoutClick = {
                showMenu = false
                onLogoutClick()
            }
        )
    }
}

@Composable
private fun AdminHeader() {
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
                .background(Color(0xFFB8DAE5), CircleShape),
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
                text = "Hai..!",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Azhar",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Admin",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun SalesSummary() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .border(
                width = 4.dp,
                color = LightBlue
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Total Penjualan",
                color = TextGray,
                fontSize = 18.sp
            )

            Text(
                text = "April 2026",
                color = TextGray,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Rp  0",
            color = TextGray,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun AdminMenuCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(430.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Hai, Admin 👋",
            color = TextGray,
            fontSize = 14.sp
        )

        Text(
            text = "Kelola bisnis percetakanmu dengan lebih mudah",
            color = TextGray,
            fontSize = 14.sp
        )

        Text(
            text = "dan cepat",
            color = TextGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AdminMenuItem(
                iconText = "📋",
                label = "Pesanan"
            )

            AdminMenuItem(
                iconText = "🖨️",
                label = "Produk"
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AdminMenuItem(
                iconText = "👔",
                label = "Pegawai"
            )

            AdminMenuItem(
                iconText = "⚙️",
                label = "Pengaturan"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(DarkGray, RoundedCornerShape(10.dp))
                .clickable {
                    // nanti bisa diarahkan ke halaman riwayat transaksi admin
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Riwayat Transaksi",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AdminMenuItem(
    iconText: String,
    label: String
) {
    Box(
        modifier = Modifier
            .size(94.dp)
            .border(
                width = 4.dp,
                color = LightBlue,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                // nanti bisa diberi navigasi sesuai menu
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = iconText,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun AdminBottomBar(
    onMenuClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.Center)
                .background(Color(0xFFB8DAE5), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.tombol_home),
                contentDescription = null,
                modifier = Modifier.size(38.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-12).dp)
                .background(Color(0xFFB8DAE5), RoundedCornerShape(8.dp))
                .clickable {
                    onMenuClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.tombol_riwayat_transaksi),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun AdminLogoutDropdown(
    showMenu: Boolean,
    onLogoutClick: () -> Unit
) {
    if (showMenu) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 18.dp, bottom = 78.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                modifier = Modifier
                    .width(210.dp)
                    .height(86.dp)
                    .background(Color.White, RoundedCornerShape(28.dp))
                    .clickable {
                        onLogoutClick()
                    }
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier.size(38.dp)
                )

                Spacer(modifier = Modifier.width(18.dp))

                Text(
                    text = "Log out",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
    }
}