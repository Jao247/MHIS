package csis.ie.ul.mhis.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;
import csis.ie.ul.mhis.objects.SwordsObj;

public class SwordList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sword_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readFile();

        final ListView    lView = (ListView) findViewById(R.id.sword_listView);
        ArrayList<String> names = new ArrayList<>();
        for ( int i = 0; i < Data.swordArray.size(); ++i )
        {
            names.add(Data.swordArray.get(i).getName());
        }

        if ( names.size() == 0 ) Toast.makeText(this, "No values", Toast.LENGTH_LONG).show();

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                final String item = (String) parent.getItemAtPosition(position);
                switchTo(position);
            }

        });

        DrawerLayout          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void switchTo(int pos)
    {
        Intent in = new Intent(this, SwordInfo.class);
        in.putExtra("id", pos);
        startActivity(in);
    }

    private class StableArrayAdapter extends ArrayAdapter<String>
    {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects)
        {
            super(context, textViewResourceId, objects);
            for ( int i = 0; i < objects.size(); ++i )
            {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position)
        {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds()
        {
            return true;
        }

    }

    public void readFile()
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("SwordList.csv")));
            String mLine, temp[];
            int ints[] = new int[4];
            // i ID, s Name, s type, i atk, s spec, s sharp, i aff, i vignette, s midSec, s ending
            while ((mLine = reader.readLine()) != null)
            {
                temp = mLine.split(",");
                try {
                    ints[0] = Integer.parseInt(temp[0]);
                    ints[1] = Integer.parseInt(temp[3]);
                    ints[2] = Integer.parseInt(temp[6]);
                    ints[3] = Integer.parseInt(temp[7]);
                } catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
                Data.swordArray.add(new SwordsObj(ints[0],temp[1],temp[2],ints[1],temp[4],temp[5],ints[2],ints[3],temp[8],temp[9]));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if (reader != null)
            {
                try {
                    reader.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void goHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_sword )
        {
            Toast.makeText(this, R.string.sword_list_toast_msg, Toast.LENGTH_LONG).show();
        } else if ( id == R.id.nav_monster )
        {
            Intent i = new Intent(this, MonsterList.class);
            startActivity(i);
            finish();
        } else if ( id == R.id.nav_wiki )
        {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("http://monsterhunter.wikia.com/wiki/Monster_Hunter_4_Ultimate"));
            startActivity(i);
        } else if ( id == R.id.nav_exit )
        {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
