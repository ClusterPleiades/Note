package com.pleiades.pleione.note.ui.activity

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.pleiades.pleione.note.Config.Companion.TABLE_NAME
import com.pleiades.pleione.note.data.Note
import com.pleiades.pleione.note.data.NoteDatabase
import kotlinx.coroutines.flow.Flow

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDatabase = Room.databaseBuilder(getApplication<Application>().applicationContext, NoteDatabase::class.java, TABLE_NAME).build()
    private val noteDao = noteDatabase.noteDao()
    val notes = noteDao.getNotes()

    fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    private fun temp(): List<Note> {
        val arrayList = arrayListOf<Note>()
        for (i in 1..5) arrayList.add(
            Note(
                123123123,
                "Title",
                "I am chicken summary.",
                "temp contents temp contents temp contents temp contents temp contents temp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contentstemp contents"
            )
        )
        return arrayList
    }
}