package com.sifasystems.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sifasystems.R;
import com.sifasystems.adapter.ScanLogDetailsRecyclerViewAdapter;
import com.sifasystems.helper.SQLiteHandler;
import com.sifasystems.model.ScanLogModel;

import java.util.ArrayList;
import java.util.List;

public class ScanLogActivity extends AppCompatActivity {

    private List<ScanLogModel> scanLogModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScanLogDetailsRecyclerViewAdapter mScanLogAdapter;
    private SQLiteHandler sqLiteHandler;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_log);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqLiteHandler=new SQLiteHandler(ScanLogActivity.this);
        if(sqLiteHandler!=null){
        //  Query to get the data from tbl_scan_log_activity
         cursor=sqLiteHandler.query("select * from tbl_scan_log_activity");
         if(cursor!=null){
             if(cursor.moveToFirst()){

                 do{
                     ScanLogModel scanLogModel=new ScanLogModel();
                     scanLogModel.setLogId(cursor.getInt(0));
                     scanLogModel.setDeviceId(cursor.getString(1));
                     scanLogModel.setUserId(cursor.getString(2));
                    scanLogModelList.add(scanLogModel);
                 }
                 while(cursor.moveToNext());
                 }
             }
         }

        mScanLogAdapter = new ScanLogDetailsRecyclerViewAdapter(scanLogModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mScanLogAdapter);

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
