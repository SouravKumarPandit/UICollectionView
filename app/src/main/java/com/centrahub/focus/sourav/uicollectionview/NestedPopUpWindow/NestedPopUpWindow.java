package com.centrahub.focus.sourav.uicollectionview.NestedPopUpWindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.centrahub.focus.sourav.uicollectionview.poupsActivity.MoreOptionDTO;

public class NestedPopUpWindow implements PopupWindow.OnDismissListener, View.OnClickListener {
    private static final int mDefaultPopupWindowStyleRes = android.R.attr.popupWindowStyle;

    private boolean dismissed = false;

    private boolean mDismissOnInsideTouch   = true;
    private boolean mDismissOnOutsideTouch  = true;

    private View mContentLayout;
    private ViewGroup mRootView;;
    private Context mContext;
    private PopupWindow mPopupWindow;
    private boolean mFocusable;
    private static  int TOTAL_INSTANCE;
    private int mArrowDirection;
    private boolean mShowArrow=true;
    private ImageView mArrowView;
    private int arrowDirection=ArrowDrawable.AUTO;
    private ArrowDrawable arrowDrawable;
    private int gravity=Gravity.START;
    private float arrowHeight;
    private float arrowWidth;
    private int mDefaultArrowWidthRes=15;
    private int mDefaultArrowHeightRes=15;
    private int mGravity;
    //todo
    private float mMargin=10;
    private View mAnchorView;


    public NestedPopUpWindow(Context mContext,View  mAnchorView)
    {
        this.mContext=mContext;
        this.mAnchorView=mAnchorView;
        mRootView = PopUpWindowUtils.findFrameLayout(mAnchorView);

        init();

        mRootView = PopUpWindowUtils.findFrameLayout(this.mAnchorView);
    }

    private void init() {
        configPopupWindow();
        configContentView(new String[]{"Text One : ", "Text Two : ", "TEXT Three : ", "Text Four : ", "TEXT Five : "});
    }

    private void configContentView(String[]  textString)
    {
/*
        if (mContentView instanceof TextView) {
            TextView tv = (TextView) mContentView;
            tv.setText(mText);
        } else {
            TextView tv = (TextView) mContentView.findViewById(mTextViewId);
            if (tv != null)
                tv.setText(mText);
        }

        mContentView.setPadding((int) mPadding, (int) mPadding, (int) mPadding, (int) mPadding);*/

        PopUpWindowLayout linearLayout = getPopUpLayout(textString);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        linearLayout.setOrientation(mArrowDirection == ArrowDrawable.LEFT || mArrowDirection == ArrowDrawable.RIGHT ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
//        int layoutPadding = (int) (mAnimated ? mAnimationPadding : 0);
//        linearLayout.setPadding(layoutPadding, layoutPadding, layoutPadding, layoutPadding);

        if (mShowArrow) {
            mArrowView = new ImageView(mContext);

                if (arrowDirection == ArrowDrawable.AUTO)
                    arrowDirection = PopUpWindowUtils.tooltipGravityToArrowDirection(gravity);
                if (arrowDrawable == null)
                    arrowDrawable = new ArrowDrawable(Color.GREEN, arrowDirection);
                if (arrowWidth == 0)
                    arrowWidth = 15;
                if (arrowHeight == 0)
                    arrowHeight =15;

            mArrowView.setImageDrawable(arrowDrawable);
            LinearLayout.LayoutParams arrowLayoutParams;

            if (mArrowDirection == ArrowDrawable.TOP || mArrowDirection == ArrowDrawable.BOTTOM) {
                arrowLayoutParams = new LinearLayout.LayoutParams((int) arrowWidth, (int) arrowHeight, 1);
            } else {
                arrowLayoutParams = new LinearLayout.LayoutParams((int) arrowHeight, (int) arrowWidth, 1);
            }

            arrowLayoutParams.gravity = Gravity.CENTER;
            mArrowView.setLayoutParams(arrowLayoutParams);

            if (mArrowDirection == ArrowDrawable.BOTTOM || mArrowDirection == ArrowDrawable.RIGHT) {
//                linearLayout.addView(mContentView);
                linearLayout.addView(mArrowView);
            } else {
                linearLayout.addView(mArrowView);
//                linearLayout.addView(mContentView);
            }
        } else {
//            linearLayout.addView(mContentView);
        }


        mContentLayout = linearLayout;
        mContentLayout.setVisibility(View.INVISIBLE);

        mPopupWindow.setContentView(mContentLayout);

    }

    public PopUpWindowLayout getPopUpLayout(String[] sArrayListItem)
    {
        PopUpWindowLayout popUpItemsList = new PopUpWindowLayout(mContext);
        popUpItemsList.setBgPaintColor(TOTAL_INSTANCE % 5);
        popUpItemsList.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; sArrayListItem.length > i; i++) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            ViewGroup.LayoutParams linearLayoutPara = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            linearLayout.setLayoutParams(linearLayoutPara);

            TextView textLable = new TextView(mContext);
            textLable.setPadding((int) PopUpWindowUtils.pxFromDp(2), (int) PopUpWindowUtils.pxFromDp(2), (int) PopUpWindowUtils.pxFromDp(2), (int) PopUpWindowUtils.pxFromDp(2));
//            textLable.setTextColor(R.drawable.text_state_drawable);
            if (i == 3 || i == 0/*todo if has option then do some thing dirty*/) {
//                textLable.setText("▶ "+sArrayListItem[i]);
                textLable.setText(sArrayListItem[i] + " ▶");
                MoreOptionDTO moreOptionDTO = new MoreOptionDTO();
                moreOptionDTO.setHasOption(true);
                moreOptionDTO.setsLable(sArrayListItem[i] + " ▶");
                moreOptionDTO.setsOptionArray(new String[]{"Text One : "+i, "Text Two : "+i, "TEXT Three : "+i, "Text Four : "+i, "TEXT Five : "+i});
                linearLayout.setTag(moreOptionDTO);

            } else {
                textLable.setText(sArrayListItem[i]);
                MoreOptionDTO moreOptionDTO = new MoreOptionDTO();
                moreOptionDTO.setHasOption(false);
                moreOptionDTO.setsLable(sArrayListItem[i]);
                moreOptionDTO.setsOptionArray(null);
                linearLayout.setTag(moreOptionDTO);

            }

            textLable.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            textLable.setTypeface(Typeface.DEFAULT_BOLD);
            LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            textLable.setLayoutParams(textParam);
            linearLayout.addView(textLable);

            popUpItemsList.addView(linearLayout);

            if (sArrayListItem.length != i) {
                View divider = new View(mContext);
                LinearLayout.LayoutParams dividerParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                dividerParam.setMargins((int) PopUpWindowUtils.pxFromDp(6), 0, (int)PopUpWindowUtils. pxFromDp(6), (int) PopUpWindowUtils.pxFromDp(2));
                divider.setLayoutParams(dividerParam);
                divider.setBackgroundColor(Color.LTGRAY);
//                popUpItemsList.addView(divider);
            }


            linearLayout.setOnClickListener(this);
        }


        return popUpItemsList;
    }

    private void configPopupWindow() {
        mPopupWindow = new PopupWindow(mContext, null, mDefaultPopupWindowStyleRes);
        mPopupWindow.setOnDismissListener(this);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);;
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getX();
                final int y = (int) event.getY();

                if (!mDismissOnOutsideTouch && (event.getAction() == MotionEvent.ACTION_DOWN)
                        && ((x < 0) || (x >= mContentLayout.getMeasuredWidth()) || (y < 0) || (y >= mContentLayout.getMeasuredHeight()))) {
                    return true;
                } else if (!mDismissOnOutsideTouch && event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                } else if ((event.getAction() == MotionEvent.ACTION_DOWN) && mDismissOnInsideTouch) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        mPopupWindow.setClippingEnabled(false);
        mPopupWindow.setFocusable(mFocusable);
    }


    public void show() {
        verifyDismissed();

        mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(mLocationLayoutListener);
        mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(mAutoDismissLayoutListener);
        mPopupWindow.showAtLocation(mRootView, Gravity.NO_GRAVITY, mRootView.getWidth(), mRootView.getHeight());

        mRootView.post(new Runnable() {
            @Override
            public void run() {
                if (mRootView.isShown())
                    mPopupWindow.showAtLocation(mRootView, Gravity.NO_GRAVITY, mRootView.getWidth(), mRootView.getHeight());
                else
                    Log.e("ZSA", "Tooltip cannot be shown, root view is invalid or has been closed.");
            }
        });
    }

    private void verifyDismissed() {
        if (dismissed) {
            throw new IllegalArgumentException("Tooltip has ben dismissed.");
        }
    }



    private final View.OnTouchListener mOverlayTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };

    private final ViewTreeObserver.OnGlobalLayoutListener mAutoDismissLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final PopupWindow popup = mPopupWindow;
            if (popup == null || dismissed) return;

            if (!mRootView.isShown()) dismiss();
        }
    };

    public interface OnDismissListener {
        void onDismiss(NestedPopUpWindow tooltip);
    }

    public interface OnShowListener {
        void onShow(NestedPopUpWindow tooltip);
    }



    private final ViewTreeObserver.OnGlobalLayoutListener mLocationLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final PopupWindow popup = mPopupWindow;
            if (popup == null || dismissed) return;
/*
            if (mMaxWidth > 0 && mContentView.getWidth() > mMaxWidth) {
                PopUpWindowUtils.setWidth(mContentLayout, mMaxWidth);*/
                popup.update(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                return;
//            }

            PopUpWindowUtils.removeOnGlobalLayoutListener(popup.getContentView(), this);
            popup.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(mArrowLayoutListener);
            PointF location = calculePopupLocation();
            popup.setClippingEnabled(true);
            popup.update((int) location.x, (int) location.y, popup.getWidth(), popup.getHeight());
            popup.getContentView().requestLayout();
        }
    };

    private PointF calculePopupLocation() {
        PointF location = new PointF();

        final RectF anchorRect = PopUpWindowUtils.calculeRectInWindow(mAnchorView);
        final PointF anchorCenter = new PointF(anchorRect.centerX(), anchorRect.centerY());

        switch (gravity) {
            case Gravity.START:
                location.x = anchorRect.left - mPopupWindow.getContentView().getWidth() - mMargin;
                location.y = anchorCenter.y - mPopupWindow.getContentView().getHeight() / 2f;
                break;
            case Gravity.END:
                location.x = anchorRect.right + mMargin;
                location.y = anchorCenter.y - mPopupWindow.getContentView().getHeight() / 2f;
                break;
            case Gravity.TOP:
                location.x = anchorCenter.x - mPopupWindow.getContentView().getWidth() / 2f;
                location.y = anchorRect.top - mPopupWindow.getContentView().getHeight() - mMargin;
                break;
            case Gravity.BOTTOM:
                location.x = anchorCenter.x - mPopupWindow.getContentView().getWidth() / 2f;
                location.y = anchorRect.bottom + mMargin;
                break;
            case Gravity.CENTER:
                location.x = anchorCenter.x - mPopupWindow.getContentView().getWidth() / 2f;
                location.y = anchorCenter.y - mPopupWindow.getContentView().getHeight() / 2f;
                break;
            default:
                throw new IllegalArgumentException("Gravity must have be CENTER, START, END, TOP or BOTTOM.");
        }

        return location;
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mArrowLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final PopupWindow popup = mPopupWindow;
            if (popup == null || dismissed) return;

            PopUpWindowUtils.removeOnGlobalLayoutListener(popup.getContentView(), this);

            popup.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(mAnimationLayoutListener);
            popup.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(mShowLayoutListener);
            if (mShowArrow) {
                RectF achorRect = PopUpWindowUtils.calculeRectOnScreen(mAnchorView);
                RectF contentViewRect = PopUpWindowUtils.calculeRectOnScreen(mContentLayout);
                float x, y;
                if (mArrowDirection == ArrowDrawable.TOP || mArrowDirection == ArrowDrawable.BOTTOM) {
                    x = mContentLayout.getPaddingLeft() + PopUpWindowUtils.pxFromDp(2);
                    float centerX = (contentViewRect.width() / 2f) - (mArrowView.getWidth() / 2f);
                    float newX = centerX - (contentViewRect.centerX() - achorRect.centerX());
                    if (newX > x) {
                        if (newX + mArrowView.getWidth() + x > contentViewRect.width()) {
                            x = contentViewRect.width() - mArrowView.getWidth() - x;
                        } else {
                            x = newX;
                        }
                    }
                    y = mArrowView.getTop();
                    y = y + (mArrowDirection == ArrowDrawable.BOTTOM ? -1 : +1);
                } else {
                    y = mContentLayout.getPaddingTop() + PopUpWindowUtils.pxFromDp(2);
                    float centerY = (contentViewRect.height() / 2f) - (mArrowView.getHeight() / 2f);
                    float newY = centerY - (contentViewRect.centerY() - achorRect.centerY());
                    if (newY > y) {
                        if (newY + mArrowView.getHeight() + y > contentViewRect.height()) {
                            y = contentViewRect.height() - mArrowView.getHeight() - y;
                        } else {
                            y = newY;
                        }
                    }
                    x = mArrowView.getLeft();
                    x = x + (mArrowDirection == ArrowDrawable.RIGHT ? -1 : +1);
                }
                PopUpWindowUtils.setX(mArrowView, (int) x);
                PopUpWindowUtils.setY(mArrowView, (int) y);
            }
            popup.getContentView().requestLayout();
        }
    };

    private OnShowListener mOnShowListener;
    private final ViewTreeObserver.OnGlobalLayoutListener mShowLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final PopupWindow popup = mPopupWindow;
            if (popup == null || dismissed) return;

            PopUpWindowUtils.removeOnGlobalLayoutListener(popup.getContentView(), this);

            if (mOnShowListener != null)
                mOnShowListener.onShow(NestedPopUpWindow.this);
            mOnShowListener = null;

            mContentLayout.setVisibility(View.VISIBLE);
        }
    };

    private final ViewTreeObserver.OnGlobalLayoutListener mAnimationLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final PopupWindow popup = mPopupWindow;
            if (popup == null || dismissed) return;

            PopUpWindowUtils.removeOnGlobalLayoutListener(popup.getContentView(), this);


            popup.getContentView().requestLayout();
        }
    };



    public void dismiss() {
        if (dismissed)
            return;

        dismissed = true;
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
    @Override
    public void onDismiss() {
        dismissed = true;
        mPopupWindow.dismiss();
       mPopupWindow = null;
    }

    @Override
    public void onClick(View view)
    {
            mAnchorView=view;
        LinearLayout lableLayout = (LinearLayout) view;
        Object tagObj = lableLayout.getTag();
        MoreOptionDTO moreOpt = null;
        if (tagObj != null)
            moreOpt = (MoreOptionDTO) tagObj;
        if (moreOpt != null) {
            if (moreOpt.isHasOption()) {
                new NestedPopUpWindow(mContext,view);
//                valueArrayList.add(moreOpt.getsLable());

            } else {
                Toast.makeText(mContext, "" + moreOpt.getsLable(), Toast.LENGTH_SHORT).show();
            }


        }
    }
}
