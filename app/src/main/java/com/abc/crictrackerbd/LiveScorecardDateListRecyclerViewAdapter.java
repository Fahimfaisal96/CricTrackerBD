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

class LiveScorecardDateListRecyclerViewAdapter extends RecyclerView.Adapter<LiveScorecardDateListRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "Recycler View";

    private ArrayList<String> mLiveScorecardDate = new ArrayList<>();
    private ArrayList<String> mLiveScorecardFormat = new ArrayList<>();
    private ArrayList<String> mLiveScorecardKey = new ArrayList<>();
    private Context context;


    public LiveScorecardDateListRecyclerViewAdapter(Context context, ArrayList<String> mLiveScorecardFormat, ArrayList<String> mLiveScorecardDate, ArrayList<String> mLiveScorecardKey) {
        this.mLiveScorecardDate = mLiveScorecardDate;
        this.mLiveScorecardFormat = mLiveScorecardFormat;
        this.mLiveScorecardKey = mLiveScorecardKey;
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
    public void onBindViewHolder(@NonNull LiveScorecardDateListRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: " + i);

        viewHolder.DateListName.setText(mLiveScorecardDate.get(i));


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Single Teacher Page
                if(mLiveScorecardFormat.get(i).equals("test")) {
                    Intent intent = new Intent(context, showSingleLiveScorecard2.class);
                    intent.putExtra("showKey", mLiveScorecardKey.get(i));
                    intent.putExtra("LivescoreFormat", mLiveScorecardFormat.get(i));
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, showSingleLiveScorecard.class);
                    intent.putExtra("showKey", mLiveScorecardKey.get(i));
                    intent.putExtra("LivescorecardFormat", mLiveScorecardFormat.get(i));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLiveScorecardDate.size();
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
