package com.example.notes_20

import androidx.lifecycle.LiveData

class NoteRepo(private val noteDao: Dao) {
    val allNotesTime: LiveData<List<Entry>> = noteDao.getDataTimeWise();
    val allNotesPriority: LiveData<List<Entry>> = noteDao.getDataPriorityWise();
    suspend fun insert(entry: Entry){
        noteDao.insertEntry(entry);
    }
    suspend fun delete(entry: Entry){
        noteDao.deleteEntry(entry);
    }
    suspend fun deleteAll(){
        noteDao.deleteAll();
    }
    suspend fun update(text:String, id:Int){
        noteDao.update(text, id);
    }
}