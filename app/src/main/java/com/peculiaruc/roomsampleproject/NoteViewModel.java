package com.peculiaruc.roomsampleproject;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase noteRoomDatabase;
    LiveData<List<Note>> mAllNotes;

    public NoteViewModel(Application application){
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
        mAllNotes = noteDao.getAllNotes();
    }
    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }
//    LiveData<List<Note>> getAllNotes(){
//        return mAllNotes;
//    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroy");
    }

    public LiveData getAllNotes() {
        return mAllNotes;
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
         NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }
}
