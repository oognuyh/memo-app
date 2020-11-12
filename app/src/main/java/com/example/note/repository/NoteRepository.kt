package com.example.note.repository

import com.example.note.db.Note
import com.example.note.db.NoteDatabase

class NoteRepository(val db: NoteDatabase) {

    fun getNote() = db.getNoteDao().getAllNotes()

    fun deleteNote(note: Note) = db.getNoteDao().delete(note)

    fun updateNote(note: Note) = db.getNoteDao().update(note)

    fun addNote(note: Note) = db.getNoteDao().insert(note)

}