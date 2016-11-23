package comsitejustdoitwhistle.google.sites.monermoney;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddIncome extends AppCompatActivity {

    private final int TYPE_INCOME = 0;
    private Button mCalendarPicker;
    private int amount;
    private Editable comment;
    private int date, month, years;
    private static final int DIALOG_ID = 0;
    private EditText mDateTextEdit;
    private Button mdatePickerBtn;
    private EditText mAmountIncome;
    private EditText mNoteTextView;
    private Button mSaveBtn;
    private Button mCancleBtn;
    protected static MonerMoneyDB dataBase;
    private AlertDialog alertDialog;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        bindWidget();
        setOnListener();

        final Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        years = calendar.get(Calendar.YEAR);
        mDateTextEdit.setText(date + "/" + month + "/" + years);
        dataBase = new MonerMoneyDB(this);
        alertDialog = new AlertDialog.Builder(AddIncome.this).create();
    }

    private void setOnListener() {
        mDateTextEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
        mdatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = true;
                comment = mNoteTextView.getText();
                try {
                    amount = Integer.parseInt(String.valueOf(mAmountIncome.getText()));
                } catch (NumberFormatException e) {
                    check = false;
                    alertDialog.setTitle("ผิดพลาด");
                    alertDialog.setMessage("กรุณาใส่จำนวนเงิน");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                if (check) {
                    try {
                        dataBase.insertData(amount, date, month, years, (comment).toString(), TYPE_INCOME);
                    } catch (MonerMoneyDBException e) {
                        Toast.makeText(AddIncome.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(AddIncome.this, "บันทึกเรียบร้อย!", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
        mCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //Add Object
    private void bindWidget() {
        mCalendarPicker = (Button) findViewById(R.id.in_calendarpickerbtn);
        mDateTextEdit = (EditText) findViewById(R.id.in_dateEditText);
        mdatePickerBtn = (Button) findViewById(R.id.in_calendarpickerbtn);
        mAmountIncome = (EditText) findViewById(R.id.int_amonnum);
        mNoteTextView = (EditText) findViewById(R.id.in_note);
        mSaveBtn = (Button) findViewById(R.id.in_savebtn);
        mCancleBtn = (Button) findViewById(R.id.in_canclebtn);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, years, month, date);
        else return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            years = year;
            month = monthOfYear + 1;
            date = dayOfMonth;
            mDateTextEdit.setText(date + "/" + month + "/" + years);
        }
    };
}
