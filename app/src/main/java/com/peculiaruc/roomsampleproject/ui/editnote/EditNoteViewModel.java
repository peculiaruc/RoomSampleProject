package com.peculiaruc.roomsampleproject.ui.editnote;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.peculiaruc.roomsampleproject.Note;
import com.peculiaruc.roomsampleproject.NoteDao;
import com.peculiaruc.roomsampleproject.NoteRoomDatabase;

public class EditNoteViewModel extends AndroidViewModel {

    private String TAG = getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase db;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Edit ViewModel");
        db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
    }

    public LiveData<Note> getNote(String noteId) {
        return noteDao.getNote(noteId);
    }
}
