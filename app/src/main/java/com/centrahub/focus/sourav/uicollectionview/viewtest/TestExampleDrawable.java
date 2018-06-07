package com.centrahub.focus.sourav.uicollectionview.viewtest;
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
public class TestExampleDrawable extends Drawable {
private static final float[] VIEW_BOX = { 0.000000f, 0.000000f, 512.000000f, 512.000000f };
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
paint.setAlpha(255);
paint.setAlpha(255);
paint.setAlpha(255);
paint.setAlpha(255);
{
int[] colors =  { -1949085, -2286009 };
float[] positions =  { 0.0f, 1.0f };
shader = new LinearGradient(-0.000003f, 256.000000f, 512.000000f, 256.000000f, colors, positions, Shader.TileMode.CLAMP);
}
paint.setShader(shader);
paint.setStyle(Paint.Style.FILL);
canvas.drawCircle(factorScale * 256.000000f, factorScale * 256.000000f, factorScale * 256.000000f, paint);
paint.setAlpha(255);
paint.setAlpha(255);
paint.setAlpha(255);
paint.setAlpha(255);
p.reset();
p.moveTo(factorScale * 256.000000f, factorScale * 469.333862f);
p.rCubicTo(factorScale * -117.631470f, factorScale * 0.000000f, factorScale * -213.333344f, factorScale * -95.702393f, factorScale * -213.333344f, factorScale * -213.333344f);
p.rCubicTo(factorScale * 0.000000f, factorScale * -117.631454f, factorScale * 95.701866f, factorScale * -213.333313f, factorScale * 213.333344f, factorScale * -213.333313f);
p.rCubicTo(factorScale * 117.636261f, factorScale * 0.000000f, factorScale * 213.333344f, factorScale * 95.701866f, factorScale * 213.333344f, factorScale * 213.333313f);
p.cubicTo(factorScale * 469.333344f, factorScale * 373.631470f, factorScale * 373.636261f, factorScale * 469.333862f, factorScale * 256.000000f, factorScale * 469.333862f);
p.close();
p.moveTo(factorScale * 256.000000f, factorScale * 469.333862f);
{
int[] colors =  { -2286009, -1949085 };
float[] positions =  { 0.0f, 1.0f };
shader = new LinearGradient(42.666664f, 256.000519f, 469.333344f, 256.000519f, colors, positions, Shader.TileMode.CLAMP);
}
paint.setShader(shader);
paint.setStyle(Paint.Style.FILL);
canvas.drawPath(p, paint);
paint.setAlpha(255);
paint.setAlpha(255);
p.reset();

p.moveTo(factorScale * 369.104004f, factorScale * 158.895996f);
p.rCubicTo(factorScale * -19.854950f, factorScale * -19.854935f, factorScale * -52.044800f, factorScale * -19.854935f, factorScale * -71.898682f, factorScale * 0.000000f);
p.rLineTo(factorScale * -10.553741f, factorScale * 10.554138f);
p.rLineTo(factorScale * 71.898285f, factorScale * 71.898132f);
p.rLineTo(factorScale * 10.554138f, factorScale * -10.553604f);
p.cubicTo(factorScale * 388.957855f, factorScale * 210.940796f, factorScale * 388.957855f, factorScale * 178.751465f, factorScale * 369.104004f, factorScale * 158.895996f);
p.close();
p.moveTo(factorScale * 369.104004f, factorScale * 158.895996f);
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);
canvas.drawPath(p, paint);
paint.setAlpha(255);
p.reset();
p.moveTo(factorScale * 144.005859f, factorScale * 312.094391f);
p.lineTo(factorScale * 146.770660f, factorScale * 381.228271f);
p.lineTo(factorScale * 215.904266f, factorScale * 383.994659f);
p.lineTo(factorScale * 227.054291f, factorScale * 372.843719f);
p.lineTo(factorScale * 155.156265f, factorScale * 300.945587f);
p.close();
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);
canvas.drawPath(p, paint);
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707107f, factorScale * 0.707107f, factorScale * -116.499344f, factorScale * -0.707107f, factorScale * 0.707107f, factorScale * 261.039581f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);
canvas.drawRect(factorScale * 173.869873f, factorScale * 259.135529f, factorScale * 339.836060f, factorScale * 283.158325f, paint);
canvas.restore();
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707117f, factorScale * 0.707097f, factorScale * -105.830742f, factorScale * -0.707097f, factorScale * 0.707117f, factorScale * 235.278381f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);
canvas.drawRect(factorScale * 148.113785f, factorScale * 230.975723f, factorScale * 314.079315f, factorScale * 259.805847f, paint);
canvas.restore();
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707116f, factorScale * 0.707097f, factorScale * -127.167984f, factorScale * -0.707097f, factorScale * 0.707116f, factorScale * 286.790588f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setStyle(Paint.Style.FILL);
paint.setColor(0xFF000000);
canvas.drawRect(factorScale * 199.626389f, factorScale * 282.488403f, factorScale * 365.592163f, factorScale * 311.318573f, paint);
canvas.restore();
paint.setAlpha(255);
paint.setAlpha(255);
p.reset();
p.moveTo(factorScale * 361.104004f, factorScale * 150.895996f);
p.rCubicTo(factorScale * -19.854950f, factorScale * -19.854935f, factorScale * -52.044800f, factorScale * -19.854935f, factorScale * -71.898926f, factorScale * 0.000000f);
p.rLineTo(factorScale * -10.553497f, factorScale * 10.554138f);
p.rLineTo(factorScale * 71.898285f, factorScale * 71.898132f);
p.rLineTo(factorScale * 10.554138f, factorScale * -10.553604f);
p.cubicTo(factorScale * 380.957855f, factorScale * 202.940796f, factorScale * 380.957855f, factorScale * 170.751465f, factorScale * 361.104004f, factorScale * 150.895996f);
p.close();
p.moveTo(factorScale * 361.104004f, factorScale * 150.895996f);
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawPath(p, paint);
paint.setAlpha(255);
p.reset();

p.moveTo(factorScale * 136.005859f, factorScale * 304.094391f);
p.lineTo(factorScale * 138.770660f, factorScale * 373.228271f);
p.lineTo(factorScale * 207.904266f, factorScale * 375.994659f);
p.lineTo(factorScale * 219.054291f, factorScale * 364.843719f);
p.lineTo(factorScale * 147.156265f, factorScale * 292.945587f);
p.close();
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawPath(p, paint);
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707107f, factorScale * 0.707107f, factorScale * -113.185616f, factorScale * -0.707107f, factorScale * 0.707107f, factorScale * 253.039612f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawRect(factorScale * 165.869965f, factorScale * 251.135574f, factorScale * 331.836090f, factorScale * 275.158295f, paint);
canvas.restore();
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707115f, factorScale * 0.707098f, factorScale * -102.517052f, factorScale * -0.707098f, factorScale * 0.707115f, factorScale * 227.279419f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawRect(factorScale * 140.113785f, factorScale * 222.975708f, factorScale * 306.079346f, factorScale * 251.805878f, paint);
canvas.restore();
paint.setAlpha(255);
matrix.reset();
matrix.setValues(new float[]{ factorScale * 0.707116f, factorScale * 0.707097f, factorScale * -123.854271f, factorScale * -0.707097f, factorScale * 0.707116f, factorScale * 278.790741f, 0, 0, factorScale});
canvas.save();
canvas.concat(matrix);
paint.setShader(null);
paint.setColor(-1);
paint.setAlpha(255);
paint.setStyle(Paint.Style.FILL);
canvas.drawRect(factorScale * 191.626389f, factorScale * 274.488373f, factorScale * 357.592163f, factorScale * 303.318542f, paint);
canvas.restore();
canvas.restore();
}
@Override public void setAlpha(int alpha) { }
@Override public void setColorFilter(ColorFilter cf) { }
@Override public int getOpacity() { return 0; }
@Override public int getMinimumHeight() { return 10; }
@Override public int getMinimumWidth() { return 10; }
}

