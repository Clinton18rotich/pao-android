package com.example.pao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAOEntry
import com.example.pao.data.PAO_DATA
import com.example.pao.ui.theme.*

@Composable
fun FlashcardsScreen() {
    var current by remember { mutableStateOf(PAO_DATA.random()) }
    var revealed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🃏 Flashcards", color = Orange, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "🔢 ${current.number}",
                    color = Orange,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(20.dp))

                if (!revealed) {
                    Button(
                        onClick = { revealed = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange,
                            contentColor = Color.Black
                        )
                    ) { Text("Reveal PAO", fontSize = 16.sp) }
                } else {
                    Text(current.person, color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(current.action, color = LightGrey, fontSize = 16.sp)
                    Spacer(Modifier.height(8.dp))
                    Text(current.objectName, color = LightGrey, fontSize = 16.sp)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                current = PAO_DATA.random()
                revealed = false
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))
        ) { Text("Next Card", fontSize = 16.sp) }
    }
}
