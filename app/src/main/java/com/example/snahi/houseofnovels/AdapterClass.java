package com.example.snahi.houseofnovels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Viewholder> {
    Context context;
    List<BookModel> lists;

    public AdapterClass(Context context, List<BookModel> list) {
        this.context = context;
        lists = list;
    }

    @NonNull
    @Override
    public AdapterClass.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.Viewholder viewholder, int i) {
        viewholder.title.setText((CharSequence) lists.get(i).getTitle());
        String link = lists.get(i).getImglink();
        Picasso.with(context).load(String.valueOf(link)).placeholder(R.mipmap.ic_launcher).into(viewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, id, author;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.booktitle);
            imageView = itemView.findViewById(R.id.bookimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("key", lists.get(pos).getId());
                    i.putExtra("title", lists.get(getAdapterPosition()).getTitle());
                    i.putExtra("author", lists.get(getAdapterPosition()).getAuthor());
                    i.putExtra("publisher", lists.get(getAdapterPosition()).getPublisher_name());
                    i.putExtra("p_date", lists.get(getAdapterPosition()).getPublish_date());
                    i.putExtra("image", lists.get(getAdapterPosition()).getImglink());
                    i.putExtra("price", lists.get(getAdapterPosition()).getPrice());
                    i.putExtra("desc", lists.get(getAdapterPosition()).getDescription());
                    context.startActivity(i);



                }
            });
        }
    }
}
