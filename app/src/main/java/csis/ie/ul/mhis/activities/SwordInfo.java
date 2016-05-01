package csis.ie.ul.mhis.activities;

import android.content.Intent;
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

        String imgURL = "http://vignette" + Data.swordArray.get(pos).getVigNumb() + ".wikia.nocookie.net/monsterhunter/images/" +
                           Data.swordArray.get(pos).getMidSec() + "/MH4-Great_Sword_Render_" + Data.swordArray.get(pos).getDigits() + ".png";
        try
        {
            Toast.makeText(this, "Acquiring image from the wiki.", Toast.LENGTH_LONG).show();
            Picasso.with(this).load(imgURL).placeholder(R.mipmap.ic_placeholder).into(iv);
        } catch (Exception e)
        {
            Toast.makeText(this, "Could not acquire image, " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
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
    public void onBackPressed()
    {
        Intent intent = new Intent(this,SwordList.class);
        startActivity(intent);
    }
}
