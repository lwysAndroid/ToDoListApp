package com.example.todolistapp.features.seenotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.core.model.NoteModel

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notesList = mutableListOf<NoteModel>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteTitleTv: TextView
        val noteMessageTv: TextView

        init {
            noteTitleTv = view.findViewById(R.id.noteTitleTv)
            noteMessageTv = view.findViewById(R.id.noteMessageTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.noteTitleTv.text = currentNote.title
        holder.noteMessageTv.text = currentNote.message
    }

    fun updateList(list: List<NoteModel>) {
        notesList.clear()
        notesList.addAll(list)
        notifyDataSetChanged()
    }

}