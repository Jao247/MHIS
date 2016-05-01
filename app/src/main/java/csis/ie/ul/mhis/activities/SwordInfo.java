package csis.ie.ul.mhis.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.R;

public class SwordInfo extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sword_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle dataIn = getIntent().getExtras();
        int    pos    = dataIn.getInt("id");

        changeDisplay(pos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void changeDisplay(int pos)
    {
        setTitle(Data.swordArray.get(pos).getName());

        ImageView iv = (ImageView) findViewById(R.id.sImage);

        String preString = "http://vignette2.wikia.nocookie.net/monsterhunter/images/3/31/MH4-Great_Sword_Render_";

        if ( ++pos / 10 > 0 ) preString += "0";
        else if ( pos / 100 > 0 ) preString += "";
        else preString += "00";

        try
        {
            Picasso.with(this).load(preString + pos + ".png").into(iv);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Toast.makeText(this, preString + pos + ".png", Toast.LENGTH_LONG).show();

        pos--;
        TextView tv = (TextView) findViewById(R.id.atkData);
        tv.setText("" + Data.swordArray.get(pos).getAtk());
        tv = (TextView) findViewById(R.id.sharpData);
        tv.setText("" + Data.swordArray.get(pos).getSharp());
        tv = (TextView) findViewById(R.id.affData);
        tv.setText("" + Data.swordArray.get(pos).getAff() + "%");
        tv = (TextView) findViewById(R.id.specData);
        tv.setText("" + Data.swordArray.get(pos).getSpecial());
        tv = (TextView) findViewById(R.id.typeData);
        tv.setText("" + Data.swordArray.get(pos).getType());
    }
}
