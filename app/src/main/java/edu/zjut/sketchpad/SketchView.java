package edu.zjut.sketchpad;


import android.content.Context;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SketchView extends View
{
    Paint paint = null;
    Bitmap originalBitmap;
    Bitmap new1_Bitmap;
    Bitmap new2_Bitmap = null;
    float startX = 0,startY = 0;
    float clickX = 0,clickY = 0;
    boolean isMove = true;
    boolean isClear = false;
    int color=Color.rgb(28, 109, 208);
    float strokeWidth=10.0f;

    public SketchView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        originalBitmap = Bitmap.createBitmap(
                DensityUtil.getScreenWidth(getContext()),
                DensityUtil.getScreenHeight(getContext()),
                Bitmap.Config.ARGB_8888);
        new1_Bitmap = Bitmap.createBitmap(originalBitmap);
    }

    public void clear(){
        isClear = true;
        new2_Bitmap = Bitmap.createBitmap(originalBitmap);
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void eraser(){
        color=Color.WHITE;
        strokeWidth=80.0f;
    }

    public void changeColor(int color){
        this.color=color;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(SketchView(new1_Bitmap), 0, 0,null);
    }

    public Bitmap SketchView(Bitmap o_Bitmap)
    {
        Canvas canvas = null;
        if(isClear) {
            canvas = new Canvas(new2_Bitmap);
        }
        else{
            canvas = new Canvas(o_Bitmap);
        }
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        if(isMove)
        {
            canvas.drawLine(startX, startY, clickX, clickY, paint);
        }
        startX = clickX;
        startY = clickY;
        if(isClear)
        {
            return new2_Bitmap;
        }
        return o_Bitmap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        clickX = event.getX();
        clickY = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            isMove = false;
            invalidate();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            isMove = true;
            strokeWidth=Math.min(event.getSize()*200,17);
            invalidate();
            return true;
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            performClick();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick(){
        return super.performClick();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }
}



