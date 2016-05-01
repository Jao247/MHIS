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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;

/** A class to display a list of items from an array of swords.
 *  @author Jason Fahy
 */
public class SwordList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    /**
     * The method called when the activity is made.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Set up the activity to display the correct content. and the toolbar.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sword_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // If the array for swords is empty, read the details from the "SwordList.csv" file

        // Create an object for the list view and a arraylist to store all the sword names
        final ListView    lView = (ListView) findViewById(R.id.sword_listView);
        ArrayList<String> names = new ArrayList<>();
        for ( int i = 0; i < Data.swordArray.size(); ++i )
        {
            names.add(Data.swordArray.get(i).getName());
        }

        // Give a toast if it cannot load the data into the sword list.
        if ( names.size() == 0 ) Toast.makeText(this, "No values", Toast.LENGTH_LONG).show();

        // Create an object of the Stable Array Adapter so that the names can be displayed.
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        lView.setAdapter(adapter);

        // Adds a listener for clicking any of the list items so that we can switch to another activity.
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                final String item = (String) parent.getItemAtPosition(position);
                switchTo(position);
            }

        });

        // Sets up the navigation drawer, this is done for all activities that includes the drawer.
        DrawerLayout          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // This adds the Navigation view to the activity.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * A method to switch from the list activity to the info activity for swords.
     * @param pos the position in the sword array that has been tapped on.
     */
    private void switchTo(int pos)
    {
        Intent in = new Intent(this, SwordInfo.class);
        in.putExtra("id", pos);
        in.putExtra("gotoMain", false);
        startActivity(in);
    }

    /** A class for the List view to allow us to properly display the list item
     */
    private class StableArrayAdapter extends ArrayAdapter<String>
    {
        // An implementation of the ArrayAdapter for the List View.
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects)
        {
            super(context, textViewResourceId, objects);
            for ( int i = 0; i < objects.size(); ++i )
            {
                mIdMap.put(objects.get(i), i);
            }
            // We are using the Hashmap to store the data for the List of Items.
        }

        /** This is used to return the ItemID, although we didnt use it as part of our app,
         *  It is required to be here to override the method of the same name and arguments
         *  in the Array Adapter class.
         *  @param position This is the position in the list view.
         */
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



    /**
     * This is to bring the user back to the main page, this is also achieved by using the back button
     * @param view this is a required parameter for the onClick function.
     */
    public void goHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * This is how we are using the Navigation Items and how we deal with what they do.
     * @param item this is to signify which item was pressed
     * @return this is returning a boolean to state that an item was selected.
     */
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
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
