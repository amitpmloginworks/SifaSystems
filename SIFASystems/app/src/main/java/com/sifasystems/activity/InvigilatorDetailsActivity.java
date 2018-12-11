package com.sifasystems.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sifasystems.R;

public class InvigilatorDetailsActivity extends AppCompatActivity {

    EditText txt_name, txt_id;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invigilator_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize views
        txt_id=(EditText)findViewById(R.id.txt_number);
        txt_name=(EditText)findViewById(R.id.txt_name);
        btn_confirm=(Button)findViewById(R.id.btn_confirm);

        //on clicking confirm button
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_id.getText().toString().trim().length()!=0 && txt_name.getText().toString().trim().length()!=0 ){
                    String name = txt_name.getText().toString();
                    String id = txt_id.getText().toString();
                    startActivity(new Intent(InvigilatorDetailsActivity.this, InvigilatorConfirmActivity.class).putExtra("invigilaorName",name).putExtra("invigilatorId",id));
                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                }
                else{
                    Toast.makeText(InvigilatorDetailsActivity.this,"Please Enter Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   //on android back button press
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
