package com.peculiaruc.roomsampleproject.ui.editnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.peculiaruc.roomsampleproject.Note;
import com.peculiaruc.roomsampleproject.R;

public class EditNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATED_NOTE = "note_text";
    EditNoteViewModel editNoteModel;
    //   static final String UPDATED_NOTE = "note_text";
    private EditText meditNote;
    private Bundle bundle;
    private String noteId;
    private LiveData<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        meditNote = findViewById(R.id.editText_editnote);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            noteId = bundle.getString("note_Id");
        }

        editNoteModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);
        note = editNoteModel.getNote(noteId);
        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                meditNote.setText(note.getId());
            }
        });
    }

    public void updateNote(View view) {
        String updataNote = meditNote.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(NOTE_ID, noteId);
        resultIntent.putExtra(UPDATED_NOTE, updataNote);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }

}
