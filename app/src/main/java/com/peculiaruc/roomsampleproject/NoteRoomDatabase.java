package com.peculiaruc.roomsampleproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    private NoteDao noteDao;
    private static NoteRoomDatabase noteRoomInstance;

    public static NoteRoomDatabase getDatabase(final Context context){
        if (noteRoomInstance == null){
            synchronized (NoteRoomDatabase.class){
                if (noteRoomInstance == null){
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                           NoteRoomDatabase.class, "note_database" )
                    .build();
                }
            }
        }
        return noteRoomInstance;
    }

    public abstract NoteDao noteDao();

}
