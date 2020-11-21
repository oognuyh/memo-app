package com.example.note.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.model.Note
import com.example.note.utils.toNoteItems
import com.example.note.utils.toast
import com.example.note.viewmodel.NoteViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(R.layout.fragment_home), View.OnClickListener, SearchViewListener {
    private lateinit var viewModel: NoteViewModel
    private var adapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var search: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFloatingActionButton()
        setupRecyclerView()

        val activity = activity as MainActivity
        viewModel = activity.viewModel

        observeAllNotes()
    }

    private fun observeAllNotes() {
        viewModel.getAllNotes().observe(viewLifecycleOwner) {notes ->
            adapter.updateAsync(notes.toNoteItems(this))
        }
    }

    private fun observeSearchedNotes(text: String) {
        val query = "%$text%"
        viewModel.search(query).observe(viewLifecycleOwner) {notes ->
            adapter.updateAsync(notes.toNoteItems(this))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.item_menu_done).isVisible = false

        search = menu.findItem(R.id.item_menu_search)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                collapseSearchView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty() and newText.isNotBlank()) {
                        context?.toast("not null or blank")
                        observeSearchedNotes(newText)
                    }
                    else {
                        observeAllNotes()
                    }
                }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        rv_home_notes.adapter = adapter
    }

    private fun setupFloatingActionButton() {
        // change this with databinding
        fab_home_add.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        collapseSearchView()
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(Note())
        findNavController().navigate(directions)
    }

    override fun collapseSearchView() {
        search.collapseActionView()
    }
}