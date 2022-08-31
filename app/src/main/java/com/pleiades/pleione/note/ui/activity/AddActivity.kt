@file:OptIn(ExperimentalMaterial3Api::class)

package com.pleiades.pleione.note.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pleiades.pleione.note.R
import com.pleiades.pleione.note.ui.theme.*

class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                ComposeScaffold()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposePreview() {
    MainTheme {
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ComposeScaffold() {
    val activity = LocalContext.current as Activity
    var title by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            Surface(modifier = Modifier.fillMaxWidth(), color = PurpleWhite) {
                Row {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Purple
                        )
                    }
                    BasicTextField(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        value = title,
                        onValueChange = { title = it },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(),
                        decorationBox = { innerTextField ->
                            if (title.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.no_named),
                                    style = MaterialTheme.typography.bodyLarge.copy(),
                                    color = Purple
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
        }
    ) {
        ComposeContent()
    }
}

@Composable
private fun ComposeContent() {
    val activity = LocalContext.current as Activity
    var content by rememberSaveable { mutableStateOf("") }

    Surface(color = PurpleWhite) {
        Column {
            Surface(
                modifier = Modifier
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f)
                    .fillMaxSize(),
                color = PurpleLight
            ) {
                BasicTextField(
                    modifier = Modifier.padding(all = 16.dp),
                    value = content,
                    onValueChange = { content = it },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(),
                    decorationBox = { innerTextField ->
                        if (content.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.content),
                                style = MaterialTheme.typography.bodyLarge.copy(),
                                color = Purple
                            )
                        }
                        innerTextField()
                    }
                )
            }
            Row(modifier = Modifier.padding(all = 12.dp)){
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = stringResource(R.string.edit),
                        tint = Purple
                    )
                }
                IconButton(onClick = { activity.finish() }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.delete),
                        tint = Purple
                    )
                }
            }
        }
    }
}

@Composable
private fun ComposeButton() {

}