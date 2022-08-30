package com.pleiades.pleione.note.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.pleiades.pleione.note.ui.theme.NoteTheme
import com.pleiades.pleione.note.ui.theme.Purple
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pleiades.pleione.note.R
import com.pleiades.pleione.note.data.Note
import com.pleiades.pleione.note.ui.theme.PurpleLight
import com.pleiades.pleione.note.ui.theme.PurpleWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoteTheme {
                DrawScaffold()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawPreview() {
    NoteTheme {
        DrawScaffold()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawScaffold(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { /* TODO */ },
                shape = RoundedCornerShape(128.dp),
                containerColor = Purple,
            ) {
                Icon(Icons.Filled.Add, "", tint = PurpleWhite)
            }
        }
    ) {
//        val arrayList = arrayListOf<String>()
//        for (i in 1..5) arrayList.add(i.toString())
        DrawLazyColumn(viewModel.notes)
    }
}

@Composable
fun DrawLazyColumn(notes: List<Note>) {
    Surface(color = PurpleWhite) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = notes) { note ->
                    DrawLazyItem(title = note.title, summary = note.summary, contents = note.contents)
                }
            }
        }
    }
}

@Composable
fun DrawLazyItem(title: String, summary: String, contents: String) {
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
                        text = title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(text = summary)
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
                Text(modifier = Modifier.padding(end = 8.dp), text = contents)
                Row(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = stringResource(R.string.edit),
                            tint = Purple
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
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