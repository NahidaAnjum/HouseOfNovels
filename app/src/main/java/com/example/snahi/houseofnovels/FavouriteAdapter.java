package com.example.snahi.houseofnovels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    List<FavouriteBooks> favouriteBooks;


    public FavouriteAdapter(Context context, List<FavouriteBooks> favouriteBooks) {

        this.context = context;
        this.favouriteBooks = favouriteBooks;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fav_books, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder viewHolder, int i) {
        Picasso.with(context)
                .load(favouriteBooks.get(i).getImglink())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.image_tv);

    }

    @Override
    public int getItemCount() {
        return favouriteBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_tv = itemView.findViewById(R.id.fav_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("key", favouriteBooks.get(pos).getId());
                    i.putExtra("title", favouriteBooks.get(getAdapterPosition()).getTitle());
                    i.putExtra("author", favouriteBooks.get(getAdapterPosition()).getAuthor());
                    i.putExtra("publisher", favouriteBooks.get(getAdapterPosition()).getPublisher_name());
                    i.putExtra("p_date", favouriteBooks.get(getAdapterPosition()).getPublish_date());
                    i.putExtra("image", favouriteBooks.get(getAdapterPosition()).getImglink());
                    i.putExtra("price", favouriteBooks.get(getAdapterPosition()).getPrice());
                    i.putExtra("desc", favouriteBooks.get(getAdapterPosition()).getDescription());
                    context.startActivity(i);
                }
            });
        }
    }
}
