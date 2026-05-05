package com.example.projek_pbb_infinity.ui.screen

import com.example.projek_pbb_infinity.ui.component.BottomMenuDropdown
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R


private val CreamDetail = Color(0xFFF3E3D0)
private val BlueDetail = Color(0xFF8EB0CF)
private val LightBlueDetail = Color(0xFFAED3DF)
private val HeaderBlueDetail = Color(0xFF8DB4D1)
private val CardWhite = Color(0xFFFFFAF8)

data class ServiceDetail(
    val title: String,
    val price: String,
    val icon: Int
)

@Composable
fun DetailPesananScreen(
    service: ServiceDetail,
    userName: String,
    onBackClick: () -> Unit,
    onContinueClick: (Int) -> Unit,
    onLogoutClick: () -> Unit
) {
    var qty by remember { mutableStateOf(1) }
    var isDropdownOpen by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("FULL COLOR 2 SISI") }
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamDetail)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderDetail(userName = userName)

            Spacer(modifier = Modifier.height(26.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(136.dp)
                        .background(CardWhite, RoundedCornerShape(18.dp))
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(id = getDetailIcon(service.title)),
                        contentDescription = null,
                        modifier = Modifier
                            .size(if (service.title == "Brosur A4") 130.dp else 74.dp)
                            .align(Alignment.TopStart)
                            .offset(
                                x = if (service.title == "Brosur A4") -10.dp else 10.dp,
                                y = if (service.title == "Brosur A4") -10.dp else 10.dp
                            ),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 98.dp, y = 30.dp)
                    ) {
                        Text(
                            text = service.title.uppercase(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = service.price,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF78A4D6)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(240.dp)
                            .height(30.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = (-12).dp)
                            .background(LightBlueDetail, RoundedCornerShape(12.dp))
                            .clickable { isDropdownOpen = !isDropdownOpen }
                    ) {
                        Text(
                            text = selectedOption,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 14.dp)
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 12.dp)
                                .size(22.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isDropdownOpen) "⌃" else "⌄",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 30.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.65f))
                    )

                    Text(
                        text = "Jumlah Pemesanan",
                        modifier = Modifier.padding(horizontal = 14.dp),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.65f))
                    )
                }

                if (isDropdownOpen) {
                    Box(
                        modifier = Modifier
                            .width(240.dp)
                            .height(58.dp)
                            .align(Alignment.TopCenter)
                            .offset(y = 126.dp)
                            .background(LightBlueDetail, RoundedCornerShape(10.dp))
                            .padding(start = 14.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(
                                text = "FULL COLOR 2 SISI",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.clickable {
                                    selectedOption = "FULL COLOR 2 SISI"
                                    isDropdownOpen = false
                                }
                            )

                            Spacer(modifier = Modifier.height(7.dp))

                            Text(
                                text = "HITAM PUTIH 2 SISI",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.clickable {
                                    selectedOption = "HITAM PUTIH 2 SISI"
                                    isDropdownOpen = false
                                }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(108.dp)
                    .background(CardWhite, RoundedCornerShape(18.dp))
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp)
                        .height(36.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    QtyButton("+") { qty++ }

                    Box(
                        modifier = Modifier
                            .width(74.dp)
                            .height(36.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = qty.toString(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    QtyButton("−") {
                        if (qty > 1) qty--
                    }
                }

                Box(
                    modifier = Modifier
                        .width(136.dp)
                        .height(34.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (-10).dp)
                        .background(Color(0xFFD3E5F2), RoundedCornerShape(6.dp))
                        .clickable { qty = 1 },
                    contentAlignment = Alignment.Center
                ) {
                    Text("BATALKAN", fontSize = 12.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(270.dp)
                    .background(Color(0xFFFFFDF8), RoundedCornerShape(18.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 10.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 18.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF1E6BE3),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(start = 12.dp, top = 10.dp)
                ) {
                    Text(
                        text = "Catatan...",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Serif
                    )
                }

                Box(
                    modifier = Modifier
                        .width(168.dp)
                        .height(64.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-10).dp, y = (-14).dp)
                        .background(BlueDetail, RoundedCornerShape(10.dp))
                        .clickable { onContinueClick(qty) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Lanjutkan",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BottomDetailBar(
                onBackClick = onBackClick,
                onMenuClick = { showMenu = !showMenu }
            )
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

@Composable
fun HeaderDetail(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(HeaderBlueDetail)
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
            Text("Hai..!", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text(userName, color = Color.White, fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text("User", color = Color.White, fontSize = 17.sp)
    }
}

@Composable
private fun QtyButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(66.dp)
            .height(36.dp)
            .background(Color(0xFFD3E5F2), RoundedCornerShape(5.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
private fun WarningRow(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(312.dp)
            .height(40.dp)
            .border(1.dp, color, RoundedCornerShape(18.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
private fun BottomDetailBar(
    onBackClick: () -> Unit,
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
                .offset(x = 12.dp)
                .background(Color(0xFFB8DAE5), CircleShape)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.add_file_here2),
                contentDescription = "Kembali",
                modifier = Modifier.size(34.dp),
                contentScale = ContentScale.Fit
            )
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
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit
            )
        }

        Box(
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-12).dp)
                .background(Color(0xFFB8DAE5), RoundedCornerShape(8.dp))
                .clickable { onMenuClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.tombol_riwayat_transaksi),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
private fun getDetailIcon(title: String): Int {
    return when (title) {
        "Brosur A4" -> R.drawable.brosur_a4_2
        "ID CARD" -> R.drawable.id_card_2
        "Fotocopy" -> R.drawable.fotocopy_2
        "Print" -> R.drawable.print_2
        else -> R.drawable.brosur_a4_2
    }
}