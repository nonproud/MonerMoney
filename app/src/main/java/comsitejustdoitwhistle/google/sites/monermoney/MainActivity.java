package comsitejustdoitwhistle.google.sites.monermoney;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mFloatbtnAdd;
    private PopupMenu popupMenu;
//    public static final MonerMoneyDB db = new MonerMoneyDB(MainActivity.this);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button mNewBtn;
    private Button mOverViewIncomebtn;
    private Button mOverViewOutComebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        setPopupMenu();
        setOnClickListener();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setOnClickListener() {
        mFloatbtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });

        mOverViewIncomebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mOverViewOutComebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mOverViewIncomebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OverViewIncome_new.class));
            }
        });

        mOverViewOutComebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OverViewOutcome_new.class));
            }
        });
    }

    private void setPopupMenu() {
        //Toast.makeText(MainActivity.this, "Add was Cilcked!!", Toast.LENGTH_LONG).show();
        popupMenu = new PopupMenu(MainActivity.this, mFloatbtnAdd);
        //MenuInflater menuInflater = popupMenu.getMenuInflater();
        popupMenu.getMenuInflater().inflate(R.menu.add_popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_income_popup_menu:
                        startActivity(new Intent(MainActivity.this, AddIncome.class));
                        return true;
                    case R.id.add_outcome_popup_menu:
                        startActivity(new Intent(MainActivity.this, AddOutcome.class));
                        return true;
                }
                return false;
            }
        });
    }

    private void bindWidget() {
        mFloatbtnAdd = (FloatingActionButton) findViewById(R.id.floatbtn_add);
        mOverViewIncomebtn = (Button) findViewById(R.id.overview_income_btn);
        mOverViewOutComebtn = (Button) findViewById(R.id.overview_outcome_btn);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("DEBUGGING");
        builder.setMessage(message);
        builder.show();
    }
}
