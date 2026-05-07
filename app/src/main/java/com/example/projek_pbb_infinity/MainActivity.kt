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
import com.google.firebase.firestore.FirebaseFirestore

private const val ADMIN_EMAIL = "admin@gmail.com"

enum class Screen {
    LANDING,
    LOGIN_FORM,
    SIGN_UP_FORM,
    BERANDA_USER,
    DETAIL_PESANAN,
    METODE_PEMBAYARAN,
    QRIS_BARCODE,
    PEMBAYARAN_BERHASIL,
    ADMIN_HOME
}

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

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

                fun goToLogin() {
                    auth.signOut()
                    currentScreen = Screen.LOGIN_FORM
                }

                fun loginWithEmail(realEmail: String, password: String, fallbackName: String = "User") {
                    auth.signInWithEmailAndPassword(realEmail.trim(), password)
                        .addOnSuccessListener { result ->
                            val emailLogin = result.user?.email ?: ""

                            userName = result.user?.displayName
                                ?: result.user?.email?.substringBefore("@")
                                        ?: fallbackName

                            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                            currentScreen = if (emailLogin == ADMIN_EMAIL) {
                                Screen.ADMIN_HOME
                            } else {
                                Screen.BERANDA_USER
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Login gagal. Cek username/email dan password.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }

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
                            onLoginSubmit = { input, password ->
                                val loginInput = input.trim()

                                if (loginInput.contains("@")) {
                                    loginWithEmail(loginInput, password)
                                } else {
                                    db.collection("users")
                                        .document(loginInput.lowercase())
                                        .get()
                                        .addOnSuccessListener { document ->
                                            val realEmail = document.getString("email")
                                            val savedUsername = document.getString("username") ?: loginInput

                                            if (realEmail.isNullOrBlank()) {
                                                Toast.makeText(
                                                    this,
                                                    "Username tidak ditemukan",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            } else {
                                                loginWithEmail(realEmail, password, savedUsername)
                                            }
                                        }
                                        .addOnFailureListener { error ->
                                            Toast.makeText(
                                                this,
                                                "Gagal cek username: ${error.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                }
                            }
                        )
                    }

                    Screen.SIGN_UP_FORM -> {
                        UserSignUpFormScreen(
                            onBackClick = { currentScreen = Screen.LANDING },
                            onNextClick = { name, _, email, password ->
                                val cleanName = name.trim()
                                val cleanEmail = email.trim()
                                val usernameKey = cleanName.lowercase()

                                auth.createUserWithEmailAndPassword(cleanEmail, password)
                                    .addOnSuccessListener { result ->
                                        val profileUpdates = userProfileChangeRequest {
                                            displayName = cleanName
                                        }

                                        result.user?.updateProfile(profileUpdates)

                                        val userData = hashMapOf(
                                            "username" to usernameKey,
                                            "email" to cleanEmail
                                        )

                                        db.collection("users")
                                            .document(usernameKey)
                                            .set(userData)
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    this,
                                                    "Sign Up berhasil & tersimpan di database",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                auth.signOut()
                                                currentScreen = Screen.LOGIN_FORM
                                            }
                                            .addOnFailureListener { error ->
                                                Toast.makeText(
                                                    this,
                                                    "Gagal simpan ke database: ${error.message}",
                                                    Toast.LENGTH_LONG
                                                ).show()
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

                    Screen.ADMIN_HOME -> {
                        AdminHomeScreen(
                            onLogoutClick = { goToLogin() }
                        )
                    }

                    Screen.BERANDA_USER -> {
                        BerandaUserScreen(
                            userName = userName,
                            onNextClick = {
                                selectedDetail = it
                                currentScreen = Screen.DETAIL_PESANAN
                            },
                            onLogoutClick = { goToLogin() }
                        )
                    }

                    Screen.DETAIL_PESANAN -> {
                        DetailPesananScreen(
                            service = selectedDetail,
                            userName = userName,
                            onBackClick = { currentScreen = Screen.BERANDA_USER },
                            onContinueClick = { qty ->
                                selectedQty = qty
                                currentScreen = Screen.METODE_PEMBAYARAN
                            },
                            onLogoutClick = { goToLogin() }
                        )
                    }

                    Screen.METODE_PEMBAYARAN -> {
                        MetodePembayaranScreen(
                            serviceTitle = selectedDetail.title,
                            userName = userName,
                            totalPayment = getHarga(selectedDetail.title) * selectedQty,
                            onBackClick = { currentScreen = Screen.DETAIL_PESANAN },
                            onQrisPayClick = { currentScreen = Screen.QRIS_BARCODE },
                            onLogoutClick = { goToLogin() }
                        )
                    }

                    Screen.QRIS_BARCODE -> {
                        QrisBarcodeScreen(
                            userName = userName,
                            onBackClick = { currentScreen = Screen.METODE_PEMBAYARAN },
                            onCheckStatusClick = { currentScreen = Screen.PEMBAYARAN_BERHASIL },
                            onLogoutClick = { goToLogin() }
                        )
                    }

                    Screen.PEMBAYARAN_BERHASIL -> {
                        PembayaranBerhasilScreen(
                            serviceTitle = selectedDetail.title,
                            userName = userName,
                            totalPayment = getHarga(selectedDetail.title) * selectedQty,
                            onBackClick = { currentScreen = Screen.QRIS_BARCODE },
                            onLogoutClick = { goToLogin() }
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