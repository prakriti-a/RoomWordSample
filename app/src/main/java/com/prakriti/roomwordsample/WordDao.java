package com.prakriti.roomwordsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
/*
Data access object validates SQL at compile-time and associates it with a method.
Use annotations to represent database operations
Room uses the DAO to create a clean API for your code.
The DAO must be an interface or abstract class. By default, all queries must be executed on a separate thread.
 */

    // allowing the insertion of the same word multiple times by passing conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
    /*
    When data changes you have to observe the data so that when it changes, you can react.
    LiveData, a lifecycle library class for data observation, solves this problem.
    Use a return value of type LiveData and Room generates all necessary code to update the LiveData when the database is updated.
     */
}
