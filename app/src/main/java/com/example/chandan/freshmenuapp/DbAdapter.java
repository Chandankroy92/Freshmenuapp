package com.example.chandan.freshmenuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;


/**
 * Created by Chandan on 14-07-2016.
 */
public class DbAdapter {

    private static final String DB_NAME = "MyDatabse";
    private static final int DB_VERSION = 1;
    public static final int NAME_COLUMN = 1;


    private static final String LOGIN = "register";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASS = "pass";
    private static final String REPASS = "repass";
    private static final String DATE = "date";




    private static final String REGISTER_QUERY = "CREATE TABLE " + LOGIN + " ( "+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT,  " + EMAIL + " TEXT, " + PASS + " TEXT, " + REPASS + " TEXT, " + DATE + " TEXT)";

    private Context context;
    private DbHalper dbHalper;

    private SQLiteDatabase db;

    public DbAdapter(Context context) {
        this.context = context;
        dbHalper=new DbHalper();
    }



    public long setAllRegistration(String name, String email, String pass, String repass, String dt) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(PASS,pass);
        contentValues.put(REPASS,repass);
        contentValues.put(DATE,dt);
        return db.insert(LOGIN,null,contentValues);
    }



    public Cursor getSinlgeEntry(String userName) {
        return db.query(LOGIN,new String[]{ID,NAME,EMAIL,PASS,REPASS,DATE},EMAIL + " like '%"+ userName+"%'",null,null,null,null,null);
            }

    public void updateEntry(String name, String email, String dt, String pass, String repass) {
        ContentValues upadteValue=new ContentValues();
        upadteValue.put(NAME,name);
        upadteValue.put(EMAIL,email);
        upadteValue.put(PASS,pass);
        upadteValue.put(REPASS,repass);
        upadteValue.put(DATE,dt);

        String where = "EMAIL = ?";
        db.update("LOGIN", upadteValue, where, new String[] { email });
    }

// Here class of DbHalper for SQLite Databse

    private class DbHalper extends SQLiteOpenHelper {

        public DbHalper() {

            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

                  sqLiteDatabase.execSQL(REGISTER_QUERY);
            Log.i("Chandan","table ceation");
                //sqLiteDatabase.execSQL(TABLE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVerion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVerion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+LOGIN);

            onCreate(db);

        }
    }



    public DbAdapter open() throws Exception{
            db=dbHalper.getWritableDatabase();
            return this;
        }
        public void close(){
            db.close();
        }
    }


