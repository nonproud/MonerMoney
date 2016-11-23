package comsitejustdoitwhistle.google.sites.monermoney;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OverViewOutcome_new extends Activity {
    MonerMoneyDB dataBase;
    public ArrayList<String> list;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view_outcome_new);
        bindWidget();
        dataBase = new MonerMoneyDB(OverViewOutcome_new.this);
        list= new ArrayList<String>();
        list = dataBase.getAllOutcomeData();
        if(list.size() == 0){
            list.add("ไม่พบขอมูลรายรับ");
        }

        mListView.setAdapter(new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, list));
    }

    private void bindWidget() {
        mListView = (ListView) findViewById(R.id.outcome_listView);
    }


}
