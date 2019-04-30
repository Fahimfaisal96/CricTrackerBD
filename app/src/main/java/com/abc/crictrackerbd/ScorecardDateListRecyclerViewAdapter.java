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

class ScorecardDateListRecyclerViewAdapter extends RecyclerView.Adapter<ScorecardDateListRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "Recycler View";

    private ArrayList<String> mScorecardDate = new ArrayList<>();
    private ArrayList<String> mScorecardFormat = new ArrayList<>();
    private ArrayList<String> mScorecardKey = new ArrayList<>();
    private Context context;


    public ScorecardDateListRecyclerViewAdapter(Context context, ArrayList<String> mScorecardFormat, ArrayList<String> mScorecardDate, ArrayList<String> mScorecardKey) {
        this.mScorecardDate = mScorecardDate;
        this.mScorecardFormat = mScorecardFormat;
        this.mScorecardKey = mScorecardKey;
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
    public void onBindViewHolder(@NonNull ScorecardDateListRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: " + i);

        viewHolder.DateListName.setText(mScorecardDate.get(i));


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Single Teacher Page
                if(mScorecardFormat.get(i).equals("test")) {
                    Intent intent = new Intent(context, showSingleScorecard2.class);
                    intent.putExtra("showKey", mScorecardKey.get(i));
                    intent.putExtra("ScorecardFormat", mScorecardFormat.get(i));
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, showSingleScorecard.class);
                    intent.putExtra("showKey", mScorecardKey.get(i));
                    intent.putExtra("ScorecardFormat", mScorecardFormat.get(i));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mScorecardDate.size();
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
