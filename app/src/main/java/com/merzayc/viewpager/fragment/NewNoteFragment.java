package com.merzayc.viewpager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;

import com.merzayc.viewpager.MainActivity;
import com.merzayc.viewpager.R;
import com.merzayc.viewpager.adapters.ImageAdapter;
import com.merzayc.viewpager.util.DatabaseHandler;
import com.merzayc.viewpager.util.Note;


public class NewNoteFragment extends Fragment {


    public static final String TAG = "NewNoteFragmentTag";
    private Gallery mGallery;
    private ImageAdapter mImageAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_note_fragment, null);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mGallery = (Gallery)getActivity().findViewById(R.id.gallery);
        mImageAdapter = new ImageAdapter(getActivity());
        mGallery.setAdapter(mImageAdapter);

        Button btnSend = (Button) getActivity().findViewById(R.id.new_note_btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getActivity());

                EditText noteTitle = (EditText) getActivity().findViewById(R.id.new_note_edit_title);
                EditText noteData = (EditText) getActivity().findViewById(R.id.new_note_edit_data);


                if (noteTitle.getText().toString().equals("") || noteData.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Field is not filled", Toast.LENGTH_LONG).show();


                else {
                    Note newNote = new Note();
                    newNote.set_data(noteData.getText().toString());
                    newNote.set_title(noteTitle.getText().toString());
                    newNote.set_date(System.currentTimeMillis());
                    newNote.set_icon(mImageAdapter.getResourceId(mGallery.getSelectedItemPosition()));
                    db.addNote(newNote);

                    noteTitle.setText("");
                    noteData.setText("");

                    Toast.makeText(getActivity(), "Note added", Toast.LENGTH_LONG).show();

                    TextView textView = (TextView) getActivity().findViewById(R.id.txt_havent_notes);
                    textView.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    v.getContext().startActivity(intent);
                }
            }


        });
    }
}