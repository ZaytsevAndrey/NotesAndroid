package com.merzayc.viewpager.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.merzayc.viewpager.NoteViewActivity;
import com.merzayc.viewpager.R;
import com.merzayc.viewpager.util.DatabaseHandler;
import com.merzayc.viewpager.util.Note;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoteAdapter extends BaseAdapter  {

    private List<Note> list;
    private LayoutInflater layoutInflater;
    public DatabaseHandler db;





    public NoteAdapter(Context context, DatabaseHandler db) {
        this.db = db;
        this.list = db.getAllNotes();
        layoutInflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Note getNote(int position){
        return (Note) getItem(position);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        }



        Note note = getNote(position);

        //SET ICON
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(note.get_icon());





        //SET TITLE
        TextView item_title = (TextView) view.findViewById(R.id.list_item_title);
        item_title.setText(note.get_title());

        //SET DATA
      /*  TextView item_data = (TextView) view.findViewById(R.id.list_item_data);
        item_data.setText(note.get_data());*/


        //SET DATE
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        TextView item_date = (TextView) view.findViewById(R.id.list_item_date);
        item_date.setText(dateFormat.format(note.get_date()));

        //SET BTN DELETE
      /*  ImageButton imageButton = (ImageButton) view.findViewById(R.id.btn_delete_item);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(getNote(position));
                list.remove(position);
                notifyDataSetChanged();
            }
        });*/

        //SET ITEM ONCLICK
        LinearLayout rl = (LinearLayout)view.findViewById(R.id.list_item_container);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteViewActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }







}
