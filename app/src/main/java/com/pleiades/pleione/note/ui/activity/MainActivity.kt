@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.pleiades.pleione.note.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pleiades.pleione.note.Config.Companion.CONTENT
import com.pleiades.pleione.note.Config.Companion.TITLE
import com.pleiades.pleione.note.R
import com.pleiades.pleione.note.data.Note
import com.pleiades.pleione.note.ui.theme.*
import com.pleiades.pleione.note.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
        ComposeScaffold()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ComposeScaffold(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val defaultTitle = stringResource(id = R.string.untitled)
    val defaultContent = stringResource(id = R.string.content)
    val coroutineScope = rememberCoroutineScope()
    val addResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data!!
            val title = data.getStringExtra(TITLE) ?: defaultTitle
            val content = data.getStringExtra(CONTENT) ?: defaultContent
            val summary = content.split("\\n")[0]

            coroutineScope.launch(Dispatchers.IO) {
                viewModel.insert(Note(System.currentTimeMillis(), title, summary, content))
            }
        }
    }

    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    addResultLauncher.launch(Intent(context, AddActivity::class.java))
                },
                shape = RoundedCornerShape(128.dp),
                containerColor = Purple,
            ) {
                Icon(Icons.Filled.Add, "", tint = PurpleWhite)
            }
        }
    ) {
        val notes by viewModel.notes.collectAsState(initial = emptyList())
        ComposeNotes(notes, viewModel)
    }
}

@Composable
private fun ComposeNotes(notes: List<Note>, viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Surface(color = PurpleWhite) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp),
            color = PurpleWhite
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(items = notes) { _, note ->
                    ComposeNotesItem(note,
                        onDeleted = { item ->
                            coroutineScope.launch(Dispatchers.IO) {
                                viewModel.delete(item)
                            }
                        })
                }
            }
        }
    }
}

@Composable
private fun ComposeNotesItem(note: Note, onDeleted: (Note) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp)),
        color = PurpleLight
    ) {
        Column(Modifier.padding(top = 8.dp, start = 16.dp, end = 8.dp)) {
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(text = note.summary)
                }
                IconButton(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = { expanded = !expanded }) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) {
                            stringResource(R.string.Less)
                        } else {
                            stringResource(R.string.more)
                        },
                        tint = Purple
                    )
                }
            }
            if (expanded) {
                Text(modifier = Modifier.padding(end = 8.dp), text = note.content)
                Row(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)) {
                    Spacer(modifier = Modifier.weight(1f))
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Done,
//                            contentDescription = stringResource(R.string.edit),
//                            tint = Purple
//                        )
//                    }
                    IconButton(onClick = {
                        onDeleted(note)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(R.string.delete),
                            tint = Purple
                        )
                    }
                }
            }
        }
    }
}