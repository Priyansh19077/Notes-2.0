package com.example.notes_20

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application:Application): AndroidViewModel(application) {
    val repo:NoteRepo;
    val allNotesTime: LiveData<List<Entry>>
    val allNotesPriority: LiveData<List<Entry>>
    init{
        val dao=NoteDatabase.getDatabase(application).getNoteDao();
        repo=NoteRepo(dao);
        allNotesTime=repo.allNotesTime;
        allNotesPriority=repo.allNotesPriority;
    }
    fun addNote(note: Entry) = viewModelScope.launch (Dispatchers.IO){
        repo.insert(note);
    }
    fun deleteNote(note:Entry) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note);
    }
    fun updateNote(text:String, id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(text, id);
    }
}