package edu.gatech.spacetraders.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.spacetraders.R;

/**
 * adapter for market place recycler view
 */
public class MarketplaceRecyclerViewAdapter extends
        RecyclerView.Adapter<MarketplaceRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private final LayoutInflater mInflater;
    // data is passed into the constructor

    /**
     * constructor
     * @param context contextual
     * @param data data
     */
    public MarketplaceRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewmarketplace_row, parent, false);
        return new ViewHolder(view);
    }

    /**
     * binds it
     * @param holder Views hold
     * @param position of good?
     */
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String good = mData.get(position);
        holder.myTextView.setText(good);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.waterMkt);
        }

    }

    // convenience method for getting data at click position


    /*public String getItem(int id) {
        return mData.get(id);
    }*/

    // allows clicks events to be caught
    /*void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }*/

    /**
     * sets the list
     * @param data list of string
     */
    public void setList(List<String> data) {
        mData = data;
    }

    // parent activity will implement this method to respond to click events

}
