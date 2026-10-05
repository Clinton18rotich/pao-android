package com.example.pao.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAOEntry
import com.example.pao.data.getPAO
import com.example.pao.ui.theme.*

@Composable
fun TeachScreen() {
    var input by remember { mutableStateOf("") }
    var active by remember { mutableStateOf<PAOEntry?>(null) }
    var error by remember { mutableStateOf("") }

    val scroll = rememberScrollState()

    fun load() {
        if (input.length == 2) {
            val entry = getPAO(input)
            if (entry != null) {
                active = entry
                error = ""
            } else {
                error = "Number not found"
                active = null
            }
        } else {
            error = "Enter 2 digits (00-99)"
            active = null
        }
    }

    fun random() {
        val n = (0..99).random().toString().padStart(2, '0')
        input = n
        active = getPAO(n)
        error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {
        Text(
            "🧠 Teach",
            color = Orange,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            if (active != null) {
                val resId = androidx.compose.ui.platform.LocalContext.current.resources
                    .getIdentifier("img_${active!!.number}", "drawable",
                        androidx.compose.ui.platform.LocalContext.current.packageName)
                if (resId != 0) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = active!!.person,
                        modifier = Modifier.size(160.dp).clip(RoundedCornerShape(80.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    FallbackCircle(active!!.person)
                }
            } else {
                FallbackCircle("Enter #")
            }
        }

        Spacer(Modifier.height(8.dp))

        // Number
        Text(
            active?.number ?: "??",
            color = Orange,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(8.dp))

        // PAO rows
        PAORow("Person:", active?.person ?: "—")
        PAORow("Action:", active?.action ?: "—")
        PAORow("Object:", active?.objectName ?: "—")

        Spacer(Modifier.height(12.dp))

        // Bio
        if (active != null) {
            Text(
                "📖 ${active!!.bio}",
                color = LightGrey,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Mnemonic
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E2A1E), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text("🧠 How to remember this number:", color = Green, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Spacer(Modifier.height(4.dp))
                Text(active!!.mnemonic, color = Color(0xFFDDDDDD), fontSize = 14.sp)
            }
        }

        Spacer(Modifier.height(16.dp))

        // Input
        OutlinedTextField(
            value = input,
            onValueChange = {
                if (it.length <= 2 && it.all { c -> c.isDigit() }) input = it
            },
            label = { Text("Enter number 00-99") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { load() }),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange,
                unfocusedBorderColor = Color(0xFF444444),
                focusedLabelColor = Orange,
                unfocusedLabelColor = LightGrey,
                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )

        if (error.isNotEmpty()) {
            Text(error, color = Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { load() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White)
            ) { Text("↵ Go") }

            Button(
                onClick = { random() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)
            ) { Text("🎲 Random") }
        }
    }
}

@Composable
private fun PAORow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = LightGrey, fontSize = 15.sp)
        Text(value, color = White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun FallbackCircle(text: String) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(80.dp))
            .background(DarkSurface),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Orange, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
