package comsitejustdoitwhistle.google.sites.monermoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kenn on 11/18/2016.
 */

public class MonerMoneyDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MonerMoney.db";
    public static final String TABLE_NAME = "DATA";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MONEY";
    public static final String COL_3 = "DAY";
    public static final String COL_4 = "MONTH";
    public static final String COL_5 = "YEAR";
    public static final String COL_6 = "NOTE";
    public static final String COL_7 = "STATUS";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " INTEGER, " +
            COL_3 + " INTEGER, " + COL_4 + " INTEGER, " + COL_5 + " INTEGER, " + COL_6 + " TEXT, " + COL_7 + " INTEGER)";

    public MonerMoneyDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertData(int money, int day, int month, int year, String note, int type) throws MonerMoneyDBException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, money);
        contentValues.put(COL_3, day);
        contentValues.put(COL_4, month);
        contentValues.put(COL_5, year);
        contentValues.put(COL_6, note);
        contentValues.put(COL_7, type);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            throw new MonerMoneyDBException("Can't insert Data");
    }

    public ArrayList<Income> getAllIncomeData(int i) throws MonerMoneyDBException {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if(res.getCount() == 0) {
            throw new MonerMoneyDBException("Nothing Found");
        }
        ArrayList<Income> incomes = new ArrayList<Income>();
        StringBuffer buffer = new StringBuffer();
        for( ; res.moveToNext() ; ) {
            if(res.getString(6).equalsIgnoreCase("0")) {
                String money = res.getString(1);
                buffer.append(res.getString(2));
                buffer.append("/");
                buffer.append(res.getString(3));
                buffer.append("/");
                buffer.append(res.getString(4));
                String date = buffer.toString();
                String note = res.getString(5);
                incomes.add(new Income(Integer.parseInt(money), date, note));
            }
        }
        Collections.reverse(incomes);
        return incomes;
    }
    //New method return ArrayList of String
    public ArrayList<String> getAllIncomeData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if(res.getCount() == 0) {
            return null;
        }
        ArrayList<String> incomes = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        for( ; res.moveToNext() ; ) {
            if(res.getString(6).equalsIgnoreCase("0")) {
                String money = res.getString(1);
                buffer.append(res.getString(2));
                buffer.append("/");
                buffer.append(res.getString(3));
                buffer.append("/");
                buffer.append(res.getString(4));
                String date = buffer.toString();
                String note = res.getString(5);
                incomes.add(money+"    "+date+"   "+note);
            }
        }
        Collections.reverse(incomes);
        return incomes;
    }

    public ArrayList<Outcome> getAllOutcomeData() throws MonerMoneyDBException {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if(res.getCount() == 0) {
            throw new MonerMoneyDBException("Nothing Found");
        }
        ArrayList<Outcome> outcomes = new ArrayList<Outcome>();
        StringBuffer buffer = new StringBuffer();
        for( ; res.moveToNext() ; ) {
            if(res.getString(6).equalsIgnoreCase("1")) {
                String money = res.getString(1);
                buffer.append(res.getString(2));
                buffer.append("/");
                buffer.append(res.getString(3));
                buffer.append("/");
                buffer.append(res.getString(4));
                String date = buffer.toString();
                String note = res.getString(5);
                outcomes.add(new Outcome(Integer.parseInt(money), date, note));
            }
        }
        Collections.reverse(outcomes);
        return outcomes;
    }



    public int getIncomeSum() throws MonerMoneyDBException {
        int sum = 0;
        ArrayList<Income> incomes = getAllIncomeData(1);
        for(int i = 0 ; i < incomes.size() ; i++)
            sum += incomes.get(i).getMoney();
        return sum;
    }

    public int getOutcomeSum() throws MonerMoneyDBException {
            int sum = 0;
            ArrayList<Outcome> outcomes = null;
            outcomes = getAllOutcomeData();
            for(int i = 0 ; i < outcomes.size() ; i++)
                sum += outcomes.get(i).getMoney();
            return sum;
    }

//

}
