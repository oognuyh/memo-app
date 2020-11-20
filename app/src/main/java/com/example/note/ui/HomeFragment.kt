package com.example.note.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.model.Note
import com.example.note.utils.toNoteItems
import com.example.note.viewmodel.NoteViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_note.*

class HomeFragment: Fragment(R.layout.fragment_home), View.OnClickListener {
    private lateinit var viewModel: NoteViewModel
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFloatingActionButton()
        setupRecyclerView()

        val activity = activity as MainActivity
        viewModel = activity.viewModel
        viewModel.getAllNotes().observe(viewLifecycleOwner) {notes ->
            adapter.updateAsync(notes.toNoteItems())
        }
    }
    // todo - implement SWIPE TO DELETE & SEARCH

    private fun setupRecyclerView() {
        rv_home_notes.adapter = adapter
    }

    private fun setupFloatingActionButton() {
        // change this with databinding
        fab_home_add.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // todo - pass a parcelled data -> add new one if null else edit an existed
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        findNavController().navigate(directions)
    }
}