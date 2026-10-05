package com.example.pao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.ui.theme.*

@Composable
fun HowToScreen() {
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {
        Text("🧠 How to Memorize 00–99", color = Orange, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        Section("1. The PAO System",
            "PAO stands for Person - Action - Object. Every 2-digit number (00–99) becomes a vivid mental image.\n\nExample: 08 = Mario (Person) → Stomping (Action) → a Goomba (Object).")

        Section("2. The Shape Rule",
            "Connect the shape of the number to the image:\n\n• 08: The '0' is a mushroom, the '8' is two stacked mushrooms. Mario stomps the top one.\n\n• 07: The '7' is a rope. Wonder Woman lassoes a round tank wheel.\n\n• 02: The '2' sounds like 'To'. James Bond counts down 'To' the explosion.")

        Section("3. The Memory Palace",
            "Imagine walking through a place you know well (like your house). Place each PAO image in a specific spot. The weirder the image, the better you'll remember it.")

        Section("4. How to Use This App",
            "📖 Teach: Read the bio and the memory hook.\n\n🃏 Flashcards: Test yourself before revealing.\n\n🧪 Quiz: Type the full PAO and track your score.\n\n📊 Table: Review all 100 entries at a glance.")

        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun Section(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(title, color = Orange, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(body, color = Color(0xFFDDDDDD), fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(Modifier.height(12.dp))
        Text("─────", color = Color(0xFF333333), fontSize = 12.sp)
    }
}
