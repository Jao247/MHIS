package csis.ie.ul.mhis.DBHandlers;

import android.content.Context;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adam on 30/04/2016.
 */

// PLACED A N/A!!!
public class SwordDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "swordinfo.db";
    public static final String TABLE_NAME = "SwordInfo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_ATTACK = "attack";
    public static final String COLUMN_SPECIAL = "special";
    public static final String COLUMN_SHARPNESS = "sharpness";
    public static final String COLUMN_AFFINITY = "affinity";


    public SwordDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("+
                COLUMN_ID + "TEXT NOT NULL UNIQUE, " +
                COLUMN_NAME + "TEXT NOT NULL, " +
                COLUMN_TYPE + "TEXT NOT NULL, "+
                COLUMN_ATTACK + "INTEGER NOT NULL, " +
                COLUMN_SPECIAL + "TEXT NOT NULL, " +
                COLUMN_SHARPNESS + "TEXT NOT NULL, " +
                COLUMN_AFFINITY + "INTEGER NOT NULL, PRIMARY KEY(" + COLUMN_ID +") " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
