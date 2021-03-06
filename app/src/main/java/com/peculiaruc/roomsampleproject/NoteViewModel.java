package com.peculiaruc.roomsampleproject;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public LiveData<List<Note>> getAllNote;
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

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroy");
    }

    private class OperationsAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao mAsyncTaskDao;

        OperationsAsyncTask(NoteDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(NoteDao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {
        UpdateAsyncTask(NoteDao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {
        public DeleteAsyncTask(NoteDao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.delete(notes[0]);
            return null;
        }
    }
}
