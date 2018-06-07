package com.centrahub.focus.sourav.uicollectionview.syncCustomView;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
public class SyncDrawable extends Drawable {
private static final float[] VIEW_BOX = { 0.000000f, 0.000000f, 561.000000f, 561.000000f };
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
p.reset();
p.moveTo(factorScale * 280.500000f, factorScale * 76.500000f);
p.lineTo(factorScale * 280.500000f, factorScale * 0.000000f);
p.rLineTo(factorScale * -102.000000f, factorScale * 102.000000f);
p.rLineTo(factorScale * 102.000000f, factorScale * 102.000000f);
p.rLineTo(0, factorScale * -76.500000f);
p.rCubicTo(factorScale * 84.150002f, factorScale * 0.000000f, factorScale * 153.000000f, factorScale * 68.849998f, factorScale * 153.000000f, factorScale * 153.000000f);
p.rCubicTo(factorScale * 0.000000f, factorScale * 25.500000f, factorScale * -7.650000f, factorScale * 51.000000f, factorScale * -17.850000f, factorScale * 71.400002f);
p.rLineTo(factorScale * 38.250000f, factorScale * 38.250000f);
p.cubicTo(factorScale * 471.750000f, factorScale * 357.000000f, factorScale * 484.500000f, factorScale * 321.299988f, factorScale * 484.500000f, factorScale * 280.500000f);
p.cubicTo(factorScale * 484.500000f, factorScale * 168.300003f, factorScale * 392.700012f, factorScale * 76.500000f, factorScale * 280.500000f, factorScale * 76.500000f);
p.close();
p.moveTo(factorScale * 280.500000f, factorScale * 76.500000f);
p.moveTo(factorScale * 280.500000f, factorScale * 433.500000f);
p.rCubicTo(factorScale * -84.150002f, factorScale * 0.000000f, factorScale * -153.000000f, factorScale * -68.849998f, factorScale * -153.000000f, factorScale * -153.000000f);
p.rCubicTo(factorScale * 0.000000f, factorScale * -25.500000f, factorScale * 7.650000f, factorScale * -51.000000f, factorScale * 17.850000f, factorScale * -71.400002f);
p.rLineTo(factorScale * -38.250000f, factorScale * -38.250000f);
p.cubicTo(factorScale * 89.250000f, factorScale * 204.000000f, factorScale * 76.500000f, factorScale * 239.699997f, factorScale * 76.500000f, factorScale * 280.500000f);

p.rCubicTo(factorScale * 0.000000f, factorScale * 112.199997f, factorScale * 91.800003f, factorScale * 204.000000f, factorScale * 204.000000f, factorScale * 204.000000f);
p.lineTo(factorScale * 280.500000f, factorScale * 561.000000f);
p.rLineTo(factorScale * 102.000000f, factorScale * -102.000000f);
p.rLineTo(factorScale * -102.000000f, factorScale * -102.000000f);
p.lineTo(factorScale * 280.500000f, factorScale * 433.500000f);
p.close();
p.moveTo(factorScale * 280.500000f, factorScale * 433.500000f);
p.setFillType(Path.FillType.EVEN_ODD);
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawPath(p, paint);
paint.setAlpha(255);
canvas.restore();
}
@Override public void setAlpha(int alpha) { }
@Override public void setColorFilter(ColorFilter cf) { }
@Override public int getOpacity() { return 0; }
@Override public int getMinimumHeight() { return 10; }
@Override public int getMinimumWidth() { return 10; }
}

