package com.example.progassign2.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students_table")
public class Students {
    @PrimaryKey
    public int student_id;
    @ColumnInfo(name="name")
    public String studentName;
    @ColumnInfo(name="surname")
    public String studentSurname;
    @ColumnInfo(name="gpa")
    public String gpa;

    public String getName()
    {
        return studentName;
    }

    public String getStudentSurname()
    {
        return studentSurname;
    }

    public int getStudent_id()
    {
        return student_id;
    }



    public Students(int student_id, String studentName, String studentSurname, String gpa) {
        this.student_id = student_id;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.gpa = gpa;
    }
}
