package com.centrahub.focus.sourav.uicollectionview.multiarcframe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.centrahub.focus.sourav.uicollectionview.R;

public class MultiArcView extends View {


    private final RectF oval;
    private RectF outterCircle;
    private float radius;


    Paint paintLeft;
    Paint paintCenter;
    Paint paintRight;


    Path myPath;

    RectF shadowRectF;
    private PointF point1;
    private PointF point2;

    public MultiArcView(Context context, AttributeSet attrs)
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

        paintCenter = new Paint();
        paintCenter.setDither(true);
        paintCenter.setStyle(Paint.Style.FILL);
        paintCenter.setStrokeJoin(Paint.Join.ROUND);
        paintCenter.setStrokeCap(Paint.Cap.ROUND);
        paintCenter.setAntiAlias(true);
        paintCenter.setStrokeWidth(2);
        paintCenter.setAlpha(200);


        paintLeft = new Paint();
        paintLeft.setDither(true);
        paintLeft.setStyle(Paint.Style.STROKE);
        paintLeft.setColor(Color.RED);
        paintLeft.setStrokeJoin(Paint.Join.ROUND);
        paintLeft.setStrokeCap(Paint.Cap.ROUND);
        paintLeft.setAntiAlias(true);
        paintLeft.setStrokeWidth(4);
        paintLeft.setAlpha(120);


        paintRight = new Paint();
        paintRight.setDither(true);
        paintRight.setColor(Color.GREEN);
        paintRight.setStyle(Paint.Style.FILL);
        paintRight.setStrokeJoin(Paint.Join.ROUND);
        paintRight.setStrokeCap(Paint.Cap.ROUND);
        paintRight.setAntiAlias(true);
        paintRight.setColor(Color.BLACK);
//        paintRight.setStrokeWidth(radius / 14.0f);
        paintRight.setAlpha(160);


        myPath = new Path();
        shadowRectF = new RectF();
        outterCircle = new RectF();
        oval = new RectF();

        point1 = new PointF();
        point2 = new PointF();
        float adjust = 0.038f * radius;
        outterCircle.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        shadowRectF.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(shadowRectF, paintLeft);
        point1.set(0,canvas.getHeight());
        point2.set(canvas.getWidth(),0);




        Paint paint  = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        final Path path = new Path();
        float x1=point1.x;
        float y1=point1.y;
        float x2=point2.x;
        float y2=point2.y;
        int curveRadius=100;
        int midX            = (int) (x1 + ((x2 - x1) / 2));
        int midY            = (int) (y1 + ((y2 - y1) / 2));
        float xDiff         = midX - x2;
        float yDiff         = midY - y2;
        double angle        = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 120;
        double angleRadians = Math.toRadians(angle*2);
        float pointX        = (float) (midX + curveRadius * Math.cos(angleRadians));
        float pointY        = (float) (midY + curveRadius * Math.sin(angleRadians));

        path.moveTo(x1, y1);
        path.cubicTo(x1,y1,pointX*2, pointY*2, x2, y2);
        canvas.drawPath(path, paint);


    }

}