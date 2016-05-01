package csis.ie.ul.mhis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;
import csis.ie.ul.mhis.objects.BossObj;
/*Importing the Monster data into the BossArray in the data class. Here we used a Input Steam which calls from the
  Asset Manager, which then references the file in the asset directory. We the then use a buffer reader to read in the
  info from the CSV file. Using a for loop, objects on BossObj type are created and putt into the Boss Array list.
  A second local array is made and the names from the objects are put into it. This is then displayed in the list where
  the user can select an option*/
public class MonsterList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Data.bossArray.clear();

        AssetManager assMan = getAssets();
        InputStream is = null;
        try {
            is = assMan.open("BossList.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        String elements[];
        try {
            for (int i = 0; i <= 35; i++) {
                line = reader.readLine();
                elements = line.split(",");
                Data.bossArray.add(new BossObj(Integer.parseInt(elements[0]), elements[1], elements[2], elements[3], elements[4], elements[5]));
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }

        final ListView lView = (ListView) findViewById(R.id.monster_listView);

        final ArrayList<String> monNames = new ArrayList<>();
        for (int i = 0; i < Data.bossArray.size(); i++) {
            monNames.add(Data.bossArray.get(i).get_name());
        }


        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, monNames);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                switchTo(position);            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    /*when a position in the list is pressed on the phone, this method is called and the position in the array list
    is passed through and then passed to the new activity*/

    private void switchTo(int pos)
    {
        Intent in = new Intent(this, MonsterInfo.class);
        in.putExtra("id", pos);
        startActivity(in);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
    public void goHome(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sword) {
            Intent i = new Intent(this, SwordList.class);
            startActivity(i);
            //finish();
        } else if (id == R.id.nav_monster) {
            Toast.makeText(this, R.string.monster_list_toast_msg, Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_wiki) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("http://monsterhunter.wikia.com/wiki/Monster_Hunter_4_Ultimate"));
            startActivity(i);
        } else if (id == R.id.nav_exit) {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //When the phone back button is pressed the app goes back to the main activity from this activity.
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
