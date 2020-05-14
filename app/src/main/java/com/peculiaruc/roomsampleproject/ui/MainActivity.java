package com.peculiaruc.roomsampleproject.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.peculiaruc.roomsampleproject.Adapter.NoteListAdapter;
import com.peculiaruc.roomsampleproject.Note;
import com.peculiaruc.roomsampleproject.NoteViewModel;
import com.peculiaruc.roomsampleproject.R;
import com.peculiaruc.roomsampleproject.ui.editnote.EditNoteActivity;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListener {

    private static final int NEW_NOTE_ACTIVITY_REQUEST__CODE_ = 1;
    public static final int UPDATE_NOTE_ACTIVITY_REQUEST__CODE_ = 2;
    private String TAG = this.getClass().getSimpleName();
    private NoteViewModel noteViewModel;
    private NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        noteListAdapter = new NoteListAdapter(this, this);
        recyclerView.setAdapter(noteListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fabintent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(fabintent, NEW_NOTE_ACTIVITY_REQUEST__CODE_);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNote.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteListAdapter.setNotes(notes);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Code to insert note
        final  String note_id = UUID.randomUUID().toString();
       Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
       noteViewModel.insert(note);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST__CODE_ && requestCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST__CODE_ && requestCode == RESULT_OK) {
            // Code to update Note
            Note note1 = new Note(
                    data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATED_NOTE));
            noteViewModel.update(note1);

            Toast.makeText(getApplicationContext(), R.string.updated,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.not_saved, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnDeleteClickListener(Note myNote) {
        noteViewModel.delete(myNote);

    }
}
