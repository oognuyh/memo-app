package com.example.note.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.Toast
import com.example.note.model.Note
import com.example.note.ui.adapters.NoteItem

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun List<Note>.toNoteItems() : List<NoteItem> {
    return this.map {
        NoteItem(it)
    }
}

fun View.hideKeyboard() {
    (context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, HIDE_NOT_ALWAYS)
}