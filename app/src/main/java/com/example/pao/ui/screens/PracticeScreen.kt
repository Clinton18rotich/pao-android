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
        Text("Link the Person, Action, and Object", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            numbers.forEachIndexed { index, num ->
                Card(modifier = Modifier.weight(1f).height(180.dp), colors = CardDefaults.cardColors(containerColor = DarkSurface)) {
                    Column(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(num.number, color = Orange, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        
                        // Show the specific PAO element based on position
                        when (index) {
                            0 -> {
                                Text("PERSON", color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                Text(num.person, color = White, fontSize = 12.sp, maxLines = 2, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                            }
                            1 -> {
                                Text("ACTION", color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                Text(num.action, color = White, fontSize = 12.sp, maxLines = 2, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                            }
                            2 -> {
                                Text("OBJECT", color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                Text(num.objectName, color = White, fontSize = 12.sp, maxLines = 2, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                            }
                        }
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
                Text("The Story:", color = Orange, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Imagine ${numbers[0].person} is ${numbers[1].action} a ${numbers[2].objectName}.", 
                    color = White, fontSize = 18.sp, fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "e.g., ${numbers[0].person} grabs a ${numbers[2].objectName} and starts ${numbers[1].action} it!", 
                    color = LightGrey, fontSize = 14.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(onClick = { numbers = List(3) { PAO_DATA.random() }; revealed = false }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text("🎲 Shuffle New PAO")
        }
    }
}
