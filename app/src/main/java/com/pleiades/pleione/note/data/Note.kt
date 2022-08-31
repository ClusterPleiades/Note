package com.pleiades.pleione.note.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val time: Long,
    @ColumnInfo var title: String,
    @ColumnInfo var summary: String,
    @ColumnInfo var contents: String
)