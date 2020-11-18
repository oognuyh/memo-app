package com.example.note.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE :query OR content LIKE :query OR date LIKE :query ORDER BY id DESC")
    fun search(query: String): LiveData<List<Note>>

}