package com.centrahub.focus.sourav.uicollectionview.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.centrahub.focus.sourav.uicollectionview.R;

public class RotationScaleing extends ImageView {

  private int rotationDegrees = 0;
  private float scale;
  private int directionScale;

  public RotationScaleing(Context context) {
    super(context);
    init();
  }

  public RotationScaleing(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public RotationScaleing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.delete_click);
    setImageBitmap(bitmap);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
    canvas.rotate(rotation(1));
    float scaleFactor = scale(0.001f);

    canvas.scale(scaleFactor, scaleFactor);
    canvas.translate(-canvas.getWidth()/2, -canvas.getHeight()/2);
    postInvalidateOnAnimation();
    super.onDraw(canvas);
  }

  private float scale(float delta) {
    scale = (scale + delta * directionScale);
    if (scale <= 0.8f) {
      directionScale = 1;
      scale = 0.8f;
    } else if (scale >= 1) {
      directionScale = -1;
      scale = 1;
    }
    return scale;
  }

  private int rotation(int delta) {
    rotationDegrees = (rotationDegrees + delta) % 360;
    return rotationDegrees;
  }
}