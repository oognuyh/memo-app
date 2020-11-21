package com.example.note.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.note.model.Note
import com.example.note.ui.SearchViewListener
import com.example.note.ui.adapters.NoteItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: CharSequence) {
    CoroutineScope(Dispatchers.Main).launch {
        Snackbar.make(this@snackbar, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun List<Note>.toNoteItems(listener: SearchViewListener) : List<NoteItem> {
    return this.map {
        NoteItem(it, listener)
    }
}