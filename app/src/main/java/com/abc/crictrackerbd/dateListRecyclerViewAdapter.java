package com.abc.crictrackerbd;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class dateListRecyclerViewAdapter extends RecyclerView.Adapter<dateListRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "Recycler View";

    private ArrayList<String> mFixtureDate = new ArrayList<>();
    private ArrayList<String> mFixtureFormat = new ArrayList<>();
    private ArrayList<String> mFixtureKey = new ArrayList<>();
    private Context context;


    public dateListRecyclerViewAdapter(Context context, ArrayList<String> mFixtureFormat, ArrayList<String> mFixtureDate, ArrayList<String> mFixtureKey) {
        this.mFixtureDate = mFixtureDate;
        this.mFixtureFormat = mFixtureFormat;
        this.mFixtureKey = mFixtureKey;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listofdates,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull dateListRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: " + i);

        viewHolder.DateListName.setText(mFixtureDate.get(i));


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Single Teacher Page
                Intent intent = new Intent(context,showSingleFixture.class);
                intent.putExtra("showKey",mFixtureKey.get(i));
                intent.putExtra("FixtureFormat",mFixtureFormat.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFixtureDate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView DateListName;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DateListName = itemView.findViewById(R.id.fixture_date);
            parentLayout = itemView.findViewById(R.id.date_element_layout);
        }
    }
}

