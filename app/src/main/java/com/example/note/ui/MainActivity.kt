package com.example.note.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.note.R
import com.example.note.db.NoteDatabase
import com.example.note.repository.NoteRepository
import com.example.note.util.toast
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var viewModel: NoteViewModel // To access this in fragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupViewModel()
    }

    private fun setupViewModel() {
        try {
            val repository = NoteRepository(NoteDatabase.getInstance(this@MainActivity)!!)
            val viewModelFactory = NoteViewModelFactory(repository)

            viewModel = ViewModelProvider(this@MainActivity, viewModelFactory)[NoteViewModel::class.java]
        } catch (e: Exception) {
            toast("An error occurred.")
        }
    }

    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration)
    }
}
