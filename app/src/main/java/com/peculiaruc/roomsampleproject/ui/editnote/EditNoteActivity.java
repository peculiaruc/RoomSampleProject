package com.peculiaruc.roomsampleproject.ui.editnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.peculiaruc.roomsampleproject.R;

public class EditNoteActivity extends AppCompatActivity {

    private EditText meditText;
    EditNoteViewModel editNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        meditText = findViewById(R.id.editText_editnote);
        editNoteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);
    }

    public void updateNote(View view) {

    }

    public void cancelUpdate(View view) {
    }

}
