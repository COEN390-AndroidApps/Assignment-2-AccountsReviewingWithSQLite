package com.example.progassign2.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.progassign2.database.entities.Students;

import java.util.List;

@Dao
public interface StudentsDao {

    @Query("SELECT * FROM `students_table `")
    List<Students> getAll();

    @Query("SELECT * FROM `students_table ` WHERE student_id=:id  ")
    Students findById(String id);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Students student);
}
