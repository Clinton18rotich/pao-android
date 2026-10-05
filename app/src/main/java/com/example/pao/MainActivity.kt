package com.example.pao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pao.ui.navigation.PAONavGraph
import com.example.pao.ui.theme.PAOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAOTheme {
                PAONavGraph()
            }
        }
    }
}
