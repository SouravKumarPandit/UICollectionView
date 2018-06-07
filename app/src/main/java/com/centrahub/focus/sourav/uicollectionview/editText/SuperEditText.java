package com.centrahub.focus.sourav.uicollectionview.editText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.centrahub.focus.sourav.uicollectionview.R;

import java.lang.reflect.Field;

public class SuperEditText extends AppCompatEditText {


    /*
     * Define attribute variables
     * */
    private Paint mPaint; // Brush

    private int ic_deleteResID; // Delete Icon Resource ID
    private Drawable ic_delete; // Delete icon
    private int delete_x, delete_y, delete_width, delete_height; // Delete icon starting point (x,y), delete icon width, height (px)p
    private int ic_left_clickResID, ic_left_unclickResID; // left icon resource ID (click & no click)
    private Drawable ic_left_click, ic_left_unclick; // Left icon (click & not click)
    private int left_x, left_y, left_width, left_height; // left icon starting point (x,y), left icon width, height (px)p
    private int cursor; // cursor

    // Split line variable


    private int lineColor_click, lineColor_unclick;
    private int color;
    private int linePosition;

    public SuperEditText(Context context) {
        super(context);

    }

    public SuperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {// Get control resources
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText);

        /**
         * Initialize the left icon (click & do not click)
         */

        // a. Click the left icon of the status
        // 1. Get the resource ID
        ic_left_clickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_click, R.drawable.ic_left_click);
        // 2. Get icon resource according to resource ID (converted to Drawable object)
        ic_left_click = getResources().getDrawable(ic_left_clickResID);
        // 3. Set the icon size
        // starting point (x,y), width = left_width, height = left_height
        left_x = typedArray.getInteger(R.styleable.SuperEditText_left_x, 0);
        left_y = typedArray.getInteger(R.styleable.SuperEditText_left_y, 0);
        left_width = typedArray.getInteger(R.styleable.SuperEditText_left_width, 60);
        left_height = typedArray.getInteger(R.styleable.SuperEditText_left_height, 60);

        ic_left_click.setBounds(left_x, left_y, left_width, left_height);
        // Drawable.setBounds(x,y,width,height) = Set the Drawable's initial position, width and height information
        // x = start of the component on the X axis of the container, y = start of the component on the Y axis of the container, width = length of the component, height = height of the component

        // b. The icon to the left of the unclicked state
        // 1. Get the resource ID
        ic_left_unclickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_unclick, R.drawable.ic_left_unclick);
        // 2. Get icon resource according to resource ID (converted to Drawable object)
        // 3. Set the icon size (here the default left icon is the same size as the unclicked state)
        ic_left_unclick = getResources().getDrawable(ic_left_unclickResID);
        ic_left_unclick.setBounds(left_x, left_y, left_width, left_height);


        /**
         * Initialize delete icon
         */

        // 1. Get the resource ID
        ic_deleteResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_delete, R.drawable.delete);
        // 2. Get icon resource based on resource ID (converted to Drawable object)
        ic_delete = getResources().getDrawable(ic_deleteResID);
        // 3. Set the icon size
        // starting point (x,y), width = left_width, height = left_height
        delete_x = typedArray.getInteger(R.styleable.SuperEditText_delete_x, 0);
        delete_y = typedArray.getInteger(R.styleable.SuperEditText_delete_y, 0);
        delete_width = typedArray.getInteger(R.styleable.SuperEditText_delete_width, 60);
        delete_height = typedArray.getInteger(R.styleable.SuperEditText_delete_height, 60);
        ic_delete.setBounds(delete_x, delete_y, delete_width, delete_height);

        /**
         * Set the left & right image of the EditText (the initial state is only the left image)
         */
        setCompoundDrawables(ic_left_unclick, null,
                null, null);

        // setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom)
        // role: in EditText, under, left, right set icon (equivalent to android: drawableLeft = "" android: drawableRight = "")
        // Remarks: The Drawable object passed in must already have setBounds (x, y, width, height), ie the initial position, width and height must be set
        // x: the starting point of the component on the X axis of the container y: the starting point of the component on the Y axis of the container width: the length of the component height: the height of the component
        // Set to null if you do not want to show it somewhere

        // Another similar method: setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)
        // Role: EditText, under, left, right set icon
        // The difference with setCompoundDrawables: setCompoundDrawablesWithIntrinsicBounds() Drawable width and height = intrinsic width height (automatically obtained by getIntrinsicWidth() & getIntrinsicHeight())
        // do not need to setBounds (x, y, width, height)

        /**
         * Initialize the cursor (color & thickness)
         */
        // Principle: Dynamically set the cursor through the reflection mechanism
        // 1. Get the resource ID
        cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor);
        try {

            // 2. Get cursor properties by reflection
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            // 3. Incoming resource ID
            f.set(this, cursor);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Initialization of the dividing line (color, thickness, location)
         */
        // 1. Set brush
        mPaint = new Paint();
        mPaint.setStrokeWidth(2.0f); // Split line thickness

        // 2. Set the splitter color (use hexadecimal codes such as #333, #8e8e8e)
        int lineColorClick_default = context.getResources().getColor(R.color.colorPrimary); // default = blue #1296db
        int lineColorunClick_default = context.getResources().getColor(R.color.colorPrimary); // Default = Gray # 9b9b9b
        lineColor_click = typedArray.getColor(R.styleable.SuperEditText_lineColor_click, lineColorClick_default);
        lineColor_unclick = typedArray.getColor(R.styleable.SuperEditText_lineColor_unclick, lineColorunClick_default);
        color = lineColor_unclick;

        mPaint.setColor(lineColor_unclick); // Splitter default color = gray
        setTextColor(color); // font default color = gray

        // 3. Split line position
        linePosition = typedArray.getInteger(R.styleable.SuperEditText_linePosition, 1);
        // Eliminated underlined
        setBackground(null);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDeleteIconVisible(hasFocus() && text.length() > 0, hasFocus());
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisible(focused && length() > 0, focused);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
// Principle: When the position where the finger is lifted is in the area where the icon is deleted,
// it is deemed that the delete icon is clicked = the content of the search box is cleared
        switch (event.getAction()) {
// Judgment action = when the finger is raised
            case MotionEvent.ACTION_UP:
                Drawable drawable = ic_delete;

                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {

                    // determine the condition
                    // event.getX() : coordinates of the position when lifted
                    // getWidth(): the width of the control
                    // getPaddingRight(): removes the distance from the right edge of the icon icon to the right edge of the EditText control
                    // ie: getWidth() - getPaddingRight() = remove icon's right edge coordinate = X1
                    //  getWidth () - getPaddingRight () - drawable.getBounds (). width () = Delete the coordinates of the left edge of the icon = X2
                    // so the area between X1 and X2 = the area to delete the icon
                    // When the position where the finger is lifted is in the area where the icon is deleted (X2 = <event.getX () <= X1), it is regarded as clicking the delete icon = Empty the search box                    setText("");
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setDeleteIconVisible(boolean deleteVisible, boolean leftVisible) {
        setCompoundDrawables(leftVisible ? ic_left_click : ic_left_unclick, null,
                deleteVisible ? ic_delete : null, null);
        color = leftVisible ? lineColor_click : lineColor_unclick;
        setTextColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        setTextColor(color);
        // Draw the split line
        // need to consider: When the input length exceeds the input box, the drawn line needs to follow the extension
        // Solution: length of line = control length + extended length
        int x = this.getScrollX(); // Get the extended length
        int w = this.getMeasuredWidth(); // Get the length of the control

        // The length of the line when the parameter is passed in = the length of the control + the length of the extension
        canvas.drawLine(0, this.getMeasuredHeight() - linePosition, w + x,
                this.getMeasuredHeight() - linePosition, mPaint);

    }

}