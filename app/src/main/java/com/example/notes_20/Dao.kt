package com.example.notes_20

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao

interface Dao {

    @Query("SELECT * FROM notes_table ORDER BY PRIORITY ASC")
    fun getDataPriorityWise() : LiveData<List<Entry>>

    @Query("SELECT * FROM notes_table ORDER BY ID DESC")
    fun getDataTimeWise(): LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEntry(entry: Entry)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("UPDATE notes_table SET DATA =:text WHERE ID = :id")
    fun update(text:String, id:Int)

}