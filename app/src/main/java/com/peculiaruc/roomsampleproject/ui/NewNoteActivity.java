package com.peculiaruc.roomsampleproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.peculiaruc.roomsampleproject.R;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";
    private EditText noteedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

       noteedittext = findViewById(R.id.editText_note);
        Button notebutton = findViewById(R.id.button_addnote);

        notebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(noteedittext.getText())){
                    setResult(RESULT_CANCELED, resultIntent);
                }else{
                    String note = noteedittext.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK, resultIntent);
                }

//                Intent resitIntent = getIntent();

                finish();

            }
        });

    }

}
