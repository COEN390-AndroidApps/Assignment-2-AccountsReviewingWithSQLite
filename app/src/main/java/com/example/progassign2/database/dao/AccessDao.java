package com.example.progassign2.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.progassign2.database.entities.Access;

import java.util.List;

@Dao
public interface AccessDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLog(Access log);

    @Query("SELECT * FROM access_log WHERE student_id = :studentId ORDER BY timestamp DESC")
    List<Access> getLogsForStudent(int studentId);

    @Query("DELETE FROM access_log WHERE student_id = :studentId")
    void deleteLogsForStudent(int studentId);
}