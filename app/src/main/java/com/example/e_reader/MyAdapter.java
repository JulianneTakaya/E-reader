package com.example.e_reader;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Book> bookList;
    // constructor
    public MyAdapter(List<Book> bookList) {
        this.bookList = bookList;
    } // MyAdapter
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        return new MyViewHolder(view);
    }// MyViewHolder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvComplete.setText(book.getComplete() ? "Complete" : "Incomplete");
    }// onBindViewHolder
    @Override
    public int getItemCount() {
        return bookList.size();
    }
    // ViewHolder class
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvAuthor, tvComplete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvComplete = itemView.findViewById(R.id.tvComplete);
        }
    }// MyViewHolder class

}// main
