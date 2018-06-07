//package com.centrahub.focus.sourav.uicollectionview.viewtest;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.hardware.SensorListener;
//import android.view.MotionEvent;
//import android.view.ViewGroup;
//
//public class RotateView extends ViewGroup {
//
///* ...  */
//
//    private Matrix invertedMatrix = new Matrix();
//    private float[] tempLocation = new float[2];
//
//    public RotateView(Context context)
//    {
//        super(context);
//    }
//
//
//    @SuppressLint("WrongConstant")
//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        canvas.save(Canvas.MATRIX_SAVE_FLAG);
//        canvas.rotate(mHeading, getWidth() * 0.5f, getHeight() * 0.5f);
//        canvas.getMatrix().invert(invertedMatrix);
//        mCanvas.delegate = canvas;
//        super.dispatchDraw(mCanvas);
//        canvas.restore();
//    }
//
//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3)
//    {
//
//    }
//
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        centeringAllowed = false;
//
//        float[] location = tempLocation;
//        location[0] = ev.getX();
//        location[1] = ev.getY();
//        invertedMatrix.mapPoints(location);
//        ev.setLocation(location[0], location[1]);
//        return super.dispatchTouchEvent(ev);
//    }
//}