package com.merzayc.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.Toast;

import com.merzayc.viewpager.adapters.ImageAdapter;
import com.merzayc.viewpager.util.DatabaseHandler;
import com.merzayc.viewpager.util.Note;
import java.util.List;


public class NoteEditActivity extends MainActivity {


    int position;
    DatabaseHandler db = new DatabaseHandler(this);
    private List<Note> list;
    private Gallery mGallery;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_fragment);

        position = getIntent().getIntExtra("position", position);
        list = db.getAllNotes();
        final Note note = getNote(position);
        final EditText noteEditTitle = (EditText) findViewById(R.id.new_note_edit_title);
        final EditText noteEditData = (EditText) findViewById(R.id.new_note_edit_data);

        mGallery = (Gallery) findViewById(R.id.gallery);
        mImageAdapter = new ImageAdapter(this);
        mGallery.setAdapter(mImageAdapter);

        noteEditTitle.setText(note.get_title());
        noteEditData.setText(note.get_data());

        Button btnSend = (Button) findViewById(R.id.new_note_btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.set_data(noteEditData.getText().toString());
                note.set_title(noteEditTitle.getText().toString());
                note.set_icon(mImageAdapter.getResourceId(mGallery.getSelectedItemPosition()));

                db.updateNote(note);

                noteEditTitle.setText("");
                noteEditData.setText("");

                Toast.makeText(v.getContext(), "Note edit", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

            }
        });


    }

    public Object getItem(int position) {
        return list.get(position);
    }

    private Note getNote(int position) {
        return (Note) getItem(position);
    }
}
