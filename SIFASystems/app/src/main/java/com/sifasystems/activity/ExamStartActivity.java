package com.sifasystems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sifasystems.R;
import com.sifasystems.helper.SQLiteHandler;
import com.sifasystems.model.Global;


public class ExamStartActivity extends AppCompatActivity {

    private Button btn_submit;
    private TextView txt_paper, txt_time;
    private ImageView img_paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_start);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_paper = (TextView) findViewById(R.id.txt_paper);
        img_paper=(ImageView) findViewById(R.id.img_paper);
        txt_time = (TextView) findViewById(R.id.txt_time);

        txt_paper.setText(String.format("Paper %d", Global.selectedPaper));
      // set Time With Automatic Refresh
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                   while(!isInterrupted()) {
                       Thread.sleep(500);
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                             //  txt_time = (TextView) findViewById(R.id.txt_time);
                             //  txt_time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                               String resultTxt = ""+DateFormat.format("h:mm:ss aa", System.currentTimeMillis());
                               txt_time.setText(resultTxt);
                           }
                       });
                   }
                }
                catch(InterruptedException e){
                  e.printStackTrace();
                }
            }
        };thread.start();



        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent=getIntent();
                Intent i=getIntent();
                String schoolName=i.getStringExtra("school_name");
                long paper=i.getLongExtra("paper",0);
                SQLiteHandler mydb = new SQLiteHandler(getApplicationContext());
                mydb.saveExamDetails(schoolName, paper);
                Toast.makeText(getApplicationContext(), "Submission Successful.", Toast.LENGTH_LONG).show();

                //Back to InvigilatorMain Activity
                Intent intent = new Intent(ExamStartActivity.this, InvigilatorMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_left_to_right, R.anim.activity_right_to_left);
                finish();
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
