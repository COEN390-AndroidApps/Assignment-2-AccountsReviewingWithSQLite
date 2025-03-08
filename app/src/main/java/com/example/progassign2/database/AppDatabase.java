package com.example.progassign2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.progassign2.database.dao.StudentsDao;
import com.example.progassign2.database.entities.Students;

@Database(entities = {Students.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase instance;
    private static final String DB_NAME= "students_database";
    protected AppDatabase(){}

    private static AppDatabase create(Context context){
        return Room.databaseBuilder(context, AppDatabase.class,DB_NAME).allowMainThreadQueries() .build();
    }

    public static synchronized AppDatabase getInstance(Context context)
    {
        if(instance ==null)
        {
            instance = create(context);
        }
        return instance;
    }

    public abstract StudentsDao studentsDao();
}
