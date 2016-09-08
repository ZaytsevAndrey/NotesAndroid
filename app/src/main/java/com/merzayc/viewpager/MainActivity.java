package com.merzayc.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.merzayc.viewpager.fragment.ListFragment;
import com.merzayc.viewpager.fragment.NewNoteFragment;
import com.merzayc.viewpager.util.DatabaseHandler;
import com.merzayc.viewpager.util.Note;


public class MainActivity extends FragmentActivity{

	private ViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

		
		mViewPager = (ViewPager) findViewById(R.id.pager);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setDrawFullUnderline(true);

	    
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            /*case R.id.menu_languages:
                Intent i = new Intent(mContext, AddActivity.class);
                startActivityForResult (i, ADD_ACTIVITY);
                updateList();
                return true;*/
            case R.id.menu_delete_all:
                DatabaseHandler db = new DatabaseHandler(this);
                db.deleteAll();
                Toast.makeText(this, "All notes are deleted", Toast.LENGTH_LONG).show();
                mViewPager.getAdapter().notifyDataSetChanged();
                mViewPager.setCurrentItem(1);


                return true;
            case R.id.menu_exit:


                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }








    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final String titles[] = getResources().getStringArray(R.array.data);
        private final Fragment frags[] = new Fragment[titles.length];
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new ListFragment();
            frags[1] = new NewNoteFragment();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


   /* public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }*/



}
