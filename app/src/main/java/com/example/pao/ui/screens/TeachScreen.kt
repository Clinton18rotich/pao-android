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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAOEntry
import com.example.pao.data.getPAO
import com.example.pao.ui.theme.*

@Composable
fun TeachScreen(onPracticeClick: () -> Unit) {
    var input by remember { mutableStateOf("") }
    var active by remember { mutableStateOf<PAOEntry?>(null) }
    var error by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    val context = LocalContext.current

    fun load() {
        if (input.length == 2) {
            val entry = getPAO(input)
            if (entry != null) { active = entry; error = "" } else { error = "Not found"; active = null }
        } else { error = "Enter 2 digits"; active = null }
    }

    fun random() { val n = (0..99).random().toString().padStart(2, '0'); input = n; active = getPAO(n); error = "" }

    fun next() {
        val current = active?.number?.toIntOrNull() ?: 0
        val nextNum = (current + 1) % 100
        val nextStr = nextNum.toString().padStart(2, '0')
        input = nextStr
        active = getPAO(nextStr)
        error = ""
    }

    fun getImageId(num: String): Int = context.resources.getIdentifier("img_$num", "drawable", context.packageName)

    Column(modifier = Modifier.fillMaxSize().background(DarkBg).verticalScroll(scroll).padding(16.dp)) {
        Text("🧠 Teach", color = Orange, fontSize = 26.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
        
        Box(modifier = Modifier.fillMaxWidth().height(180.dp), contentAlignment = Alignment.Center) {
            if (active != null) {
                val resId = getImageId(active!!.number)
                if (resId != 0) {
                    Image(painter = painterResource(id = resId), contentDescription = active!!.person, modifier = Modifier.size(160.dp).clip(RoundedCornerShape(80.dp)), contentScale = ContentScale.Crop)
                } else { FallbackCircle(active!!.person) }
            } else { FallbackCircle("Enter #") }
        }

        Spacer(Modifier.height(12.dp))
        Text(active?.number ?: "??", color = Orange, fontSize = 44.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(8.dp))
        
        PAORow("Person:", active?.person ?: "—")
        PAORow("Action:", active?.action ?: "—")
        PAORow("Object:", active?.objectName ?: "—")

        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = input, onValueChange = { if (it.length <= 2 && it.all { c -> c.isDigit() }) input = it },
            label = { Text("Enter number 00-99") }, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { load() }),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Orange, focusedTextColor = White)
        )
        if (error.isNotEmpty()) { Text(error, color = Red, fontSize = 12.sp) }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { load() }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Green)) { Text("↵ Go") }
            Button(onClick = { random() }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.Black)) { Text("🎲") }
            Button(onClick = { next() }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF555555))) { Text("➡️") }
        }

        if (active != null) {
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.background(if (active!!.type == "Real Person") Green else Color(0xFF42A5F5), RoundedCornerShape(20.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) { Text(active!!.type, color = White, fontSize = 12.sp) }
                Box(modifier = Modifier.background(Color(0xFF1E2A1E), RoundedCornerShape(20.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) { Text(active!!.category, color = Green, fontSize = 12.sp) }
            }
            Spacer(Modifier.height(12.dp))
            Column(modifier = Modifier.fillMaxWidth().background(DarkSurface, RoundedCornerShape(10.dp)).padding(14.dp)) {
                Text("📖 Bio", color = Orange, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text(active!!.bio, color = Color(0xFFDDDDDD), fontSize = 14.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text("🧠 ${active!!.mnemonic}", color = Color(0xFFDDDDDD), fontSize = 14.sp, modifier = Modifier.background(Color(0xFF1E2A1E), RoundedCornerShape(8.dp)).padding(14.dp))
            
            Spacer(Modifier.height(16.dp))
            Button(onClick = onPracticeClick, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White)) {
                Text("🏋️ Practice this Number!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable private fun PAORow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = LightGrey, fontSize = 15.sp); Text(value, color = White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    }
}
@Composable private fun FallbackCircle(text: String) { Box(modifier = Modifier.size(160.dp).clip(RoundedCornerShape(80.dp)).background(DarkSurface), contentAlignment = Alignment.Center) { Text(text, color = Orange, fontSize = 18.sp, fontWeight = FontWeight.Bold) } }
