package csis.ie.ul.mhis.DBHandlers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import csis.ie.ul.mhis.objects.BossObj;


public class bossDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Boss_info.db";
    public static final String TABLE_NAME = "BossInfo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_WEAKNESS = "weakness";
    public static final String COLUMN_ELEMENT = "element";

    public bossDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " TEXT NOT NULL UNIQUE, " +
                COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
                COLUMN_TYPE + " TEXT NOT NULL, " +
                COLUMN_WEAKNESS + " TEXT NOT NULL, " +
                COLUMN_ELEMENT + " TEXT, PRIMARY KEY(" + COLUMN_ID + ")" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //print out the database as a string

    public void databaseToString() {
        ArrayList<BossObj> boos = new ArrayList<>();
        Connection c = null;
        try {
            Statement statement = c.createStatement();
            String s = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = statement.executeQuery(s);
            while (rs.next()) {
                boos.add(new BossObj(
                        rs.getString("_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("weakness"),
                        rs.getString("element")
                ));
            }
            rs.close();
            } catch (Exception e) {
                System.out.println(e);
        }
    }
}

