package com.pleiades.pleione.note.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note") // ORDER BY time ASC
    fun getNotes() : Flow<List<Note>>

    @Insert
    fun insertNote(note : Note)

    @Update
    fun updateNote(note : Note)

    @Delete
    fun deleteNote(note : Note)
}