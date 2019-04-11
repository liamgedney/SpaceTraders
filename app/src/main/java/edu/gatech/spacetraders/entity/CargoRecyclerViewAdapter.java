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
 * Adapter for CargoViewRecycler
 * @author Jenny
 */
public class CargoRecyclerViewAdapter extends
        RecyclerView.Adapter<CargoRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private final LayoutInflater mInflater;

    /**
     * constructor
     * @param context contextual
     * @param data string
     */
    public CargoRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed

    /**
     * Holds the view
     * @param parent not the child
     * @param viewType a type of view
     * @return Viewholder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewcargo_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
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
            myTextView = itemView.findViewById(R.id.waterCargo);
        }
    }


    /**
     * new data set??
     * @param data string
     */
    public void setList(List<String> data) {
        mData = data;
    }
}