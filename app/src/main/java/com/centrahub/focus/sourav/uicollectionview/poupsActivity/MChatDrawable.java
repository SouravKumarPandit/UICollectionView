package com.centrahub.focus.sourav.uicollectionview.poupsActivity;
import android.graphics.drawable.Drawable;
import android.graphics.Shader;
import android.graphics.RadialGradient;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
public class MChatDrawable extends Drawable {
private static final float[] VIEW_BOX = { 0.000000f, 0.000000f, 2250.000000f, 2250.000000f };
private RectF rect = new RectF();
private Matrix matrix = new Matrix();
private Shader shader;
private Path p = new Path();
private Paint paint = new Paint();
@Override
public void draw(Canvas canvas) {
paint.setAntiAlias(true);
float viewBoxWidth = VIEW_BOX[2];
float viewBoxHeight = VIEW_BOX[3];
Rect bounds = getBounds();
if (viewBoxHeight <= 0 || viewBoxWidth <= 0 || bounds.width() <= 0 || bounds.height() <= 0) {
return;
}
canvas.save();
float viewBoxRatio = viewBoxWidth / (float) viewBoxHeight;
float boundsRatio = bounds.width() / (float) bounds.height();
float factorScale;
if (boundsRatio > viewBoxRatio) {
 // canvas larger than viewbox
 factorScale = bounds.height() / (float) viewBoxHeight;
} else {
 // canvas higher (or equals) than viewbox
 factorScale = bounds.width() / (float) viewBoxWidth;
}
int newViewBoxHeight = Math.round(factorScale * viewBoxHeight);
int newViewBoxWidth = Math.round(factorScale * viewBoxWidth);
int marginX = bounds.width() - newViewBoxWidth;
int marginY = bounds.height() - newViewBoxHeight;
canvas.translate(bounds.left, bounds.top);
canvas.translate(Math.round(marginX / 2f), Math.round(marginY / 2f));
canvas.clipRect(0, 0, newViewBoxWidth, newViewBoxHeight);
canvas.translate(-Math.round(factorScale * VIEW_BOX[0]), -Math.round(factorScale * VIEW_BOX[1]));
paint.setAlpha(255);
paint.setAlpha(255);
paint.setAlpha(255);
p.reset();
p.moveTo(factorScale * 876.000000f, factorScale * 1939.000000f);
p.rCubicTo(factorScale * -65.000000f, factorScale * -15.000000f, factorScale * -153.000000f, factorScale * -69.000000f, factorScale * -206.000000f, factorScale * -125.000000f);
p.rCubicTo(factorScale * -66.000000f, factorScale * -70.000000f, factorScale * -107.000000f, factorScale * -158.000000f, factorScale * -122.000000f, factorScale * -263.000000f);
p.rLineTo(factorScale * -12.000000f, factorScale * -83.000000f);
p.rLineTo(factorScale * -132.000000f, factorScale * -117.000000f);
p.rCubicTo(factorScale * -72.000000f, factorScale * -64.000000f, factorScale * -141.000000f, factorScale * -132.000000f, factorScale * -153.000000f, factorScale * -152.000000f);
p.rCubicTo(factorScale * -24.000000f, factorScale * -43.000000f, factorScale * -27.000000f, factorScale * -98.000000f, factorScale * -6.000000f, factorScale * -138.000000f);
p.rCubicTo(factorScale * 8.000000f, factorScale * -16.000000f, factorScale * 77.000000f, factorScale * -85.000000f, factorScale * 154.000000f, factorScale * -155.000000f);
p.rCubicTo(factorScale * 149.000000f, factorScale * -135.000000f, factorScale * 141.000000f, factorScale * -122.000000f, factorScale * 157.000000f, factorScale * -241.000000f);
p.rCubicTo(factorScale * 16.000000f, factorScale * -129.000000f, factorScale * 121.000000f, factorScale * -266.000000f, factorScale * 251.000000f, factorScale * -328.000000f);
p.rLineTo(factorScale * 68.000000f, factorScale * -32.000000f);
p.rLineTo(factorScale * 384.000000f, factorScale * -3.000000f);
p.rCubicTo(factorScale * 363.000000f, factorScale * -3.000000f, factorScale * 387.000000f, factorScale * -2.000000f, factorScale * 451.000000f, factorScale * 17.000000f);

p.rCubicTo(factorScale * 112.000000f, factorScale * 34.000000f, factorScale * 205.000000f, factorScale * 114.000000f, factorScale * 260.000000f, factorScale * 221.000000f);
p.rCubicTo(factorScale * 48.000000f, factorScale * 93.000000f, factorScale * 50.000000f, factorScale * 117.000000f, factorScale * 50.000000f, factorScale * 585.000000f);
p.rCubicTo(factorScale * 0.000000f, factorScale * 468.000000f, factorScale * -2.000000f, factorScale * 492.000000f, factorScale * -50.000000f, factorScale * 585.000000f);
p.rCubicTo(factorScale * -53.000000f, factorScale * 104.000000f, factorScale * -143.000000f, factorScale * 180.000000f, factorScale * -258.000000f, factorScale * 219.000000f);
p.rCubicTo(factorScale * -58.000000f, factorScale * 20.000000f, factorScale * -82.000000f, factorScale * 21.000000f, factorScale * -429.000000f, factorScale * 20.000000f);
p.rCubicTo(factorScale * -202.000000f, factorScale * -1.000000f, factorScale * -385.000000f, factorScale * -5.000000f, factorScale * -407.000000f, factorScale * -10.000000f);
p.close();
p.moveTo(factorScale * 876.000000f, factorScale * 1939.000000f);
p.rMoveTo(factorScale * 855.000000f, factorScale * -106.000000f);
p.rCubicTo(factorScale * 79.000000f, factorScale * -39.000000f, factorScale * 140.000000f, factorScale * -102.000000f, factorScale * 177.000000f, factorScale * -181.000000f);
p.rLineTo(factorScale * 27.000000f, factorScale * -57.000000f);
p.rLineTo(factorScale * 0.000000f, factorScale * -470.000000f);
p.rLineTo(factorScale * 0.000000f, factorScale * -470.000000f);
p.rLineTo(factorScale * -27.000000f, factorScale * -57.000000f);
p.rCubicTo(factorScale * -37.000000f, factorScale * -79.000000f, factorScale * -98.000000f, factorScale * -142.000000f, factorScale * -177.000000f, factorScale * -181.000000f);
p.rLineTo(factorScale * -66.000000f, factorScale * -32.000000f);
p.rLineTo(factorScale * -385.000000f, factorScale * 0.000000f);
p.rLineTo(factorScale * -385.000000f, factorScale * 0.000000f);
p.rLineTo(factorScale * -57.000000f, factorScale * 28.000000f);
p.rCubicTo(factorScale * -119.000000f, factorScale * 58.000000f, factorScale * -195.000000f, factorScale * 169.000000f, factorScale * -208.000000f, factorScale * 300.000000f);
p.rCubicTo(factorScale * -4.000000f, factorScale * 40.000000f, factorScale * -14.000000f, factorScale * 86.000000f, factorScale * -23.000000f, factorScale * 103.000000f);
p.rCubicTo(factorScale * -8.000000f, factorScale * 16.000000f, factorScale * -77.000000f, factorScale * 84.000000f, factorScale * -151.000000f, factorScale * 150.000000f);
p.rCubicTo(factorScale * -134.000000f, factorScale * 117.000000f, factorScale * -157.000000f, factorScale * 147.000000f, factorScale * -140.000000f, factorScale * 184.000000f);
p.rCubicTo(factorScale * 5.000000f, factorScale * 10.000000f, factorScale * 70.000000f, factorScale * 73.000000f, factorScale * 145.000000f, factorScale * 139.000000f);
p.rCubicTo(factorScale * 148.000000f, factorScale * 131.000000f, factorScale * 169.000000f, factorScale * 162.000000f, factorScale * 169.000000f, factorScale * 246.000000f);
p.rCubicTo(factorScale * 0.000000f, factorScale * 127.000000f, factorScale * 107.000000f, factorScale * 266.000000f, factorScale * 242.000000f, factorScale * 316.000000f);
p.rCubicTo(factorScale * 49.000000f, factorScale * 17.000000f, factorScale * 79.000000f, factorScale * 19.000000f, factorScale * 423.000000f, factorScale * 16.000000f);
p.rLineTo(factorScale * 370.000000f, factorScale * -2.000000f);
p.rLineTo(factorScale * 66.000000f, factorScale * -32.000000f);
p.close();
p.moveTo(factorScale * 1731.000000f, factorScale * 1833.000000f);
p.setFillType(Path.FillType.EVEN_ODD);
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);

canvas.drawPath(p, paint);
canvas.restore();
}
@Override public void setAlpha(int alpha) { }
@Override public void setColorFilter(ColorFilter cf) { }
@Override public int getOpacity() { return 0; }
@Override public int getMinimumHeight() { return 10; }
@Override public int getMinimumWidth() { return 10; }
}

