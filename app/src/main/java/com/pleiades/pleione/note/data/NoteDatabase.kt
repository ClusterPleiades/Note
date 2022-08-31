package com.pleiades.pleione.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pleiades.pleione.note.Config.Companion.DATABASE_NAME

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, DATABASE_NAME).build()
                this.instance = instance
                instance
            }
        }
    }
}