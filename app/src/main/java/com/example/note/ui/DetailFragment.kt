package com.example.note.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.model.Note
import com.example.note.utils.hideKeyboard
import com.example.note.utils.toast
import com.example.note.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val EDIT = 1
const val NEW = 0

class DetailFragment: Fragment(R.layout.fragment_detail) {
    private val TAG = javaClass.simpleName

    private lateinit var viewModel: NoteViewModel
    private lateinit var note: Note
    private val args: DetailFragmentArgs by navArgs()
    private var MODE: Int = NEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    // if the passed data is null, create new
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        viewModel = activity.viewModel

        if (args.note == null) {
            // if create new
            note = Note(date = getDate(), title = "")
            MODE = EDIT
        }
        else {
            // if edit an existing note
            note = args.note!!
            tv_detail_content.setText(note.content)
            tv_detail_title.setText(note.title)
        }
        Log.d(TAG, note.toString())
    }

    // if done, update or insert in Room and navigate
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_menu_done) {
            note.date = getDate()
            note.title = tv_detail_title.text.toString()
            note.content = tv_detail_title.text.toString()

            if (MODE == NEW) {
                viewModel.update(note)
            }
            else {
                viewModel.save(note)
            }
            CoroutineScope(Dispatchers.Main).launch {
                context?.toast("Completed")
            }
            requireView().hideKeyboard()
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDate(): String {
        val now = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }

    // To hide the search icon
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.item_menu_search).isVisible = false
        menu.findItem(R.id.item_menu_done).isVisible = true
    }
}