package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R
import com.example.projek_pbb_infinity.ui.component.BottomMenuDropdown

private val Cream = Color(0xFFF3E3D0)
private val Blue = Color(0xFF8EB0CF)
private val LightBlue = Color(0xFFAED3DF)
private val HeaderBlue = Color(0xFF8DB4D1)

@Composable
fun BerandaUserScreen(
    userName: String,
    onNextClick: (ServiceDetail) -> Unit,
    onLogoutClick: () -> Unit
) {
    var selectedService by remember { mutableStateOf<ServiceDetail?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 🔥 HEADER
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
                    Text("Hai..!", color = Color.White, fontSize = 17.sp)
                    Text(userName, color = Color.White, fontSize = 17.sp)
                }

                Spacer(modifier = Modifier.weight(1f))
                Text("User", color = Color.White, fontSize = 17.sp)
            }

            // 🔥 TITLE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("~BERANDA~", fontSize = 22.sp, color = Color.Black)
            }

            // 🔥 ADD FILE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 22.dp, end = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .width(260.dp)
                        .height(56.dp)
                        .background(Blue, RoundedCornerShape(8.dp))
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.print),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.width(18.dp))

                    Text("Add File Here!", color = Color.White, fontSize = 17.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(R.drawable.add_file_here),
                    contentDescription = "Lanjut",
                    modifier = Modifier
                        .size(72.dp)
                        .offset(x = 6.dp)
                        .clickable {
                            selectedService?.let { onNextClick(it) }
                        }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            ServiceItem(ServiceDetail("Brosur A4", "RP 15.000", R.drawable.brosur_a4),
                selectedService?.title == "Brosur A4") { selectedService = it }

            ServiceItem(ServiceDetail("ID CARD", "RP 10.000", R.drawable.id_card),
                selectedService?.title == "ID CARD") { selectedService = it }

            ServiceItem(ServiceDetail("Fotocopy", "RP 2.000", R.drawable.fotocopy),
                selectedService?.title == "Fotocopy") { selectedService = it }

            ServiceItem(ServiceDetail("Print", "RP 5.000", R.drawable.print),
                selectedService?.title == "Print") { selectedService = it }

            Spacer(modifier = Modifier.weight(1f))

            // 🔥 BOTTOM BAR
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.White)
            ) {

                if (selectedService != null) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .align(Alignment.CenterStart)
                            .offset(x = 12.dp)
                            .background(Color(0xFFB8DAE5), CircleShape)
                            .clickable { selectedService = null },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.add_file_here2),
                            contentDescription = "Reset",
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }

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
                        modifier = Modifier.size(36.dp)
                    )
                }

                // 🔥 GARIS 3 (CLICKABLE)
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = (-12).dp)
                        .background(Color(0xFFB8DAE5), RoundedCornerShape(8.dp))
                        .clickable { showMenu = !showMenu },
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

        // 🔥 DROPDOWN MENU
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

// 🔥 ITEM SERVICE
@Composable
private fun ServiceItem(
    service: ServiceDetail,
    selected: Boolean,
    onClick: (ServiceDetail) -> Unit
) {
    val bgColor = if (selected) Blue else LightBlue
    val textColor = if (selected) Color.Black else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 6.dp)
            .height(68.dp)
            .background(bgColor, RoundedCornerShape(17.dp))
            .clickable { onClick(service) }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        ) {
            Text(service.title, color = textColor, fontSize = 15.sp)
            Text(service.price + "/lembar", color = textColor, fontSize = 15.sp)
        }

        Image(
            painter = painterResource(service.icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 18.dp)
                .size(40.dp)
        )
    }
}