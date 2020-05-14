package com.peculiaruc.roomsampleproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey
    @NonNull
    private String mId;

    @ColumnInfo(name = "note")
    @NonNull
    private String mNote;

    @NonNull
    public String getId() {
        return this.mId;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }

    public Note(String id, String mNote) {
        this.mId = id;
        this.mNote = mNote;


    }
}
