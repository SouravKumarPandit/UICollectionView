package com.centrahub.focus.sourav.uicollectionview.HexagonalButton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.centrahub.focus.sourav.uicollectionview.R;

@SuppressLint("AppCompatCustomView")
public class HexaButton extends Button {


    private final boolean isTransparentButton;

 /*   public int getViewAlpha()
    {
        return viewAlpha;
    }*/

    public void setViewAlpha(int viewAlpha)
    {
        this.viewAlpha = viewAlpha;
        init();
        invalidate();
    }

    private int viewAlpha;
    private final boolean isFlatButton;
    private float strokeWidth;
    Paint buttonPaint;
    Paint shadowPaint;


    private Path cropPath;
    private Point point1_draw;
    private Point point2_draw;
    private Point point3_draw;
    private Point point4_draw;
    private Point point5_draw;
    private Point point6_draw;

    public HexaButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HexaButton,
                0, 0
        );

        try {
            strokeWidth = a.getDimension(R.styleable.HexaButton_strokeWidth, 4.0f);
            isTransparentButton = a.getBoolean(R.styleable.HexaButton_isTransparentButton, false);
            isFlatButton = a.getBoolean(R.styleable.HexaButton_isFlatButton, false);
            viewAlpha = a.getInt(R.styleable.HexaButton_setButtonAlpha, 255);

        } finally {
            a.recycle();
        }
        init();

    }


    private void init()
    {
        buttonPaint = new Paint();
        buttonPaint.setDither(true);
        buttonPaint.setStyle(Paint.Style.FILL);
        buttonPaint.setStrokeJoin(Paint.Join.ROUND);
        buttonPaint.setStrokeCap(Paint.Cap.ROUND);
        buttonPaint.setAntiAlias(true);
        buttonPaint.setColor(Color.WHITE);
        buttonPaint.setAlpha(viewAlpha);

        shadowPaint = new Paint();
        shadowPaint.setDither(true);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setStrokeJoin(Paint.Join.ROUND);
        shadowPaint.setStrokeCap(Paint.Cap.ROUND);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(Color.BLACK);
        shadowPaint.setAlpha(viewAlpha);

        cropPath = new Path();
        point1_draw = new Point();
        point2_draw = new Point();
        point3_draw = new Point();
        point4_draw = new Point();
        point5_draw = new Point();
        point6_draw = new Point();


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        setBackground(null);
        drawshadowRect(canvas);
        super.onDraw(canvas);
    }

    public void drawshadowRect(Canvas canvas)
    {


        point1_draw.set(canvas.getHeight() / 3, 0);
        point2_draw.set(0, canvas.getHeight() / 2);
        point3_draw.set(canvas.getHeight() / 3, canvas.getHeight());
        point4_draw.set(canvas.getWidth() - canvas.getHeight() / 3, canvas.getHeight());
        point5_draw.set(canvas.getWidth(), canvas.getHeight() / 2);
        point6_draw.set(canvas.getWidth() - canvas.getHeight() / 3, 0);


        cropPath.moveTo(point1_draw.x, point1_draw.y);
        cropPath.lineTo(point2_draw.x, point2_draw.y);
        cropPath.lineTo(point3_draw.x, point3_draw.y);
        cropPath.lineTo(point4_draw.x, point4_draw.y);
        cropPath.lineTo(point5_draw.x, point5_draw.y);
        cropPath.lineTo(point5_draw.x, point5_draw.y);
        cropPath.lineTo(point6_draw.x, point6_draw.y);
        cropPath.close();
        if (isTransparentButton) {
            buttonPaint.setStyle(Paint.Style.STROKE);
            buttonPaint.setStrokeWidth(strokeWidth);

            shadowPaint.setStyle(Paint.Style.STROKE);
            shadowPaint.setStrokeWidth(strokeWidth);
        }
        if (isFlatButton) {
            shadowPaint.setShader(null);
            shadowPaint.setShadowLayer(8, 4, 4, 0xaa000000);
            canvas.drawPath(cropPath, shadowPaint);
        }
        canvas.drawPath(cropPath, buttonPaint);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l)
    {
        setViewAlpha(125);
        super.setOnTouchListener(l);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setViewAlpha(200);
                break;
            case MotionEvent.ACTION_UP:
                setViewAlpha(255);
                break;


        }
        return super.onTouchEvent(event);
    }


/*
    setOnTouchListener(new OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                imageAddSign.setImageResource(R.drawable.a);
            }else{
                imageAddSign.setImageResource(R.drawable.b);
            }
            return false;
        }
    });*/
}