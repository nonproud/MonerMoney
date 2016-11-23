package comsitejustdoitwhistle.google.sites.monermoney;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OverViewIncome_new extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    MonerMoneyDB dataBase;
    public ArrayList<String> list;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view_income_new);
        dataBase = new MonerMoneyDB(OverViewIncome_new.this);
        list= new ArrayList<String>();
        //list.add("ไม่พบข้อมูลรายรับ");
        bindWidget();
        list = dataBase.getAllIncomeData();
//        Toast.makeText(this, list.size(), Toast.LENGTH_LONG).show();
        if(list.size() == 0){
            list.add("ไม่พบขอมูลรายรับ");
        }

        mListView.setAdapter(new ArrayAdapter<String>( this,
        android.R.layout.simple_list_item_1, list));


    }

    private void bindWidget() {
        mListView = (ListView) findViewById(R.id.income_listview);
    }
}
