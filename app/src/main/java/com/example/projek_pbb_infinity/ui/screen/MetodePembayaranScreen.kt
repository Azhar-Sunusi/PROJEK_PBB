package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

private val Cream = Color(0xFFF3E3D0)
private val Blue = Color(0xFF8EB0CF)
private val LightBlue = Color(0xFFAED3DF)
private val HeaderBlue = Color(0xFF8DB4D1)

@Composable
fun MetodePembayaranScreen(
    serviceTitle: String,
    totalPayment: Int,
    onBackClick: () -> Unit,
    onQrisPayClick: () -> Unit
) {
    var selectedPayment by remember { mutableStateOf("Tunai") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        HeaderPayment()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "~Halaman Instruksi pembayaran~",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .height(220.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "metode pembayaran:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(14.dp))

                PaymentOption(
                    icon = R.drawable.dollar_1,
                    text = "Pembayaran Tunai",
                    selected = selectedPayment == "Tunai",
                    onClick = { selectedPayment = "Tunai" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                PaymentOption(
                    icon = R.drawable.transfer_virtual_account,
                    text = "Transfer Virtual Account",
                    selected = selectedPayment == "VA",
                    onClick = { selectedPayment = "VA" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                PaymentOption(
                    icon = R.drawable.qris,
                    text = "QRIS",
                    selected = selectedPayment == "QRIS",
                    onClick = { selectedPayment = "QRIS" }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 10.dp)
                .height(100.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Text(
                    text = "Total Pembayaran",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = serviceTitle,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            Text(
                text = formatRupiah(totalPayment),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 4.dp)
            )

            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp)
                    .align(Alignment.BottomCenter)
                    .background(Blue, RoundedCornerShape(10.dp))
                    .clickable {
                        if (selectedPayment == "QRIS") {
                            onQrisPayClick()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bayar Sekarang",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomPaymentBar(onBackClick = onBackClick)
    }
}

@Composable
fun HeaderPayment() {
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
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text("Hai..!", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text("Azhar", color = Color.White, fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text("User", color = Color.White, fontSize = 17.sp)
    }
}

@Composable
private fun PaymentOption(
    icon: Int,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Blue else LightBlue

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(bgColor, RoundedCornerShape(6.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(
                if (text == "QRIS") 42.dp else 26.dp
            ),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(if (text == "QRIS") 8.dp else 12.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottomPaymentBar(
    onBackClick: () -> Unit
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
                .background(Color(0xFFB8DAE5), RoundedCornerShape(8.dp)),
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

fun formatRupiah(value: Int): String {
    return "Rp " + "%,d".format(value).replace(",", ".")
}