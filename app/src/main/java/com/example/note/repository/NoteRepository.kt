package com.example.note.repository

import com.example.note.model.Note
import com.example.note.db.NoteDatabase

class NoteRepository(private val db: NoteDatabase) {

    fun getAllNotes() = db.getNoteDao().getAllNotes()

    fun search(query: String) = db.getNoteDao().search(query)

    suspend fun delete(note: Note) = db.getNoteDao().delete(note)

    suspend fun update(note: Note) = db.getNoteDao().update(note)

    suspend fun add(note: Note) = db.getNoteDao().insert(note)

}