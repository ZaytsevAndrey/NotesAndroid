package com.merzayc.viewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.merzayc.viewpager.adapters.NoteAdapter;
import com.merzayc.viewpager.R;
import com.merzayc.viewpager.util.DatabaseHandler;


public class ListFragment extends Fragment {

    public static final String TAG = "ListFragmentTag";
    private ListView listView;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = (TextView)getActivity().findViewById(R.id.txt_havent_notes);
        listView = (ListView)getActivity().findViewById(R.id.listView);


        DatabaseHandler db = new DatabaseHandler(getActivity());
        //Log.v("XXX", "check count "+db.getNoteCount());
         if (db.getNoteCount() != 0)
         {
          //   Log.v("XXX", "check count CHECK "+db.getNoteCount());
             textView.setVisibility(View.INVISIBLE);
             NoteAdapter adapter = new NoteAdapter(getActivity().getApplicationContext(), db);
             listView.setAdapter(adapter);
            // Log.v("XXX", "check count CHECK "+db.getNoteCount());

         }

       else{
             textView.setVisibility(View.VISIBLE);
         }

    }


}
