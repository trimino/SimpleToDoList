package com.example.simpletodolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    // MainActivity will implement
    public interface OnLongClickListener{
        // The class that will implement this needs to now the position of where the user
        // did the long press, so it notify the adapter that this is the position
        // that was long clicked
        void onItemLongClickListener(int position);
    }

    // Class members
    List<String> items;
    OnLongClickListener longClickListener;

    // Constructor
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
    }

    // Involves inflating a layout from XML and returning the ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    // Taking data from a particular position and putting it in a view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at a certain position
        String item = items.get(position);
        // Bind the item into the specify view holder
        holder.bind(item);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    // Provide a direct reference to each of the views within a data item
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        // Created a constructor that accepts the entire item row (the sentence)
        // and does the view lookups to find each subview
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder wih this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener on which position was long pressed
                    longClickListener.onItemLongClickListener(getAdapterPosition());
                    // callback is consuming the long click
                    return true;
                }
            });
        }
    }
}
