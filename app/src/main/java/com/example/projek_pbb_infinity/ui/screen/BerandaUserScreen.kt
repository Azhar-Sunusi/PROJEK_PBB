package com.example.projek_pbb_infinity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R

private val Cream = Color(0xFFF3E3D0)
private val Blue = Color(0xFF8EB0CF)
private val HeaderBlue = Color(0xFF8DB4D1)

@Composable
fun BerandaUserScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {

        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(HeaderBlue)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color(0xFFB8DAE5), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.profil_kiri_atas),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text("Hai..!", color = Color.White)
                Text("Azhar", color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text("User", color = Color.White)
        }

        // TITLE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("~BERANDA~", fontSize = 20.sp)
        }

        // ADD FILE
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(Blue, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.print),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text("Add File Here!", color = Color.White)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(R.drawable.add_file_here),
                contentDescription = null,
                modifier = Modifier.size(55.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        ServiceItem("Brosur A4", "RP. 15.000/lembar", R.drawable.brosur_a4)
        ServiceItem("ID CARD", "RP.10.000/PCS", R.drawable.id_card)
        ServiceItem("Fotocopy", "RP.2.000/lembar", R.drawable.fotocopy)
        ServiceItem("Print", "Rp.5.000/lembar", R.drawable.print)

        Spacer(modifier = Modifier.weight(1f))

        // BOTTOM BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White)
        ) {

            Image(
                painter = painterResource(R.drawable.tombol_home),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.Center)
                    .offset(y = (-15).dp)
            )

            Image(
                painter = painterResource(R.drawable.tombol_riwayat_transaksi),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }
    }
}

@Composable
fun ServiceItem(title: String, price: String, icon: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .height(70.dp)
            .background(Blue, RoundedCornerShape(20.dp))
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        ) {
            Text(title, color = Color.White)
            Text(price, color = Color.White)
        }

        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(40.dp),
            contentScale = ContentScale.Fit
        )
    }
}