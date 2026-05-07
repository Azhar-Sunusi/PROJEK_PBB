package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

private val Cream = Color(0xFFF3E3D0)
private val HeaderBlue = Color(0xFF8DB4D1)
private val LightBlue = Color(0xFFAED3DF)
private val TextGray = Color(0xFF707070)
private val StatusGold = Color(0xFFD39F3E)

data class AdminOrder(
    val id: String,
    val date: String,
    val status: String,
    val amount: String
)

@Composable
fun AdminPesananScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit = onBackClick,
    onLogoutClick: () -> Unit = {}
) {
    var showMenu by remember { mutableStateOf(false) }

    val orders = listOf(
        AdminOrder("ID# 00001", "11 April 2026", "Berlangsung", "Rp 1.000.000"),
        AdminOrder("ID# 00002", "11 April 2026", "Berlangsung", "Rp 2.100.000"),
        AdminOrder("ID# 00003", "11 April 2026", "Berlangsung", "Rp 780.000"),
        AdminOrder("ID# 00004", "11 April 2026", "Berlangsung", "Rp 760.000"),
        AdminOrder("ID# 00005", "11 April 2026", "Berlangsung", "Rp 5.000.000"),
        AdminOrder("ID# 00006", "11 April 2026", "Berlangsung", "Rp 10.000.000")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AdminPesananHeader()

            AdminPesananSalesSummary()

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp)
            ) {
                orders.forEachIndexed { index, order ->
                    AdminOrderItem(order = order)

                    if (index != orders.lastIndex) {
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            AdminPesananBottomBar(
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = {
                    showMenu = !showMenu
                }
            )
        }

        AdminPesananLogoutDropdown(
            showMenu = showMenu,
            onLogoutClick = {
                showMenu = false
                onLogoutClick()
            }
        )
    }
}

@Composable
private fun AdminPesananHeader() {
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
private fun AdminPesananSalesSummary() {
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
            text = "Rp  54.340.000",
            color = TextGray,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun AdminOrderItem(
    order: AdminOrder
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = order.id,
                color = TextGray,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = order.date,
                color = TextGray,
                fontSize = 11.sp
            )
        }

        Box(
            modifier = Modifier
                .width(118.dp)
                .height(22.dp)
                .align(Alignment.Center)
                .background(StatusGold, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = order.status,
                color = Color.White,
                fontSize = 12.sp
            )
        }

        Text(
            text = order.amount,
            color = TextGray,
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun AdminPesananBottomBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
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
                .align(Alignment.CenterStart)
                .offset(x = 6.dp)
                .background(Color(0xFFB8DAE5), CircleShape)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(34.dp)
            ) {
                val path = Path().apply {
                    moveTo(size.width * 0.18f, size.height * 0.50f)
                    lineTo(size.width * 0.78f, size.height * 0.16f)
                    lineTo(size.width * 0.78f, size.height * 0.84f)
                    close()
                }

                drawPath(
                    path = path,
                    color = Color.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.Center)
                .background(Color(0xFFB8DAE5), CircleShape)
                .clickable { onHomeClick() },
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
                .offset(x = (-14).dp)
                .background(Color(0xFFB8DAE5), RoundedCornerShape(8.dp))
                .clickable { onMenuClick() },
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
private fun AdminPesananLogoutDropdown(
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
                    .clickable { onLogoutClick() }
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