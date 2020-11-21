package com.example.note.ui

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.note.R
import com.example.note.db.NoteDatabase
import com.example.note.repository.NoteRepository
import com.example.note.utils.toast
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

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

    // To hide keyboard
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration)
    }
}
