package com.example.pao.ui.screens

import androidx.compose.animation.*
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
import com.example.pao.data.PAO_DATA
import com.example.pao.ui.theme.*

@Composable
fun PracticeScreen() {
    var numbers by remember { mutableStateOf(List(3) { PAO_DATA.random() }) }
    var revealed by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(DarkBg).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("🧠 Practice Mode", color = Orange, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Link the 3 Numbers into a Story", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            numbers.forEach { num ->
                Card(modifier = Modifier.weight(1f).height(160.dp), colors = CardDefaults.cardColors(containerColor = DarkSurface)) {
                    Column(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(num.number, color = Orange, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        Text(num.person, color = White, fontSize = 12.sp, maxLines = 2)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(onClick = { revealed = !revealed }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) {
            Text(if (revealed) "Hide Story" else "Reveal Story")
        }
        Spacer(Modifier.height(16.dp))

        AnimatedVisibility(visible = revealed, enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
            Column(modifier = Modifier.fillMaxWidth().background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                Text("Link them: ${numbers[0].person} + ${numbers[1].person} + ${numbers[2].person}", color = White, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Imagine: ${numbers[0].person} is ${numbers[0].action} a ${numbers[0].objectName}, and ${numbers[1].person} is doing something with ${numbers[2].objectName}.", color = Green, fontSize = 14.sp)
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(onClick = { numbers = List(3) { PAO_DATA.random() }; revealed = false }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text("🎲 Shuffle New Numbers")
        }
    }
}
