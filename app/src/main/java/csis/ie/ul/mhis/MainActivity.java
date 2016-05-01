package csis.ie.ul.mhis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import csis.ie.ul.mhis.activities.MonsterList;
import csis.ie.ul.mhis.activities.SearchActivity;
import csis.ie.ul.mhis.activities.SwordList;
import csis.ie.ul.mhis.objects.SwordsObj;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Data.swordArray.isEmpty()) readFile();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                search();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * A method to read from the "SwordInfo.csv" file so that we can add the details
     * to the array list of swords.
     */
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

    public void search()
    {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ( drawer.isDrawerOpen(GravityCompat.START) )
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            finish();
        }
    }

    public void goHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_sword)
        {
            Intent i = new Intent(this, SwordList.class);
            startActivity(i);
            //finish();
        } else if ( id == R.id.nav_monster)
        {
            Intent i = new Intent(this, MonsterList.class);
            startActivity(i);
            //finish();
        } else if ( id == R.id.nav_wiki )
        {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("http://monsterhunter.wikia.com/wiki/Monster_Hunter_4_Ultimate"));
            startActivity(i);
        }
        else if ( id == R.id.nav_exit )
        {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onClick(View v){

    }
    public void openSwords(View view)
    {
        Intent i = new Intent(this, SwordList.class);
        startActivity(i);
       // finish();
    }

    public void openMonsters(View view)
    {
        Intent i = new Intent (this, MonsterList.class);
        startActivity(i);
        //finish();
    }

}
