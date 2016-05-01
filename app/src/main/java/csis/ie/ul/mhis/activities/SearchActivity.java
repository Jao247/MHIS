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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets up the navigation drawer, this is done for all activities that includes the drawer.
        DrawerLayout          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // This adds the Navigation view to the activity.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void searchFun(View view)
    {
        EditText searchVal = (EditText) findViewById(R.id.search_field);
        bossSearch(searchVal.getText().toString());
        swordSearch(searchVal.getText().toString());

        if ( Data.searchResultsSwords.isEmpty() && Data.searchResultsBoss.isEmpty() )
        {
            Toast.makeText(this, "No results found.", Toast.LENGTH_LONG).show();
        } else
        {
            populateList();
        }
    }

    private void populateList()
    {
        // Create an object for the list view and a arraylist to store all the sword names
        final ListView          lView      = (ListView) findViewById(R.id.search_result_list);
        final ArrayList<String> resultList = new ArrayList<>();
        for ( int i = 0; i < Data.searchResultsSwords.size(); i++ )
            resultList.add(Data.searchResultsSwords.get(i).getName());
        for ( int i = 0; i < Data.searchResultsBoss.size(); i++ )
            resultList.add(Data.searchResultsBoss.get(i).get_name());

        // Give a toast if it cannot load the data into the sword list.
        if ( resultList.size() == 0 ) Toast.makeText(this, "No values", Toast.LENGTH_LONG).show();

        // Create an object of the Stable Array Adapter so that the names can be displayed.
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, resultList);
        lView.setAdapter(adapter);

        // Adds a listener for clicking any of the list items so that we can switch to another activity.
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                final String item = (String) parent.getItemAtPosition(position);
                if ( isBoss(position, resultList) ) switchToMons(resultList.get(position));
                else if ( isSword(position, resultList) ) switchToSword(resultList.get(position));
            }
        });
    }

    public boolean isBoss(int pos, ArrayList<String> nameList)
    {
        for ( int j = 0; j < Data.bossArray.size(); j++ )
        {
            if ( nameList.get(pos).equalsIgnoreCase(Data.bossArray.get(j).get_name()) ) return true;
        }
        return false;
    }
    public boolean isSword(int pos, ArrayList<String> nameList){
        for (int j = 0; j < Data.swordArray.size(); j++){
            if(nameList.get(pos).equalsIgnoreCase(Data.swordArray.get(j).getName()))
                return true;
        }
        return false;
    }

    public void switchToMons(String name)
    {
        Intent i = new Intent(this, MonsterInfo.class);
        int position = 0;
        for (int j = 0; j < Data.bossArray.size(); j++)
            if (Data.bossArray.get(j).get_name().equalsIgnoreCase(name)) position = j;
        i.putExtra("id",position);
        i.putExtra("gotoMain",true);
        startActivity(i);
    }
    public void switchToSword(String name){
        Intent i = new Intent(this,SwordInfo.class);
        int position = 0;
        for (int j = 0; j < Data.swordArray.size(); j++)
            if (Data.swordArray.get(j).getName().equalsIgnoreCase(name)) position = j;
        i.putExtra("id", position);
        i.putExtra("gotoMain",true);
        startActivity(i);
    }

    /**
     * A class for the List view to allow us to properly display the list item
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
        }    // We are using the Hashmap to store the data for the List of Items.


        /**
         * This is used to return the ItemID, although we didnt use it as part of our app,
         * It is required to be here to override the method of the same name and arguments
         * in the Array Adapter class.
         *
         * @param position This is the position in the list view.
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
            Intent i = new Intent(this, SwordList.class);
            startActivity(i);
        } else if ( id == R.id.nav_monster )
        {
            Intent i = new Intent(this, MonsterList.class);
            startActivity(i);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param param the search parameter from the user
     */
    public void bossSearch(String param) {
        Data.searchResultsBoss.clear();
        String filler = "";
        for (int i = 0; i < Data.bossArray.size(); i++ ){
            filler = Data.bossArray.get(i).get_name().toLowerCase();
            if(filler.contains(param.toLowerCase()))
                Data.searchResultsBoss.add(Data.bossArray.get(i));
        }
    }
    public void swordSearch(String param){
        Data.searchResultsSwords.clear();
        String filler = "";
        for (int i = 0; i < Data.swordArray.size(); i++ ){
            filler = Data.swordArray.get(i).getName().toLowerCase();
            if(filler.contains(param.toLowerCase()))
                Data.searchResultsSwords.add(Data.swordArray.get(i));
        }
    }
}
