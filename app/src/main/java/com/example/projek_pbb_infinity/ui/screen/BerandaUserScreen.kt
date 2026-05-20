package com.example.projek_pbb_infinity.ui.screen

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projek_pbb_infinity.R
import com.example.projek_pbb_infinity.ui.component.BottomMenuDropdown
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

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

    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()

    val filePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

            if (uri == null) {
                Toast.makeText(context, "File tidak dipilih", Toast.LENGTH_SHORT).show()
                return@rememberLauncherForActivityResult
            }

            Toast.makeText(context, "Mengupload file...", Toast.LENGTH_SHORT).show()

            try {
                val mimeType = context.contentResolver.getType(uri)
                val inputStream = context.contentResolver.openInputStream(uri)
                val fileBytes = inputStream?.readBytes()
                inputStream?.close()

                if (fileBytes == null) {
                    Toast.makeText(context, "File gagal dibaca", Toast.LENGTH_SHORT).show()
                    return@rememberLauncherForActivityResult
                }

                val isImage = mimeType?.startsWith("image/") == true
                val isPdf = mimeType == "application/pdf"

                val fileName = when {
                    isImage -> "upload_file.jpg"
                    isPdf -> "upload_file.pdf"
                    else -> "upload_file"
                }

                val uploadUrl = when {
                    isImage -> "https://api.cloudinary.com/v1_1/dtlnouiro/image/upload"
                    isPdf -> "https://api.cloudinary.com/v1_1/dtlnouiro/raw/upload"
                    else -> "https://api.cloudinary.com/v1_1/dtlnouiro/raw/upload"
                }

                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "file",
                        fileName,
                        fileBytes.toRequestBody((mimeType ?: "*/*").toMediaTypeOrNull())
                    )
                    .addFormDataPart("upload_preset", "android_upload")
                    .build()

                val request = Request.Builder()
                    .url(uploadUrl)
                    .post(requestBody)
                    .build()

                OkHttpClient().newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context,
                                "Upload gagal: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseData = response.body?.string()

                        Handler(Looper.getMainLooper()).post {
                            if (response.isSuccessful) {
                                try {
                                    val json = JSONObject(responseData ?: "{}")
                                    val fileUrl = json.getString("secure_url")
                                    val fileType = json.optString("resource_type", "raw")
                                    val publicId = json.optString("public_id", "")

                                    val data = hashMapOf(
                                        "fileUrl" to fileUrl,
                                        "fileResponse" to responseData,
                                        "fileType" to fileType,
                                        "publicId" to publicId,
                                        "mimeType" to mimeType,
                                        "userName" to userName,
                                        "serviceTitle" to selectedService?.title,
                                        "servicePrice" to selectedService?.price,
                                        "uploadTime" to System.currentTimeMillis()
                                    )

                                    val documentId =
                                        "${selectedService?.title}_${System.currentTimeMillis()}"

                                    firestore.collection("uploads")
                                        .document(documentId)
                                        .set(data)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                context,
                                                "Upload berhasil",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(
                                                context,
                                                "Gagal simpan data: ${e.message}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Gagal membaca URL file",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Upload gagal ke Cloudinary",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })

            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("~BERANDA~", fontSize = 22.sp, color = Color.Black)
            }

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
                        .clickable {
                            if (selectedService == null) {
                                Toast.makeText(
                                    context,
                                    "Pilih layanan terlebih dahulu",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                filePickerLauncher.launch("*/*")
                            }
                        }
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
                            selectedService?.let {
                                onNextClick(it)
                            } ?: Toast.makeText(
                                context,
                                "Pilih layanan terlebih dahulu",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            ServiceItem(
                ServiceDetail("Brosur A4", "RP 15.000", R.drawable.brosur_a4),
                selectedService?.title == "Brosur A4"
            ) { selectedService = it }

            ServiceItem(
                ServiceDetail("ID CARD", "RP 10.000", R.drawable.id_card),
                selectedService?.title == "ID CARD"
            ) { selectedService = it }

            ServiceItem(
                ServiceDetail("Fotocopy", "RP 2.000", R.drawable.fotocopy),
                selectedService?.title == "Fotocopy"
            ) { selectedService = it }

            ServiceItem(
                ServiceDetail("Print", "RP 5.000", R.drawable.print),
                selectedService?.title == "Print"
            ) { selectedService = it }

            Spacer(modifier = Modifier.weight(1f))

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