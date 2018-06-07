package com.centrahub.focus.sourav.uicollectionview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.centrahub.focus.sourav.uicollectionview.poupsActivity.MChatDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroler);
        ImageView imageView=findViewById(R.id.image_tester);
        imageView.setImageDrawable(new MChatDrawable());
      /*  HexaButton shadowedButton=findViewById(R.id.get_started);
        shadowedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Tosted",Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
