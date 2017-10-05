package com.earchcor.www.votingapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 05/10/17.
 */

public class CustomVote extends ArrayAdapter<String> {

    Activity context;
    String [] ids;
    String [] names;
    String [] votes;
    Integer[] photos;

    public CustomVote(Activity context , int resource , String[] i , String[] n , String[] v , Integer[] p){
        super(context , resource , i);

        this.context = context;
        ids = i;
        names = n;
        votes = v;
        photos = p;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.my_row, null, true);

        TextView tvId = view.findViewById(R.id.star_id);
        TextView tvName = view.findViewById(R.id.star_type);
        TextView tvVotes = view.findViewById(R.id.star_vote);

        ImageView iv = view.findViewById(R.id.star_photo);

        tvId.setText(ids[position]);
        tvName.setText(names[position]);
        tvVotes.setText(votes[position]);
        iv.setImageResource(photos[position]);

        return view;
    }
}
