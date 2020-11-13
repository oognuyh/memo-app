package com.example.note.repository

import com.example.note.model.Note
import com.example.note.db.NoteDatabase

class NoteRepository(private val db: NoteDatabase) {

    fun getNote() = db.getNoteDao().getAllNotes()

    fun deleteNote(note: Note) = db.getNoteDao().delete(note)

    fun updateNote(note: Note) = db.getNoteDao().update(note)

    fun addNote(note: Note) = db.getNoteDao().insert(note)

}