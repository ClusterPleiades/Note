package com.pleiades.pleione.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pleiades.pleione.note.ui.theme.NoteTheme
import com.pleiades.pleione.note.ui.theme.Purple

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Purple) {
//                    FloatingActionButton()
                }
            }
        }
    }
}

//@Composable
//fun FloatingActionButton() {
//    Scaffold {
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {}
//            ) {
//                Icon(Icons.Filled.Add,"")
//            }
//        }
//    }
//}