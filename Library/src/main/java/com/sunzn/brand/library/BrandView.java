package com.sunzn.brand.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class BrandView extends View {

    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_RECTANGLE = 2;
    public static final int TYPE_OVAL = 3;

    private static final boolean DEFAULT_CHECK_VALUE = false;
    private static final int DEFAULT_FRAME_COLOR = 0XFF000000;
    private static final int DEFAULT_FRAME_WIDTH = 4;
    private static final int DEFAULT_ROUND_VALUE = 20;
    private static final int DEFAULT_BLOCK_COLOR = 0XFFFFFF00;

    private Paint blockPaint;

    private Paint framePaint;

    private RectF blockBorder;

    private RectF frameBorder;

    private int round;

    private int radius;

    private float frameWidth;

    private float frameBreak;

    private int type;

    private Bitmap blockBitmap;

    private PorterDuffXfermode xfermode;

    private int frameColor;

    private int blockColor;

    private int roundValue;

    private boolean checkValue;

    public BrandView(Context context) {
        this(context, null);
    }

    public BrandView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrandView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BrandView, defStyleAttr, 0);
        type = a.getInteger(R.styleable.BrandView_brand_type, TYPE_RECTANGLE);
        checkValue = a.getBoolean(R.styleable.BrandView_brand_check_value, DEFAULT_CHECK_VALUE);
        frameColor = a.getInteger(R.styleable.BrandView_brand_frame_color, DEFAULT_FRAME_COLOR);
        frameWidth = a.getInteger(R.styleable.BrandView_brand_frame_width, DEFAULT_FRAME_WIDTH);
        roundValue = a.getInteger(R.styleable.BrandView_brand_round_value, DEFAULT_ROUND_VALUE);

        Drawable drawable = a.getDrawable(R.styleable.BrandView_brand_block_value);
        if (drawable instanceof BitmapDrawable) {
            blockBitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof ColorDrawable) {
            blockColor = ((ColorDrawable) drawable).getColor();
        }

        a.recycle();

        init();
    }

    private void init() {
        blockPaint = new Paint();
        blockPaint.setAntiAlias(true);
        blockPaint.setColor(blockColor == 0 ? DEFAULT_BLOCK_COLOR : blockColor);
        round = dipToPixel(getContext(), roundValue);
        blockBorder = new RectF();
        frameBorder = new RectF();
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        framePaint = new Paint();
        framePaint.setAntiAlias(true);
        framePaint.setColor(frameColor);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setStrokeWidth(frameWidth);
        frameBreak = frameWidth / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        blockBorder.set(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        frameBorder.set(0.0f + frameBreak, 0.0f + frameBreak, (float) getMeasuredWidth() - frameBreak, (float) getMeasuredHeight() - frameBreak);
        radius = getMeasuredWidth() / 2;
        if (type == TYPE_OVAL) {
            round = Math.min(getMeasuredHeight() / 2, round);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (blockBitmap == null) {
            drawColor(canvas);
        } else {
            drawBitmap(canvas, blockBitmap, blockPaint);
        }
        if (checkValue) {
            drawFrame(canvas);
        }
    }

    public void setCheck(boolean check) {
        this.checkValue = check;
        invalidate();
        setSelected(check);
    }

    public void setImage(int image) {
        setBitmap(BitmapFactory.decodeResource(getResources(), image));
    }

    public void setBitmap(Bitmap bitmap) {
        this.blockBitmap = bitmap;
    }

    public void setColor(int color) {
        blockPaint.setColor(color);
    }

    private void drawColor(Canvas canvas) {
        switch (type) {
            case TYPE_CIRCLE:
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) radius, blockPaint);
                break;
            case TYPE_RECTANGLE:
            case TYPE_OVAL:
                canvas.drawRoundRect(blockBorder, (float) round, (float) round, blockPaint);
                break;
        }
    }

    public void drawBitmap(Canvas canvas, Bitmap bitmap, Paint paint) {
        int saveLayer = canvas.saveLayer(blockBorder.left, blockBorder.top, blockBorder.right, blockBorder.bottom, (Paint) null, Canvas.ALL_SAVE_FLAG);
        paint.setStyle(Paint.Style.FILL);
        switch (type) {
            case TYPE_CIRCLE:
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) radius, paint);
                break;
            case TYPE_RECTANGLE:
            case TYPE_OVAL:
                canvas.drawRoundRect(blockBorder, (float) round, (float) round, paint);
                break;
        }
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, (Rect) null, blockBorder, paint);
        paint.setXfermode((Xfermode) null);
        canvas.restoreToCount(saveLayer);
    }

    private void drawFrame(Canvas canvas) {
        switch (type) {
            case TYPE_CIRCLE:
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), radius - frameBreak, framePaint);
                break;
            case TYPE_RECTANGLE:
            case TYPE_OVAL:
                canvas.drawRoundRect(frameBorder, (float) round - frameWidth, (float) round - frameWidth, framePaint);
                break;
        }
    }

    public void setType(int type) {
        this.type = type;
        setSelected(checkValue);
    }

    public static int dipToPixel(Context context, int dp) {
        return (int) (TypedValue.applyDimension(1, (float) dp, context.getResources().getDisplayMetrics()) + 1.0f);
    }

}