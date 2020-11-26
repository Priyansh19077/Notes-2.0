package com.example.notes_20

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter, private val listener2: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes=ArrayList<Entry>();
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView =itemView.findViewById<TextView>(R.id.textView);
        val deletButton= itemView.findViewById<ImageButton>(R.id.imageView)
        val editButton=itemView.findViewById<ImageButton>(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )
        viewHolder.deletButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition]);
        }
        viewHolder.editButton.setOnClickListener{
            listener2.onEditItme(allNotes[viewHolder.adapterPosition]);
        }
        return viewHolder;
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curretNote=allNotes[position];
        holder.textView.text=curretNote.text;
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Entry>){
        allNotes.clear();
        allNotes.addAll(newList);
        notifyDataSetChanged();
    }


}

interface INotesRVAdapter{
    fun onItemClicked(note: Entry);
    fun onEditItme(note: Entry);
}
