package com.example.pao.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
    var fullscreenImage by remember { mutableStateOf<Int?>(null) }

    val scroll = rememberScrollState()
    val context = LocalContext.current

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

    fun next() {
        val current = active?.number?.toIntOrNull() ?: 0
        val nextNum = (current + 1) % 100
        val nextStr = nextNum.toString().padStart(2, '0')
        input = nextStr
        active = getPAO(nextStr)
        error = ""
    }

    fun getImageId(num: String): Int {
        return context.resources.getIdentifier("img_$num", "drawable", context.packageName)
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

        // Image (clickable)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (active != null) {
                val resId = getImageId(active!!.number)
                if (resId != 0) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = active!!.person,
                        modifier = Modifier
                            .size(160.dp)
                            .clip(RoundedCornerShape(80.dp))
                            .clickable { fullscreenImage = resId },
                        contentScale = ContentScale.Crop
                    )
                } else {
                    FallbackCircle(active!!.person)
                }
            } else {
                FallbackCircle("Enter #")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Number
        Text(
            active?.number ?: "??",
            color = Orange,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(8.dp))

        // PAO rows
        PAORow("Person:", active?.person ?: "—")
        PAORow("Action:", active?.action ?: "—")
        PAORow("Object:", active?.objectName ?: "—")

        // Input area (moved up here, below Object)
        Spacer(Modifier.height(12.dp))
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
            ) { Text("🎲") }

            Button(
                onClick = { next() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555), contentColor = White)
            ) { Text("➡️") }
        }

        // Below the input: badges, bio, source, mnemonic
        if (active != null) {
            Spacer(Modifier.height(16.dp))

            // Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Badge(
                    text = active!!.type,
                    color = if (active!!.type == "Real Person") Green else Color(0xFF42A5F5)
                )
                Badge(
                    text = active!!.category,
                    color = Orange
                )
            }

            Spacer(Modifier.height(12.dp))

            // Bio card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(10.dp))
                    .padding(14.dp)
            ) {
                Text("📖 Biography", color = Orange, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(Modifier.height(6.dp))
                Text(active!!.bio, color = Color(0xFFDDDDDD), fontSize = 14.sp, lineHeight = 20.sp)
            }

            Spacer(Modifier.height(10.dp))

            // Source line
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Text("🎬 Source: ${active!!.source}",
                    color = Color(0xFFBBBBBB),
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic)
            }

            Spacer(Modifier.height(10.dp))

            // Mnemonic
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E2A1E), RoundedCornerShape(8.dp))
                    .padding(14.dp)
            ) {
                Text("🧠 How to remember this number:",
                    color = Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp)
                Spacer(Modifier.height(6.dp))
                Text(active!!.mnemonic, color = Color(0xFFDDDDDD), fontSize = 14.sp, lineHeight = 20.sp)
            }
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
private fun Badge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = color, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
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
