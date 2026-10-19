package com.example.pao.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAO_DATA
import com.example.pao.data.getPAO
import com.example.pao.ui.theme.*

@Composable
fun PracticeScreen() {
    var activeTab by remember { mutableStateOf("PAO") } // PAO or Chain

    Column(modifier = Modifier.fillMaxSize().background(DarkBg).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // Tab Selector
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { activeTab = "PAO" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (activeTab == "PAO") Orange else DarkSurface)) {
                Text("3-Card PAO", color = if (activeTab == "PAO") Color.Black else White)
            }
            Button(onClick = { activeTab = "Chain" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (activeTab == "Chain") Orange else DarkSurface)) {
                Text("Number Chain", color = if (activeTab == "Chain") Color.Black else White)
            }
        }
        Spacer(Modifier.height(24.dp))

        if (activeTab == "PAO") {
            PAOPractice()
        } else {
            ChainPractice()
        }
    }
}

@Composable
fun PAOPractice() {
    var numbers by remember { mutableStateOf(List(3) { PAO_DATA.random() }) }
    var revealed by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Link the Person, Action, and Object", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            numbers.forEachIndexed { index, num ->
                Card(modifier = Modifier.weight(1f).height(160.dp), colors = CardDefaults.cardColors(containerColor = DarkSurface)) {
                    Column(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(num.number, color = Orange, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        val label = when(index) { 0 -> "PERSON"; 1 -> "ACTION"; else -> "OBJECT" }
                        Text(label, color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        val value = when(index) { 0 -> num.person; 1 -> num.action; else -> num.objectName }
                        Text(value, color = White, fontSize = 12.sp, maxLines = 2, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { revealed = !revealed }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) {
            Text(if (revealed) "Hide Story" else "Reveal Story")
        }
        AnimatedVisibility(visible = revealed) {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                Text("Imagine ${numbers[0].person} is ${numbers[1].action} a ${numbers[2].objectName}.", color = White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { numbers = List(3) { PAO_DATA.random() }; revealed = false }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text("🎲 Shuffle")
        }
    }
}

@Composable
fun ChainPractice() {
    var targetSequence by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showAnswer by remember { mutableStateOf(false) }

    fun generateSequence() {
        // Generate a random length: 3, 5, or 7 digits
        val length = listOf(3, 5, 7).random()
        targetSequence = (1..length).map { (0..9).random() }.joinToString("")
        userInput = ""
        result = ""
        showAnswer = false
    }

    LaunchedEffect(Unit) { generateSequence() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Memorize this number sequence:", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        Text(targetSequence, color = Orange, fontSize = 40.sp, fontWeight = FontWeight.Bold, letterSpacing = 4.sp)
        Spacer(Modifier.height(16.dp))

        Text("Break it into PAO pairs. If a single digit remains, add a phantom 0 (e.g., 5 becomes 05).", color = LightGrey, fontSize = 12.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { showAnswer = !showAnswer }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text(if (showAnswer) "Hide Breakdown" else "Show Breakdown")
        }

        AnimatedVisibility(visible = showAnswer) {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                val pairs = mutableListOf<String>()
                var temp = targetSequence
                while (temp.length >= 2) {
                    pairs.add(temp.substring(0, 2))
                    temp = temp.substring(2)
                }
                if (temp.length == 1) pairs.add("0" + temp) // Phantom digit fix

                pairs.forEach { pair ->
                    val entry = getPAO(pair)
                    if (entry != null) {
                        Text("$pair → ${entry.person} (${entry.action} ${entry.objectName})", color = White, fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(onClick = { generateSequence() }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) {
            Text("🔄 Generate New Sequence")
        }
    }
}
