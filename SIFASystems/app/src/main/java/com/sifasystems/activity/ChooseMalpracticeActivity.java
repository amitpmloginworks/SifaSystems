package com.sifasystems.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.sifasystems.R;
import com.sifasystems.helper.SQLiteHandler;
import com.sifasystems.model.Global;

public class ChooseMalpracticeActivity extends AppCompatActivity {

    private Button btn_confirm;
    private Spinner spinner_malpractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_malpractice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_malpractice = (Spinner) findViewById(R.id.spinner_malpractice);
        spinner_malpractice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (spinner_malpractice.getSelectedItemId() == 0) {
                Toast.makeText(getApplicationContext(), "Please choose a malpractice.", Toast.LENGTH_LONG).show();
                //store the data of the malpractice student with the center number, invigilator name, coordinator ID, studentID, malpractice type(individual, group, whole center)
                //malpractice (1,2,3),
            }
            else {
                String malpracticeType= (String) spinner_malpractice.getSelectedItem();
                Log.d("type",""+malpracticeType);

                //Adding data to DB
                SQLiteHandler mydb = new SQLiteHandler(getApplicationContext());
                mydb.addMalpracticeData("11","11",malpracticeType,Global.selectedPaper,0);

                AlertDialog alertDialog = new AlertDialog.Builder(
                        ChooseMalpracticeActivity.this).create();

                // Setting Dialog Title
//                alertDialog.setTitle("Alert Dialog");

                // Setting Dialog Message
                alertDialog.setMessage("Malpractice submitted Successfully!");

                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.tick);

                // Setting OK Button

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(ChooseMalpracticeActivity.this, InvigilatorMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_left_to_right, R.anim.activity_right_to_left);
                        finish();

//                        startActivity(new Intent(ChooseMalpracticeActivity.this,InvigilatorMainActivity.class));
//                        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                       dialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_left_to_right, R.anim.activity_right_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}
