package com.example.progassign2.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "access_log")
public class Access {

    @PrimaryKey(autoGenerate = true)
    public int log_id;

    @ColumnInfo(name = "student_id")
    public int studentId;

    @ColumnInfo(name = "timestamp")
    public long timestamp;  // Stores the event time in milliseconds.

    @ColumnInfo(name = "action")
    public String action; // CREATED, ACCESSED, CLOSED

    public Access(int studentId, long timestamp, String action) {
        this.studentId = studentId;
        this.timestamp = timestamp;
        this.action = action;
    }
}