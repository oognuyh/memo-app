package com.example.note.ui.adapters

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.note.R
import com.example.note.model.Note
import com.example.note.ui.HomeFragmentDirections
import com.example.note.ui.SearchViewListener
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_home_note.view.*

class NoteItem(private val note: Note, private val listener: SearchViewListener) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_note_title.text = note.title
            tv_note_content.text = note.content
            tv_note_date.text = note.date

            setupScroll(this)

            folding_cell.setOnClickListener{
                folding_cell.toggle(false)
            }

            // change framelayout to textview cuz of the scrolling method
            tv_note_content.setOnClickListener {
                folding_cell.toggle(false)
            }

            tv_note_content.setOnLongClickListener {
                // pass the note to edit
                listener.collapseSearchView()
                val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(note)
                findNavController().navigate(directions)
               return@setOnLongClickListener true
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupScroll(view: View) {
        // to scroll the content text(ref - https://gist.github.com/hilfritz/23ae7e8072de6f9ff1577a439828e016)
        view.tv_note_content.movementMethod = ScrollingMovementMethod.getInstance()
        view.tv_note_content.setOnTouchListener { v, event ->
            val isLarger = (v as TextView).lineCount * v.lineHeight > v.height
            if (event.action == MotionEvent.ACTION_MOVE && isLarger) {
                v.parent.requestDisallowInterceptTouchEvent(true)
            }
            else {
                v.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }
    }

    override fun getLayout(): Int = R.layout.item_home_note
}