package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class showScorecardFormat extends Activity {
    Button test,odi,t20;
    String nowUer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scorecard_format);
        test = findViewById(R.id.test);
        odi = findViewById(R.id.odi);
        t20 = findViewById(R.id.t20);

        nowUer = getIntent().getStringExtra("current");

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(showScorecardFormat.this,showScorecardDateList.class);
                intent.putExtra("ScorecardFormat","test");
                intent.putExtra("current",nowUer);
                startActivity(intent);
            }
        });

        odi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showScorecardFormat.this,showScorecardDateList.class);
                intent.putExtra("ScorecardFormat","odi");
                intent.putExtra("current",nowUer);
                startActivity(intent);
            }
        });

        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showScorecardFormat.this,showScorecardDateList.class);
                intent.putExtra("ScorecardFormat","t20");
                intent.putExtra("current",nowUer);
                startActivity(intent);
            }
        });
    }
}
