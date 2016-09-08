package com.merzayc.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.merzayc.viewpager.util.DatabaseHandler;
import com.merzayc.viewpager.util.Note;

import java.text.SimpleDateFormat;
import java.util.List;


public class NoteViewActivity extends MainActivity{

    int position;
    DatabaseHandler db = new DatabaseHandler(this);
    private List<Note> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);




        position = getIntent().getIntExtra("position", position);
        list = db.getAllNotes();
        final Note note = getNote(position);

     //   Log.v("XXX", "position " + position);
     //   Log.v("XXX", "intent " + getIntent().getIntExtra("position", position));


        TextView noteTitle = (TextView) findViewById(R.id.view_note_title);
        noteTitle.setText(note.get_title());

        TextView noteData = (TextView) findViewById(R.id.view_note_data);
        noteData.setText(note.get_data());

        TextView noteDate = (TextView) findViewById(R.id.view_note_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        noteDate.setText(dateFormat.format(note.get_date()));

        ImageView icon = (ImageView) findViewById(R.id.icon);
        icon.setImageResource(note.get_icon());


        Button btnDelete  = (Button) findViewById(R.id.view_note_btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(getNote(position));
                list.remove(position);







                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

            }
        });

        //EDIT NOTE

        Button btnEdit = (Button) findViewById(R.id.view_note_btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteEditActivity.class);
                intent.putExtra("position", position);
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
