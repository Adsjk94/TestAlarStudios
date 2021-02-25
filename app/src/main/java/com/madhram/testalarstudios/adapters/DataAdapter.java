package com.madhram.testalarstudios.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madhram.testalarstudios.R;
import com.madhram.testalarstudios.model.Datum;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    private List<Datum> mDataList = new ArrayList();
    private ItemClickListener mClickListener;

    public DataAdapter(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Datum datum = mDataList.get(position);
        holder.bind(datum, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addData(List<Datum> data){
        mDataList.clear();
        if (data !=null){
            mDataList.addAll(data);
            notifyDataSetChanged();
        }
    }

    public interface  ItemClickListener{
        void onItemClick(Datum model);
    }
}