package com.example.projek_pbb_infinity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.projek_pbb_infinity.ui.screen.*
import com.example.projek_pbb_infinity.ui.theme.ProjekPbbInfinityTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

enum class Screen {
    LANDING,
    LOGIN_FORM,
    SIGN_UP_FORM,
    BERANDA_USER,
    DETAIL_PESANAN,
    METODE_PEMBAYARAN,
    QRIS_BARCODE,
    PEMBAYARAN_BERHASIL
}

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            ProjekPbbInfinityTheme {
                var currentScreen by remember { mutableStateOf(Screen.LANDING) }
                var userName by remember { mutableStateOf("User") }

                var selectedDetail by remember {
                    mutableStateOf(
                        ServiceDetail(
                            title = "Brosur A4",
                            price = "RP 15.000",
                            icon = R.drawable.brosur_a4
                        )
                    )
                }

                var selectedQty by remember { mutableStateOf(1) }

                when (currentScreen) {
                    Screen.LANDING -> {
                        LandingScreen(
                            onLoginClick = { currentScreen = Screen.LOGIN_FORM },
                            onSignUpClick = { currentScreen = Screen.SIGN_UP_FORM }
                        )
                    }

                    Screen.LOGIN_FORM -> {
                        UserLoginFormScreen(
                            onBackClick = { currentScreen = Screen.LANDING },
                            onLoginSubmit = { email, password ->
                                auth.signInWithEmailAndPassword(email, password)
                                    .addOnSuccessListener { result ->
                                        userName = result.user?.displayName
                                            ?: result.user?.email?.substringBefore("@")
                                                    ?: "User"

                                        Toast.makeText(
                                            this,
                                            "Login berhasil",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        currentScreen = Screen.BERANDA_USER
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            this,
                                            "Login gagal. Sign Up dulu atau cek email/password.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                            }
                        )
                    }

                    Screen.SIGN_UP_FORM -> {
                        UserSignUpFormScreen(
                            onBackClick = { currentScreen = Screen.LANDING },
                            onNextClick = { name, _, email, password ->
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnSuccessListener { result ->
                                        val profileUpdates = userProfileChangeRequest {
                                            displayName = name
                                        }

                                        result.user?.updateProfile(profileUpdates)
                                            ?.addOnCompleteListener {
                                                auth.signOut()

                                                Toast.makeText(
                                                    this,
                                                    "Sign Up berhasil. Silakan login.",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                currentScreen = Screen.LOGIN_FORM
                                            }
                                    }
                                    .addOnFailureListener { error ->
                                        Toast.makeText(
                                            this,
                                            "Sign Up gagal: ${error.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                            }
                        )
                    }

                    Screen.BERANDA_USER -> {
                        BerandaUserScreen(
                            userName = userName,
                            onNextClick = {
                                selectedDetail = it
                                currentScreen = Screen.DETAIL_PESANAN
                            }
                        )
                    }

                    Screen.DETAIL_PESANAN -> {
                        DetailPesananScreen(
                            service = selectedDetail,
                            onBackClick = {
                                currentScreen = Screen.BERANDA_USER
                            },
                            onContinueClick = { qty ->
                                selectedQty = qty
                                currentScreen = Screen.METODE_PEMBAYARAN
                            }
                        )
                    }

                    Screen.METODE_PEMBAYARAN -> {
                        MetodePembayaranScreen(
                            serviceTitle = selectedDetail.title,
                            totalPayment = getHarga(selectedDetail.title) * selectedQty,
                            onBackClick = {
                                currentScreen = Screen.DETAIL_PESANAN
                            },
                            onQrisPayClick = {
                                currentScreen = Screen.QRIS_BARCODE
                            }
                        )
                    }

                    Screen.QRIS_BARCODE -> {
                        QrisBarcodeScreen(
                            onBackClick = {
                                currentScreen = Screen.METODE_PEMBAYARAN
                            },
                            onCheckStatusClick = {
                                currentScreen = Screen.PEMBAYARAN_BERHASIL
                            }
                        )
                    }

                    Screen.PEMBAYARAN_BERHASIL -> {
                        PembayaranBerhasilScreen(
                            serviceTitle = selectedDetail.title,
                            totalPayment = getHarga(selectedDetail.title) * selectedQty,
                            onBackClick = {
                                currentScreen = Screen.QRIS_BARCODE
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun getHarga(title: String): Int {
    return when (title) {
        "Brosur A4" -> 15000
        "ID CARD" -> 10000
        "Fotocopy" -> 2000
        "Print" -> 5000
        else -> 0
    }
}