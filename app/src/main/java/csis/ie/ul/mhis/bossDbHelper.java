package csis.ie.ul.mhis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by adam on 25/04/2016.
 */
public class bossDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "BOSSINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + Boss_info.newbossInfo.BOSS_TABLE+ "(" + ")"; // CONTINUE WORK HERE
    public bossDbHelper(Context context){

    super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
