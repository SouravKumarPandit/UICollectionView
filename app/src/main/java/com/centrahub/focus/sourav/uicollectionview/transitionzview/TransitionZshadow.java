package com.centrahub.focus.sourav.uicollectionview.transitionzview;

import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.centrahub.focus.sourav.uicollectionview.R;

public class TransitionZshadow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getIconView());
    }

    public View getIconView()
    {
        LinearLayout transitionLyout = new LinearLayout(this);
        transitionLyout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        transitionLyout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams((int)dpToPixel(100), (int)dpToPixel(100)));

        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setCornerRadius(75);
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(getResources().getColor(android.R.color.white ));
        int i5dp= (int) dpToPixel(5);
//        gradientDrawable.setStroke(i5dp,Color.GREEN);

        imageView.setBackground(gradientDrawable);
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher_round));
        imageView.setPadding(i5dp,i5dp,i5dp,i5dp);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setOutlineProvider(new MusicOutlineProvider());
            imageView.setTranslationZ(dpToPixel(8));
        }

        transitionLyout.addView(imageView);

        return transitionLyout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class MusicOutlineProvider extends ViewOutlineProvider {
        Path path = new Path();

        {
            RectF innerCircle = new RectF();
            innerCircle.set(0,0,dpToPixel(100),dpToPixel(100));
            path.reset();
            path.arcTo(innerCircle, 0,359.9f, false);
            path.close();
//            path.moveTo(0, dpToPixel(10));
//            path.lineTo(dpToPixel(7), dpToPixel(2));
//            path.lineTo(dpToPixel(116), dpToPixel(58));
//            path.lineTo(dpToPixel(116), dpToPixel(70));
//            path.lineTo(dpToPixel(7), dpToPixel(128));
//            path.lineTo(0, dpToPixel(120));
//            path.close();
        }

        @Override
        public void getOutline(View view, Outline outline)
        {
            outline.setConvexPath(path);
        }
    }
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
