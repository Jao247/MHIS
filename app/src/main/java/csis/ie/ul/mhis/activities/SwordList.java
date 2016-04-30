package csis.ie.ul.mhis.activities;

import android.content.Intent;
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

import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;

public class SwordList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sword_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if ( id == R.id.nav_sword)
        {
            Snackbar.make(getCurrentFocus(), "The world is in trouble.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else if ( id == R.id.nav_monster)
        {
            Intent i = new Intent(this, MonsterList.class);
            startActivity(i);
        }
        else if ( id == R.id.nav_wiki )
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
}
