package com.summercourse.keepwatchingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 5/5/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";

    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
//kda hwa 3mal l database 5las l local server bass lsa ma7tsh feha data
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
//hna l method dh 3shan a7ot l data fel table aly ana 3mlto foo2 w dh akiid aly hnadi 3aleha fel class l tanii
    /**
     * Storing user details in database
     * */
    public void addUser(String email, String password) {
        //hna ana ba3rfo ani haft7 l db 3shan aktab feha
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //hna by7ot l email wel password aly mab3oten fel method dh fel db
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }
//l method dh bt3mal check 3la an l email wel password dool mawgoden fel db wla laa
    //wel method dh akiid aly ana badhalo fel login
    public boolean getUser(String email, String pass){
        //Hna l satr dh byktab feeh l amr bta3 l get
        //hna mafrood l amr dh lw ashta8al yb2a fa3ln fel coloumn fel db feh l email kda wel passkda
        //amr say3 fash5
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";
        //hna ana b3rafo any haft7 l db 3shan a2ra mnha
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //hna 3shan ybda2 mn awal l db
        cursor.moveToFirst();
        // >0 yb2a hwa 2ra 7aga w fe fa3ln fel db bta3tna l klam dh w hayrg3 true
        //3ash fash5
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
