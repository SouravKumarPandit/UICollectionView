package com.centrahub.focus.sourav.uicollectionview.syncCustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleSyncView extends View {

    String syncing = " Syncing";
    String toSync = " To Sync";
    String synced = "  Synced";
    String valueSync = "07";
    public static int TO_SYNC = 0;
    public static int SYNCED = 1;
    public static int SYNCING = 2;

    private int rotationDegrees = 0;
    private int colorSyncingLable;
    private int colorValue;
    private int colorSyncingBg;
    private int colorSyncedBg;
    private int colorToSyncBg;
    private int colorSyncedLable;
    private int colorToSyncLable;


    public int getSyncStateIn()
    {
        return syncStateIn;
    }

    public void setSyncStateIn(int syncStateIn)
    {
        this.syncStateIn = syncStateIn;
        invalidate();

    }

    int syncStateIn = 0;

    int lableSize = 40;
    int valueSize = 50;

    Paint painLable;
    Paint paintValue;

    float measuredlableWidth;
    float measuredTextValuewidth;
    private float measuredlableHeight;
    private float measuredValueHeight;
    private Drawable mDrawable;

    public SimpleSyncView(Context context)
    {
        super(context);

        colorSyncingLable = Color.BLACK;
        colorSyncedLable = Color.BLACK;
        colorToSyncLable = Color.BLACK;
        colorValue = Color.WHITE;
        colorSyncingBg = Color.BLACK;
        colorSyncedBg = Color.GREEN;
        colorToSyncBg = Color.RED;
        init(context);

    }


    public SimpleSyncView(Context context, @Nullable AttributeSet attrs)
    {
        super(context);

        init(context);
    }

    private void init(Context context)
    {


        painLable = new Paint(Paint.ANTI_ALIAS_FLAG);
        painLable.setTextSize(lableSize * getResources().getDisplayMetrics().density);
//        painLable.setTextSize(size );
        painLable.setColor(colorSyncingLable);


        paintValue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintValue.setTextSize(valueSize * getResources().getDisplayMetrics().density);
//        paintValue.setTextSize(size );
        paintValue.setColor(colorValue);

        measuredlableWidth = painLable.measureText(syncing);
        measuredTextValuewidth = paintValue.measureText(valueSync);
        measuredlableHeight = lableSize * getResources().getDisplayMetrics().density;
        measuredValueHeight = valueSize * getResources().getDisplayMetrics().density;

        int x = (int) (measuredTextValuewidth / 2);
        int y = (int) (measuredTextValuewidth / 4);
        int width = (int) (measuredTextValuewidth );
        int height = (int) (measuredTextValuewidth);

        mDrawable = new SyncDrawable();
        mDrawable.setBounds(x, y, x + width, y + height);

    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (syncStateIn == 2) {

//            canvas.save();
            painLable.setColor(colorSyncingBg);
            canvas.drawRect(0, 0, measuredTextValuewidth * 2, canvas.getHeight(), painLable);
            painLable.setColor(colorSyncingLable);
            canvas.drawText(syncing, measuredTextValuewidth * 2, canvas.getHeight() / 2 + measuredlableHeight / 3, painLable);
//            canvas.drawText(valueSync, measuredTextValuewidth * 3 / 2 - measuredTextValuewidth / 2, canvas.getHeight() / 2 + measuredValueHeight / 2, paintValue);
            canvas.rotate(rotation(8), measuredTextValuewidth , canvas.getHeight() / 2);
            mDrawable.draw(canvas);
            postInvalidateOnAnimation();

        } else if (syncStateIn == 1) {

            canvas.save();
            painLable.setColor(colorSyncedBg);
            canvas.drawRect(0, 0, measuredTextValuewidth * 2, canvas.getHeight(), painLable);
            painLable.setColor(colorSyncedLable);
            canvas.drawText(synced, measuredTextValuewidth * 2, canvas.getHeight() / 2 + measuredlableHeight / 3, painLable);
            canvas.drawText(valueSync, measuredTextValuewidth  - measuredTextValuewidth / 2, canvas.getHeight() / 2 + measuredValueHeight / 2, paintValue);
            canvas.restore();
        } else {
            canvas.save();
            painLable.setColor(colorToSyncBg);
            canvas.drawRect(0, 0, measuredTextValuewidth * 2, canvas.getHeight(), painLable);
            painLable.setColor(colorToSyncLable);
            canvas.drawText(toSync, measuredTextValuewidth * 2, canvas.getHeight() / 2 + measuredlableHeight / 3, painLable);
            canvas.drawText(valueSync, measuredTextValuewidth - measuredTextValuewidth / 2, canvas.getHeight() / 2 + measuredValueHeight / 2, paintValue);
            canvas.restore();

        }
    }

    private int rotation(int delta)
    {
        rotationDegrees = (rotationDegrees + delta) % 360;
        return -rotationDegrees;
    }

}
