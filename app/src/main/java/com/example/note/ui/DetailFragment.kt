package com.example.note.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.model.Note
import com.example.note.utils.snackbar
import com.example.note.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val EDIT = 1
const val NEW = 0

class DetailFragment: Fragment(R.layout.fragment_detail) {
    private lateinit var viewModel: NoteViewModel
    private lateinit var note: Note
    private val args: DetailFragmentArgs by navArgs()
    private var MODE: Int = NEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        viewModel = activity.viewModel

        note = args.note
        if (note.date  != "") MODE = EDIT // if edit an existing note

        et_detail_content.setText(note.content)
        et_detail_title.setText(note.title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)

        menu.findItem(R.id.item_menu_search).isVisible = false
        menu.findItem(R.id.item_menu_done).isVisible = true
    }

    // if done, update or insert in Room and navigate
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_menu_done) {
            if (et_detail_title.text.isNullOrEmpty()) {
                requireView().snackbar("Please enter the title")
            }
            else {
                note.date = getDate()
                note.title = et_detail_title.text.toString()
                note.content = et_detail_content.text.toString()

                when (MODE) {
                    NEW -> viewModel.save(note)
                    EDIT -> viewModel.update(note)
                }

                requireView().snackbar("Completed")

                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDate(): String {
        val now = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }
}