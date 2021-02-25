package com.madhram.testalarstudios.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.madhram.testalarstudios.R;
import com.madhram.testalarstudios.model.Datum;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_image)
    ImageView mItemImage;
    @BindView(R.id.item_name)
    TextView mItemName;
    @BindView(R.id.item_id)
    TextView mItemId;
    @BindView(R.id.item_country)
    TextView mItemCountry;
    @BindView(R.id.item_lat)
    TextView mItemLat;
    @BindView(R.id.item_lon)
    TextView mItemLon;
    @BindView(R.id.item_card)
    CardView mItemCard;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Datum item, DataAdapter.ItemClickListener listener) {
        Picasso.get().load("https://picsum.photos/100/100/?random").into(mItemImage);
        mItemName.setText(item.getName());
        mItemId.setText(item.getId());
        mItemCountry.setText(item.getCountry());
        mItemLat.setText(String.valueOf(item.getLat()));
        mItemLon.setText(String.valueOf(item.getLon()));
        if (listener != null) {
            mItemCard.setOnClickListener(view -> listener.onItemClick(item));
        }
    }
}
