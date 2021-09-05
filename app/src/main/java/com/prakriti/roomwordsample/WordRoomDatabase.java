package com.prakriti.roomwordsample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
// Room database class must be abstract and extend RoomDatabase

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // Create an ExecutorService with a fixed thread pool that you will use to run database operations asynchronously on a background thread.

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
        // getDatabase returns the singleton.
        // It'll create the database the first time it's accessed,
        // using Room's database builder to create a RoomDatabase object in the application context from WordRoomDatabase class and names it "word_database".
    }
}

/*
Room is a database layer on top of an SQLite database. It uses the DAO to issue queries to its database.
By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the main thread.
When Room queries return LiveData, the queries are automatically run asynchronously on a background thread.
Room provides compile-time checks of SQLite statements.
 */
