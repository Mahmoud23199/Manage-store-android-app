package com.example.sqllite.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqllite.pojo.data;

@Database(entities = data.class,version = 10)
    public abstract class BooksDatabase extends RoomDatabase {
    private static BooksDatabase instance ;
    public abstract BooksDao booksDao();

    public static synchronized BooksDatabase getInstance(Context context)
    {
        if (instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,BooksDatabase.class,"Books_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
