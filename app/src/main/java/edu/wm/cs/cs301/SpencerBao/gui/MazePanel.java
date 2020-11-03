package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Responsibilities: MazePanel acts as a notetaker for its client classes. When the StatePlaying
 * object thinks its scene is ready, it calls the MazePanel to commit and update the screen.
 * <p></p>
 */
public class MazePanel extends View {
    private Rect rect;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;

    public MazePanel(Context context) {
        super(context);
    }

    public MazePanel(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs);
    }

    public MazePanel(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs, defStyleAttr);
        init(attrs);
    }

    public MazePanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context,attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        rect = new Rect(200, 200, 500, 500);
    }

    @Override
    public void onMeasure(int width, int height) {

        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) { // canvas class defines methods for drawing graphics
        // Canvas handles what to draw, Paint handles how to draw


        canvas.drawRect(rect, paint);
    }
}