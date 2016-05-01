package csis.ie.ul.mhis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import csis.ie.ul.mhis.Data;
import csis.ie.ul.mhis.MainActivity;
import csis.ie.ul.mhis.R;

public class MonsterInfo extends AppCompatActivity
{

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
    public void changeDisplay(int pos){
        setTitle(Data.bossArray.get(pos).get_name());

        ImageView iv = (ImageView) findViewById(R.id.mImage);

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
}
