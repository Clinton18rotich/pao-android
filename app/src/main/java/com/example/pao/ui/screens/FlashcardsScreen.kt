package com.example.pao.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAO_DATA
import com.example.pao.ui.theme.*

@Composable
fun FlashcardsScreen() {
    var current by remember { mutableStateOf(PAO_DATA.random()) }
    var revealed by remember { mutableStateOf(false) }
    var fullscreenImage by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current

    fun getImageId(num: String): Int {
        return context.resources.getIdentifier("img_$num", "drawable", context.packageName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🃏 Flashcards", color = Orange, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Character image
                val resId = getImageId(current.number)
                if (resId != 0) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = current.person,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .clickable { fullscreenImage = resId },
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .background(Color(0xFF2A2A2A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(current.person, color = Orange, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    "🔢 ${current.number}",
                    color = Orange,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                if (!revealed) {
                    Button(
                        onClick = { revealed = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange,
                            contentColor = Color.Black
                        )
                    ) { Text("Reveal PAO", fontSize = 15.sp) }
                } else {
                    Text(current.person, color = White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text(current.action, color = LightGrey, fontSize = 15.sp)
                    Spacer(Modifier.height(6.dp))
                    Text(current.objectName, color = LightGrey, fontSize = 15.sp)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    current = PAO_DATA.random()
                    revealed = false
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))
            ) { Text("🎲 Random", fontSize = 14.sp) }

            Button(
                onClick = {
                    val nextIdx = (PAO_DATA.indexOf(current) + 1) % PAO_DATA.size
                    current = PAO_DATA[nextIdx]
                    revealed = false
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))
            ) { Text("➡️ Next", fontSize = 14.sp) }
        }
    }

    // Fullscreen image modal
    fullscreenImage?.let { resId ->
        FullscreenImage(
            resId = resId,
            onDismiss = { fullscreenImage = null }
        )
    }
}
