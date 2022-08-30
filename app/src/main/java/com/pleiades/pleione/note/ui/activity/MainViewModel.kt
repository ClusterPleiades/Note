package com.pleiades.pleione.note.ui.activity

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.pleiades.pleione.note.data.Note

class MainViewModel : ViewModel() {
    val notes = getNotes().toMutableStateList()

    fun remove() {

    }

    private fun getNotes(): List<Note> {
        val arrayList = arrayListOf<Note>()
        for (i in 1..5) arrayList.add(Note("Title", "I am chicken summary.", "temp contents temp contents temp contents temp contents temp contents temp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contents"))
        return arrayList
    }
}