package com.sifasystems.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.sifasystems.R;
import com.sifasystems.adapter.UserAdapter;
import com.sifasystems.helper.SQLiteHandler;
import com.sifasystems.model.Global;
import com.sifasystems.model.User;

import java.util.ArrayList;

public class MalpracticeConfirmActivity extends AppCompatActivity {

    private Button btn_confirm, btn_malpractice;
    private TextView txt_title, txt_scan, txt_or;
    private EditText txt_malpractice;
    private ListView list_user;
    private ArrayList<User> arrayUsers = null;
    private UserAdapter adapter = null;
    private MultiAutoCompleteTextView simpleMultiAutoCompleteTextView;
    private ArrayAdapter<String> adapterRange;
    private ArrayList<String> arrayNumber;
    ArrayList<Long> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malpractice_confirm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        arrayNumber=new ArrayList<>();
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_or = (TextView) findViewById(R.id.txt_or);
        txt_scan = (TextView) findViewById(R.id.txt_scan);
        arrayList=new ArrayList<>();
        String candidateNumbers[] = { "1010111001", "1010111002", "1010111003", "1010111004", "1010111005", "1010111006",
                "1010111007","1010111008","1010111009","1010111010", "1010111011","1010111012","1010111013","1010111014",
                "1010111015","1010111016","1010111017", "1010111018","1010111019","1010111020","1010111021","1010111022",
                "1010111023","1010111024","1010111025","1010111026","1010111025","1010111027","1010111028","1010111029",
                "1010111030","1010111031","1010111032","1010111033","1010111034","1010111035","1010111036","1010111037",
                "1010111038","1010111039","1010111040","1010111041","1010111042","1010111043","1010111044", "1010111045",
                "1010111046","1010111047","1010111048","1010111049","1010111050","1010111051" ,"1010111052","1010111053",
                "1010111054" ,"1010111055","1010111056" ,"1010111057","1010111058","1010111059","1010111060","1010111061",
                "1010111062","1010111063" ,"1010111064" ,"1010111065" ,"1010111066","1010111067","1010111068","1010111069",
                "1010111070","1010111071","1010111072","1010111073","1010111074","1010111075","1010111076","1010111077",
                "1010111078","1010111079","1010111080","1010111081","1010111082","1010111083","1010111084","1010111085",
                "1010111086","1010111087","1010111088","1010111089","1010111090","1010111091","1010111092","1010111093",
                "1010111094","1010111095","1010111096","1010111097","1010111098","1010111099","1010111100" };



//       for(long l=1010111001; l<=1010111100100l; l++){
//           arrayList.add(l);
//       }




           txt_malpractice = (EditText) findViewById(R.id.txt_malpractice);
          list_user = (ListView) findViewById(R.id.list_user);
        simpleMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.simpleMultiAutoCompleteTextView);
        simpleMultiAutoCompleteTextView.getText().toString(); // retrieve a value from MultiAutoCompleteTextView
        adapterRange= new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_dropdown_item_1line,candidateNumbers);
        simpleMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        simpleMultiAutoCompleteTextView.setInputType(InputType.TYPE_CLASS_TEXT);
        simpleMultiAutoCompleteTextView.setThreshold(1);
        simpleMultiAutoCompleteTextView.setAdapter(adapterRange);

       list_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
       getInvigilators();
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (Global.malpractice_type == 1) {

                    if(simpleMultiAutoCompleteTextView.getText().toString().length()!=0){
                        showAddItemDialog(MalpracticeConfirmActivity.this);
                        getInvigilators();
                    }
                    else{
                        Toast.makeText(MalpracticeConfirmActivity.this,"Please Enter Number",Toast.LENGTH_SHORT).show();
                    }
                }
               else{

                        if(txt_malpractice.getText().toString().length()!=0){
                        showAddItemDialog(MalpracticeConfirmActivity.this);
                        getInvigilators();
                    }
                    else{
                        Toast.makeText(MalpracticeConfirmActivity.this,"Please Enter Number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_malpractice = (Button) findViewById(R.id.btn_malpractice);
        btn_malpractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayUsers == null || arrayUsers.size() == 0) {
                    Toast.makeText(getApplicationContext(), "There has no any selected invigilator.", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(MalpracticeConfirmActivity.this, ChooseMalpracticeActivity.class));
                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                }
            }
        });


        if (Global.malpractice_type == 0) {
            txt_title.setText("Enter candidate Number");
            txt_malpractice.setVisibility(View.VISIBLE);
            //make the text view Elements visible
            txt_or.setVisibility(View.VISIBLE);
            txt_scan.setVisibility(View.VISIBLE);

            txt_scan.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        }
        else if (Global.malpractice_type == 1) {
            txt_title.setText("Enter Range of Students");
            simpleMultiAutoCompleteTextView.setVisibility(View.VISIBLE);
            simpleMultiAutoCompleteTextView.setTokenizer(new SpaceTokenizer());
        }
        else if (Global.malpractice_type == 2) {
            txt_title.setText("Enter Center Code");
            txt_malpractice.setVisibility(View.VISIBLE);
        }
    }



    private void getInvigilators() {
        String id = simpleMultiAutoCompleteTextView.getText().toString().trim();
        if (id.length()!=0)
        id=id.substring(0,id.length()-1);

        Log.d("Malpractice confirm", "student id: "+id);
        SQLiteHandler mydb = new SQLiteHandler(getApplicationContext());
        if (Global.malpractice_type == 0) {
            arrayUsers = mydb.getInvigilator(id);

            for(int i=0; i<arrayUsers.size(); i++){
                arrayUsers.get(i);
                Log.d("array if",""+arrayUsers.get(i).key.toString());
                    arrayNumber.add(arrayUsers.get(i).key.toString());
            }
            simpleMultiAutoCompleteTextView.setAdapter(adapterRange);
        }
        else {
            arrayUsers = mydb.getInvigilators(id);
            for(int i=0; i<arrayUsers.size(); i++){
                arrayUsers.get(i);
                arrayNumber.add(arrayUsers.get(i).key.toString());
            }

            simpleMultiAutoCompleteTextView.setAdapter(adapterRange);

        }
        adapter = new UserAdapter(arrayUsers, getApplicationContext());
        list_user.setAdapter(adapter);
        adapterRange.notifyDataSetChanged();

        if (arrayUsers != null && arrayUsers.size() == 0) {
            Toast.makeText(getApplicationContext(), "Sorry! your entry is not recognised.", Toast.LENGTH_LONG).show();
        }
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

   //Dialog Box
    private void showAddItemDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        if (Global.malpractice_type == 1) {

            taskEditText.setText(simpleMultiAutoCompleteTextView.getText().toString());
        }
        else{
            taskEditText.setText(txt_malpractice.getText().toString());
        }

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Are you sure?")
                .setView(taskEditText)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number=taskEditText.getText().toString().trim();
                        if(number.length()!=0){

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please Enter Number",Toast.LENGTH_SHORT).show();
                        }

//                        String id = simpleMultiAutoCompleteTextView.getText().toString().trim();
//                        if (id.length()!=0){
//                         //   getInvigilators();
//                        }
//
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
                dialog.show();
    }

    //Opening NFC Activity to allow the user to scan the card.
    public void scanCard(View view){
        Intent nfcActivity=new Intent(MalpracticeConfirmActivity.this, NFCActivity.class);
        nfcActivity.putExtra("STUDENT",true);
        startActivity(nfcActivity);
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
    }


    public class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;

            while (i > 0 && text.charAt(i - 1) != ' ') {
                i--;
            }
            while (i < cursor && text.charAt(i) == ' ') {
                i++;
            }
            return i;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
            int i = cursor;
            int len = text.length();

            while (i < len) {
                if (text.charAt(i) == ' ') {
                    return i;
                } else {
                    i++;
                }
            }

            return len;
        }

        public CharSequence terminateToken(CharSequence text) {
            int i = text.length();

            while (i > 0 && text.charAt(i - 1) == ' ') {
                i--;
            }

            if (i > 0 && text.charAt(i - 1) == ' ') {
                return text;
            } else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text + "");
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                            Object.class, sp, 0);
                    return sp;
                } else {
                    return text + "-";
                }
            }
        }
    }
}
