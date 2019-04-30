package com.abc.crictrackerbd;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class playerListRecyclerViewAdapter extends RecyclerView.Adapter<playerListRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "Recycler View";

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImageKeys = new ArrayList<>();
    private Context context;

    public playerListRecyclerViewAdapter( Context context, ArrayList<String> mImages, ArrayList<String> mImageNames, ArrayList<String> mImageKeys) {
        this.mImages = mImages;
        this.mImageNames = mImageNames;
        this.mImageKeys = mImageKeys;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_of_players,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {

        Log.d(TAG, "onBindViewHolder: " + i);

        Glide.with(context)                .asBitmap()
                .load(mImages.get(i))
                .into(viewHolder.playerListImage);

        viewHolder.playerListName.setText(mImageNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Single User Page
                Intent intent = new Intent(context,showSinglePlayer.class);
                intent.putExtra("showKey",mImageKeys.get(i));
                context.startActivity(intent);
                //Toast.makeText(context,mImageNames.get(i),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView playerListImage;
        TextView playerListName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerListImage = itemView.findViewById(R.id.image);
            playerListName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.player_element_layout);
        }
    }
}

