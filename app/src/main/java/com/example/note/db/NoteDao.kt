package com.example.note.db

import androidx.room.*
import com.example.note.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notes")
    fun getAllNotes(): List<Note>

}