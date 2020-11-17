package com.example.note.ui

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*

/*
 * 노트 목록 출력 및 삭제 기능
 */

class HomeFragment: Fragment(R.layout.fragment_home), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_home_add.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
    }
}