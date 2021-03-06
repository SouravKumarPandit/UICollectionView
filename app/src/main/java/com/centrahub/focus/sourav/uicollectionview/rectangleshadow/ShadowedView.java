package com.centrahub.focus.sourav.uicollectionview.rectangleshadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.centrahub.focus.sourav.uicollectionview.R;

public class ShadowedView extends View {


    private float radius;

    Paint paint;
    Paint rectPaint;



    Path myPath;
    Path rectPath;

    RectF shadowRectF;

    public ShadowedView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DonutChart,
                0, 0
        );

        try {
            radius = a.getDimension(R.styleable.DonutChart_radius, 20.0f);
        } finally {
            a.recycle();
        }

        paint = new Paint();
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(radius / 14.0f);
//        paint.setAlpha(100);

        rectPaint=new Paint();
        rectPaint.setColor(Color.WHITE);


        myPath = new Path();
        rectPath = new Path();
        shadowRectF = new RectF();

        float adjust = (.5f * radius);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawRect(canvas, paint);
    }

    public void drawRect(Canvas canvas, Paint paint)
    {

        paint.setShader(null);
        shadowRectF.set(8, 8, canvas.getWidth() , canvas.getHeight() );
        paint.setShadowLayer(15, 5, 5, 0xaa000000);
        myPath.reset();
        myPath.addRoundRect(shadowRectF, 15, 15, Path.Direction.CCW);
        myPath.close();
        canvas.drawPath(myPath, paint);

        rectPath.reset();
        rectPath.addRoundRect(shadowRectF, 15, 15, Path.Direction.CCW);
        rectPath.close();

        setGradient(0xffFFFF00,0xfffed325);
//        setGradient(Color.parseColor("#ececec"),Color.parseColor("#fdfdfd"));
        canvas.drawPath(rectPath, rectPaint);



    }

    public void setGradient(int sColor, int eColor){
        rectPaint.setShader(new LinearGradient(0, 0,0,getHeight()/2,
                new int[]{sColor,eColor},
                new float[]{0.0f,1.0f}, Shader.TileMode.MIRROR) );
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = (int) radius * 2;
        int desiredHeight = (int) radius * 2;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //70dp exact
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //wrap content
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

}