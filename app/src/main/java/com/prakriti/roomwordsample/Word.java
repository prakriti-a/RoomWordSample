package com.prakriti.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word { // ENTITY class
    /*
    an Entity class represents an SQLite table, each property here is a table column
    room uses this class to create table & instantiate objects from rows in db
    use annotations to show entries in db, room uses them to generate code
     */

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String myWord;

    public Word(@NonNull String myWord) {
        this.myWord = myWord;
    }

    public String getMyWord() {
        return myWord;
    }

}
