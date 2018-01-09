package com.yx.news.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by XX on 2017/8/24.
 */

public class ColorChangeTextView extends TextView {
    private int mViewWidth;
    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    private int mStartColor;
    private int mSecondColor;
    private int mLastColor;
    private long mDelayTime = 200;

    public ColorChangeTextView(Context context) {
        super(context);
    }

    public ColorChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorChangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setColor(int starColor, int secondColor, int lastColor) {
        mStartColor = starColor;
        mSecondColor = secondColor;
        mLastColor = lastColor;
    }

    public void setDelayTime(long delayTime) {
        mDelayTime = delayTime;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[] {
//                                0XFFEEC900, 0XFFFFFF00, Color.GRAY
                                mStartColor, mSecondColor, mLastColor
                        }, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth/5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }

            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(mDelayTime);
        }
    }
}
