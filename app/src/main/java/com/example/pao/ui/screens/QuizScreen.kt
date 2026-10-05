package com.example.pao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.pao.ui.theme.*

@Composable
fun QuizScreen() {
    var current by remember { mutableStateOf(PAO_DATA.random()) }
    var answer by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }
    var total by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🧪 Quiz", color = Orange, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        Text(
            "🔢 ${current.number}",
            color = Orange,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))
        Text("What is the PAO?", color = LightGrey, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            placeholder = { Text("Person - Action - Object") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange,
                unfocusedBorderColor = Color(0xFF444444),
                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                val correct = "${current.person} - ${current.action} - ${current.objectName}"
                if (answer.trim().equals(correct, ignoreCase = true)) {
                    result = "✅ Correct!"
                    score++
                } else {
                    result = "❌ Wrong. Answer: $correct"
                }
                total++
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)
        ) { Text("Check Answer", fontSize = 16.sp) }

        if (result != null) {
            Spacer(Modifier.height(12.dp))
            Text(
                result!!,
                color = if (result!!.startsWith("✅")) Green else Red,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(Modifier.height(8.dp))
        Text(
            "Score: $score / $total (${if (total > 0) (score * 100 / total) else 0}%)",
            color = White,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                current = PAO_DATA.random()
                answer = ""
                result = null
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))
        ) { Text("Next Question", fontSize = 16.sp) }
    }
}
