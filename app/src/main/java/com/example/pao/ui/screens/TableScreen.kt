package com.example.pao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pao.data.PAO_DATA
import com.example.pao.ui.theme.*

@Composable
fun TableScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(8.dp)
    ) {
        Text(
            "📊 Master PAO Table",
            color = Orange,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(PAO_DATA) { entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (entry.number.toInt() % 2 == 0) Color(0xFF1A1A1A) else DarkSurface)
                        .padding(10.dp)
                ) {
                    Text(
                        entry.number,
                        color = Orange,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.width(36.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(entry.person, color = White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Text("${entry.action} ${entry.objectName}", color = LightGrey, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
