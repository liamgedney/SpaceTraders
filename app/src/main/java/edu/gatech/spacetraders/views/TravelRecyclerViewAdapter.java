package edu.gatech.spacetraders.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.spacetraders.R;
public class TravelRecyclerViewAdapter extends RecyclerView.Adapter<edu.gatech.spacetraders.views.
        TravelRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private final LayoutInflater mInflater;
    private edu.gatech.spacetraders.views.TravelRecyclerViewAdapter
            .ItemClickListener mClickListener;

    // data is passed into the constructor
    TravelRecyclerViewAdapter (Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public edu.gatech.spacetraders.views.TravelRecyclerViewAdapter
            .ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewtravel_row, parent, false);
        return new edu.gatech.spacetraders.views.TravelRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull edu.gatech.spacetraders.views.
            TravelRecyclerViewAdapter.ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.planets_row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(edu.gatech.spacetraders.views.TravelRecyclerViewAdapter.ItemClickListener
                                  itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setList(List<String> data) {
        mData = data;
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
