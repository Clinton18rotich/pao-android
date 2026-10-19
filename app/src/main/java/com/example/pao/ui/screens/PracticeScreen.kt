package com.example.pao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAO_DATA
import com.example.pao.data.getPAO
import com.example.pao.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun PracticeScreen() {
    var activeTab by remember { mutableStateOf("PAO") } // PAO, Chain, Digits

    Column(modifier = Modifier.fillMaxSize().background(DarkBg).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // Tab Selector
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = { activeTab = "PAO" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (activeTab == "PAO") Orange else DarkSurface), contentPadding = PaddingValues(4.dp)) {
                Text("PAO", color = if (activeTab == "PAO") Color.Black else White, fontSize = 12.sp)
            }
            Button(onClick = { activeTab = "Chain" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (activeTab == "Chain") Orange else DarkSurface), contentPadding = PaddingValues(4.dp)) {
                Text("Chain", color = if (activeTab == "Chain") Color.Black else White, fontSize = 12.sp)
            }
            Button(onClick = { activeTab = "Digits" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (activeTab == "Digits") Orange else DarkSurface), contentPadding = PaddingValues(4.dp)) {
                Text("Championship", color = if (activeTab == "Digits") Color.Black else White, fontSize = 12.sp)
            }
        }
        Spacer(Modifier.height(16.dp))

        when (activeTab) {
            "PAO" -> PAOPractice()
            "Chain" -> ChainPractice()
            "Digits" -> ChampionshipDigits()
        }
    }
}

@Composable
fun ChampionshipDigits() {
    var gameState by remember { mutableStateOf("setup") }
    var digitCount by remember { mutableStateOf(100) }
    var targetDigits by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(3600) } // 1 hour default for championship
    var resultMessage by remember { mutableStateOf("") }

    LaunchedEffect(gameState) {
        if (gameState == "memorizing") {
            while (timeLeft > 0 && gameState == "memorizing") {
                delay(1000L)
                timeLeft--
            }
            if (gameState == "memorizing") {
                gameState = "recall"
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        if (gameState == "setup") {
            Text("Championship Digits", color = Orange, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("Memorize a long string of digits using PAO. Standard time: 1 hour.", color = LightGrey, fontSize = 12.sp, textAlign = TextAlign.Center)
            
            Spacer(Modifier.height(24.dp))
            Text("How many digits?", color = White)
            
            // Row 1: 100, 200, 300
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(100, 200, 300).forEach { count ->
                    Button(onClick = { digitCount = count }, colors = ButtonDefaults.buttonColors(containerColor = if (digitCount == count) Orange else DarkSurface)) {
                        Text("$count", color = if (digitCount == count) Color.Black else White)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            // Row 2: 500, 1000, 2000
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(500, 1000, 2000).forEach { count ->
                    Button(onClick = { digitCount = count }, colors = ButtonDefaults.buttonColors(containerColor = if (digitCount == count) Orange else DarkSurface)) {
                        Text("$count", color = if (digitCount == count) Color.Black else White)
                    }
                }
            }
            
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                val len = if (digitCount % 2 == 0) digitCount else digitCount + 1
                targetDigits = (1..len).map { (0..9).random() }.joinToString("")
                userInput = ""
                resultMessage = ""
                timeLeft = 3600 // Reset to 1 hour
                gameState = "memorizing"
            }, colors = ButtonDefaults.buttonColors(containerColor = Green), modifier = Modifier.fillMaxWidth()) {
                Text("Start Memorizing")
            }
        }
        
        else if (gameState == "memorizing") {
            Text(String.format("⏱️ %02d:%02d:%02d", timeLeft / 3600, (timeLeft % 3600) / 60, timeLeft % 60), color = Red, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            
            // Show digits in a scrollable box for long sequences
            Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                Text(targetDigits, color = Orange, fontSize = 18.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp, textAlign = TextAlign.Center, modifier = Modifier.verticalScroll(rememberScrollState()))
            }
            
            Spacer(Modifier.height(16.dp))
            Text("Break these into PAO pairs. When ready, type them back.", color = LightGrey, fontSize = 12.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { gameState = "recall" }, colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black), modifier = Modifier.fillMaxWidth()) {
                Text("I'm Ready (Skip Timer)")
            }
        }
        
        else if (gameState == "recall") {
            Text("Type the digits back", color = Orange, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it.filter { char -> char.isDigit() } },
                label = { Text("Recall the sequence") },
                modifier = Modifier.fillMaxWidth().height(150.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Orange, focusedTextColor = White)
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                if (userInput == targetDigits) {
                    resultMessage = "✅ Perfect! You recalled all $digitCount digits correctly."
                } else {
                    resultMessage = "❌ Incorrect. Check your answer."
                }
                gameState = "results"
            }, colors = ButtonDefaults.buttonColors(containerColor = Green), modifier = Modifier.fillMaxWidth()) {
                Text("Check Answer")
            }
        }
        
        else if (gameState == "results") {
            Text(resultMessage, color = if (resultMessage.startsWith("✅")) Green else Red, fontSize = 16.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(16.dp))
            // Show target vs typed for comparison
            Column(modifier = Modifier.fillMaxWidth().background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                Text("Target:", color = LightGrey, fontSize = 12.sp)
                Text(targetDigits, color = Orange, fontSize = 14.sp, modifier = Modifier.heightIn(max = 100.dp).verticalScroll(rememberScrollState()))
                Spacer(Modifier.height(8.dp))
                Text("You typed:", color = LightGrey, fontSize = 12.sp)
                Text(userInput, color = White, fontSize = 14.sp, modifier = Modifier.heightIn(max = 100.dp).verticalScroll(rememberScrollState()))
            }
            Spacer(Modifier.height(24.dp))
            Button(onClick = { gameState = "setup" }, colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black), modifier = Modifier.fillMaxWidth()) {
                Text("Try Again")
            }
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
                        if (revealed) {
                            val value = when(index) { 0 -> num.person; 1 -> num.action; else -> num.objectName }
                            Text(value, color = White, fontSize = 12.sp, maxLines = 2, textAlign = TextAlign.Center)
                        } else {
                            Text("???", color = LightGrey, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { revealed = !revealed }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) {
            Text(if (revealed) "Hide Story" else "Reveal Story")
        }
        if (revealed) {
            Spacer(Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth().background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                Text("Imagine ${numbers[0].person} is ${numbers[1].action} a ${numbers[2].objectName}.", color = White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { numbers = List(3) { PAO_DATA.random() } }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text("🎲 Shuffle")
        }
    }
}

@Composable
fun ChainPractice() {
    var targetSequence by remember { mutableStateOf("") }
    var showAnswer by remember { mutableStateOf(false) }

    fun generateSequence() {
        val length = listOf(3, 5, 7).random()
        targetSequence = (1..length).map { (0..9).random() }.joinToString("")
        showAnswer = false
    }

    LaunchedEffect(Unit) { generateSequence() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Memorize this number sequence:", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        Text(targetSequence, color = Orange, fontSize = 40.sp, fontWeight = FontWeight.Bold, letterSpacing = 4.sp)
        Spacer(Modifier.height(16.dp))
        Text("Break it into PAO pairs. If a single digit remains, add a phantom 0.", color = LightGrey, fontSize = 12.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { showAnswer = !showAnswer }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) {
            Text(if (showAnswer) "Hide Breakdown" else "Show Breakdown")
        }
        if (showAnswer) {
            Spacer(Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth().background(DarkSurface, RoundedCornerShape(10.dp)).padding(16.dp)) {
                val pairs = mutableListOf<String>()
                var temp = targetSequence
                while (temp.length >= 2) {
                    pairs.add(temp.substring(0, 2))
                    temp = temp.substring(2)
                }
                if (temp.length == 1) pairs.add("0" + temp)
                pairs.forEach { pair ->
                    val entry = getPAO(pair)
                    if (entry != null) Text("$pair → ${entry.person} (${entry.action} ${entry.objectName})", color = White, fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = { generateSequence() }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) {
            Text("🔄 Generate New Sequence")
        }
    }
}
