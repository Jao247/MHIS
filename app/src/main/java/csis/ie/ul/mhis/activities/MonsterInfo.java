package csis.ie.ul.mhis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.R;

/**
 * This outputs the information for a monster
 * @author Adam Doherty
 */
public class MonsterInfo extends AppCompatActivity
{
    /**
     * This method is called when the activity is called
     * @param savedInstanceState This just needed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle dataIn = getIntent().getExtras();
        int pos = dataIn.getInt("id");

        changeDisplay(pos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * @param pos
     */

    public void changeDisplay(int pos){
        setTitle(Data.bossArray.get(pos).get_name());

        ImageView iv = (ImageView) findViewById(R.id.mImage);

        String imgURL = Data.bossArray.get(pos).get_url();
        try
        {
            Toast.makeText(this, "Acquiring the image from the Wiki", Toast.LENGTH_LONG).show();
            Picasso.with(this).load(imgURL).placeholder(R.mipmap.ic_placeholder).into(iv);
        } catch (Exception e)
        {
            Toast.makeText(this,"Failed to acquire image from the wiki", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        TextView tv = (TextView) findViewById(R.id.typeData);
        tv.setText("" + Data.bossArray.get(pos).get_type());
        tv = (TextView) findViewById(R.id.weaknessData);
        tv.setText("" + Data.bossArray.get(pos).get_weakness());
        tv = (TextView) findViewById(R.id.elementData);
        tv.setText("" + Data.bossArray.get(pos).get_element());
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MonsterList.class);
        startActivity(intent);
    }
    public void goHome(View view)
    {
        Intent i = new Intent(this, MonsterList.class);
        startActivity(i);
    }
}
