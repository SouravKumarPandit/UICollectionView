package com.centrahub.focus.sourav.uicollectionview.NestedPopUpWindow;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.LinearLayout;

public class PopUpWindowLayout extends LinearLayout {

    private Paint bgPaint;
    Path baloonPath;
    Path trangelPath;

    int []colorValArr=new int[]{0xffe2f7dc,0xffBDC3CF,0xffffe2d8,0xffe2ccff,0xffafcbff,0xffc1ffc5};

    public PopUpWindowLayout(Context context)
    {
        super(context);
        init(context);

    }

    public  void  setBgPaintColor(int i){
        if (i<5)
        bgPaint.setColor(colorValArr[i]);
        invalidate();
    }

    public PopUpWindowLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(context);

    }

    private void init(Context context)
    {
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER);


        setPadding((int) dpToPixel(25), (int) dpToPixel(10), (int) dpToPixel(10), (int) dpToPixel(15));
       /* rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.GRAY);
        rectPaint.setStrokeCap(Paint.Cap.ROUND);
        rectPaint.setStrokeWidth(dpToPixel(2));*/
//        rectPaint.setAntiAlias(true);

        bgPaint = new Paint();
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.WHITE);
        bgPaint.setAntiAlias(true);

        baloonPath = new Path();
        trangelPath = new Path();


    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
       getTickerPath(dpToPixel(12),dpToPixel(50));

        canvas.drawPath(trangelPath, bgPaint);
        roundedRect(dpToPixel(10), 0, canvas.getWidth(), canvas.getHeight(), dpToPixel(15), dpToPixel(15), false);

        canvas.drawPath(baloonPath, bgPaint);
        canvas.save();
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    public void roundedRect(float left, float top, float right, float bottom, float rx, float ry, boolean conformToOriginalPost)
    {
//        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        baloonPath.moveTo(right, top + ry);
        baloonPath.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        baloonPath.rLineTo(-widthMinusCorners, 0);
        baloonPath.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        baloonPath.rLineTo(0, heightMinusCorners);

        if (conformToOriginalPost) {
            baloonPath.rLineTo(0, ry);
            baloonPath.rLineTo(width, 0);
            baloonPath.rLineTo(0, -ry);
        } else {
            baloonPath.rQuadTo(0, ry, rx, ry);//bottom-left corner
            baloonPath.rLineTo(widthMinusCorners, 0);
            baloonPath.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        }

        baloonPath.rLineTo(0, -heightMinusCorners);

        baloonPath.close();//Given close, last lineto can be removed.

//        return path;
    }


    public void setGradient(int sColor, int eColor)
    {
        bgPaint.setShader(new LinearGradient(0, 0, 0, getHeight() / 2,
                new int[]{sColor, eColor},
                new float[]{0.0f, 1.0f}, Shader.TileMode.MIRROR));
    }

    public static int getScreenWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight()
    {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float dpToPixel(float dp)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    public void  getTickerPath(float positionX, float positionY)
    {
        trangelPath.reset();
        trangelPath.moveTo(0, dpToPixel(35));
        trangelPath.lineTo(dpToPixel(12), dpToPixel(25));
        trangelPath.lineTo(dpToPixel(12), dpToPixel(50));
        trangelPath.lineTo(0, dpToPixel(35));
        trangelPath.close();
    }

}
