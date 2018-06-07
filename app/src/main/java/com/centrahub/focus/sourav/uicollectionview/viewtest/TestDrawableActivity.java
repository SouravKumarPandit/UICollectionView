package com.centrahub.focus.sourav.uicollectionview.viewtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.centrahub.focus.sourav.uicollectionview.R;
import com.centrahub.focus.sourav.uicollectionview.syncCustomView.SimpleSyncView;

public class TestDrawableActivity extends AppCompatActivity {


    private static int syncState = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testdrawable_activity);
        LinearLayout linearLayout = findViewById(R.id.ll_lelo);

        final SimpleSyncView customView = new SimpleSyncView(this);
        customView.setBackgroundColor(Color.LTGRAY);

        linearLayout.addView(customView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        linearLayout.setBackground(new SyncDrawable());


        Button button = findViewById(R.id.click_me);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                customView.setSyncStateIn(syncState);
                syncState++;
                if (syncState == 3)
                    syncState = 0;
            }
        });

    }
}
