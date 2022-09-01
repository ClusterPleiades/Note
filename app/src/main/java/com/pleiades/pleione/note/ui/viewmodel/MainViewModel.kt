package com.pleiades.pleione.note.ui.viewmodel

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.pleiades.pleione.note.Config.Companion.TABLE_NAME
import com.pleiades.pleione.note.data.Note
import com.pleiades.pleione.note.data.NoteDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDatabase = Room.databaseBuilder(getApplication<Application>().applicationContext, NoteDatabase::class.java, TABLE_NAME).build()
    private val noteDao = noteDatabase.noteDao()
    val notes = noteDao.getNotes()

    @WorkerThread
    fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    @WorkerThread
    fun update(note: Note) {
        noteDao.updateNote(note)
    }

    @WorkerThread
    fun delete(note: Note) {
        noteDao.deleteNote(note)
    }
}