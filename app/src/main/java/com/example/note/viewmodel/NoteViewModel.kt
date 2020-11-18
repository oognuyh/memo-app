package com.example.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.model.Note
import com.example.note.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun save(note: Note) = viewModelScope.launch {
        repository.add(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    // Livedata in Room is already asynchronous so it will work just fine in the background
    fun getAllNotes() = repository.getAllNotes()

    fun search(query: String) = repository.search(query)

}