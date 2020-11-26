package com.example.notes_20

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes_table")

class Entry(@ColumnInfo(name="DATA") var text:String,
            @ColumnInfo(name="TIME") var time: String,
            @ColumnInfo(name="PRIORITY") var priority:Int,
            @PrimaryKey(autoGenerate = true) @ColumnInfo(name="ID") var id: Int =0
            ) {

}