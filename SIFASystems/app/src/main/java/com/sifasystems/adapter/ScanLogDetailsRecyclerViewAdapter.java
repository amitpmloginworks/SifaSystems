package com.sifasystems.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sifasystems.R;
import com.sifasystems.model.ScanLogModel;

import java.util.List;

public class ScanLogDetailsRecyclerViewAdapter extends RecyclerView.Adapter<ScanLogDetailsRecyclerViewAdapter.ScanLogViewHolder> {

    private List<ScanLogModel> scanLogModelList;

    public ScanLogDetailsRecyclerViewAdapter(List<ScanLogModel> scanLogModel){
        this.scanLogModelList=scanLogModel;

    }

    @NonNull
    @Override
    public ScanLogDetailsRecyclerViewAdapter.ScanLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scan_log_list_row, parent, false);

        return new ScanLogViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ScanLogDetailsRecyclerViewAdapter.ScanLogViewHolder holder, int position) {

        ScanLogModel scanLogModel = scanLogModelList.get(position);
        holder.scanLogId.setText(String.valueOf(scanLogModel.getLogId()));
        holder.userId.setText(String.valueOf(scanLogModel.getUserId()));
        holder.status.setText(String.valueOf(scanLogModel.getStatus()));
    }

    @Override
    public int getItemCount() {
        return scanLogModelList.size();
    }


    public class ScanLogViewHolder extends RecyclerView.ViewHolder {
        public TextView scanLogId, userId, status;

        public ScanLogViewHolder(View view) {
            super(view);
            scanLogId = (TextView) view.findViewById(R.id.scan_log_id);
            userId = (TextView) view.findViewById(R.id.user_id);
            status = (TextView) view.findViewById(R.id.status);
        }
    }

}
