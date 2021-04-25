package com.example.practicum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    //CITE: code adapted from https://demonuts.com/sqlite-multiple-tables/

    private Context context;
    private ArrayList<Review> reviews;

    public ReviewAdapter(Context context, ArrayList<Review> reviews) {

        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
//        return 0;
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReviewAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.review_item, null, true);

            holder.rating = (TextView) convertView.findViewById(R.id.review_detail);
            holder.review = (TextView) convertView.findViewById(R.id.rating_detail);

            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ReviewAdapter.ViewHolder)convertView.getTag();
        }

        holder.rating.setText("Rating: "+reviews.get(position).getRating());
        holder.review.setText("Review: "+reviews.get(position).getReview_text());

        return convertView;
    }

    public class ViewHolder {
        TextView rating, review;
    }
}
