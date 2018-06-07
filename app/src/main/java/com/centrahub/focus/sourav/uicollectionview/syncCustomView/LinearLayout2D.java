package com.centrahub.focus.sourav.uicollectionview.syncCustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LinearLayout2D extends LinearLayout {
    Paint rectPaint;

    public LinearLayout2D(Context context)
    {
        super(context);
        init(context);
    }

    private void init(Context context)
    {
        rectPaint = new Paint();
//        rectPaint.setDither(true);
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setStrokeJoin(Paint.Join.ROUND);
        rectPaint.setStrokeCap(Paint.Cap.ROUND);
        rectPaint.setAntiAlias(true);

    }

    public LinearLayout2D(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(context);

    }

    public LinearLayout2D(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init(context);

    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
//        setBackgroundColor(Color.GREEN);

        setGradient(Color.WHITE, Color.LTGRAY);
//        setGradient(Color.parseColor("#ececec"),Color.parseColor("#fdfdfd"));
        canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);

        super.dispatchDraw(canvas);
    }


    public void setGradient(int sColor, int eColor)
    {
        rectPaint.setShader(new LinearGradient(0, 0, 0, getHeight() / 2,
                new int[]{sColor, eColor},
                new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
    }


}
