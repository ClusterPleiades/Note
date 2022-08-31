package com.pleiades.pleione.note.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.pleiades.pleione.note.R
import com.pleiades.pleione.note.ui.theme.Purple
import com.pleiades.pleione.note.ui.theme.PurpleLight
import com.pleiades.pleione.note.ui.theme.SubTheme

class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubTheme {
                DrawScaffold()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawPreview() {
    SubTheme {

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun DrawScaffold() {
    val activity = LocalContext.current as Activity

    Scaffold(
        topBar = {
            Surface(modifier = Modifier.fillMaxWidth(), color = PurpleLight) {
                Row {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Purple
                        )
                    }
                }
            }
        }
    ) {
        DrawContent()
    }
}

@Composable
private fun DrawContent() {

}