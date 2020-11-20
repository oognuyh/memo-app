package com.example.note.ui.adapters

import androidx.navigation.findNavController
import com.example.note.R
import com.example.note.model.Note
import com.example.note.ui.DetailFragmentArgs
import com.example.note.ui.DetailFragmentDirections
import com.example.note.ui.HomeFragmentDirections
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_home_note.*
import kotlinx.android.synthetic.main.item_home_note.view.*

class NoteItem(private val note: Note) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_note_title.text = note.title
            tv_note_content.text = note.content
            tv_note_date.text = note.date
            fl_note_in.isClickable = true
            fl_note_in.setOnClickListener {
                // pass the note to edit
                val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(note)
                findNavController().navigate(directions)
            }
            folding_cell.setOnClickListener{
                folding_cell.toggle(false)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_home_note
}