package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.fonts.Font;
import android.media.Image;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import java.lang.invoke.ConstantCallSite;

import edu.wm.cs.cs301.SpencerBao.gui.Constants;

/**
 * Responsibilities: MazePanel acts as a notetaker for its client classes. When the StatePlaying
 * object thinks its scene is ready, it calls the MazePanel to commit and update the screen.
 * <p></p>
 * Classes: PlayManuallyActivity, PlayAnimationActivity
 * <p></p>
 * @Author Spencer Bao
 */
public class MazePanel extends View {
    private Rect rect;
    private Paint paint;  //Paint handles how to draw
    private Canvas canvas;// Canvas handles what to draw, canvas class defines methods for drawing graphics
    private Bitmap bitmap;
    private int width = Constants.VIEW_WIDTH;
    private int height = Constants.VIEW_HEIGHT;

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
        bitmap = bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        myTestImage(canvas);
    }

    @Override
    public void onDraw(Canvas canvas) { //different from the private canvas

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    public void update(Canvas canvas){
        paint(canvas);
    }

    public void update(){
        paint(canvas);
    }

    public boolean isOperational() {
        if (bitmap != null) {
            return true;
        } else {
            return false;
        }
    }


    public void paint(Canvas canvas){
        canvas.drawBitmap(bitmap, 0,0, paint);
    }

    public void setColor(int rgb) {
        paint.setColor(rgb);
    }

    public static int decodeRGB(String hex) {
        return Color.parseColor(hex);
    }

    public static int convertRGB(float r, float g, float b, float a) {
        return Color.valueOf(r, g, b, a).toArgb();

    }

    private static int calculateRGBValue(final int distance, int extensionX) {
        // compute rgb value, depends on distance and x direction
        // 7 in binary is 0...0111
        // use AND to get last 3 digits of distance
        final int part1 = distance & 7;
        final int add = (extensionX != 0) ? 1 : 0;
        final int rgbValue = ((part1 + 2 + add) * 70) / 8 + 80;
        return rgbValue;
    }


    public static int getWallColorStatic(int distance, int cc, int extensionX) {
        final int RGB_DEF = 20;
        final int RGB_DEF_GREEN = 60;
        final int d = distance / 4;

        // calculateRGBValue(distance) from Wall
        int rgbValue = calculateRGBValue(distance, extensionX);

        // mod used to limit the number of colors to 6
        int rgb;
        switch (((d >> 3) ^ cc) % 6) {
            case 0:
                rgb = Color.rgb(rgbValue, RGB_DEF, RGB_DEF);
                break;
            case 1:
                rgb = Color.rgb(RGB_DEF, RGB_DEF_GREEN, RGB_DEF);
                break;
            case 2:
                rgb = Color.rgb(RGB_DEF, RGB_DEF, rgbValue);
                break;
            case 3:
                rgb = Color.rgb(rgbValue, RGB_DEF_GREEN, RGB_DEF);
                break;
            case 4:
                rgb = Color.rgb(RGB_DEF, RGB_DEF_GREEN, rgbValue);
                break;
            case 5:
                rgb = Color.rgb(rgbValue, RGB_DEF, rgbValue);
                break;
            default:
                rgb = Color.rgb(RGB_DEF, RGB_DEF, RGB_DEF);
                break;
        }
        return rgb;
    }

    public int getWallColor(int distance, int cc, int extensionX) {
        return getWallColorStatic(distance, cc, extensionX);
    }

    private Color blend(Color c0, Color c1, double weight0) {
        if (weight0 < 0.1)
            return c1;
        if (weight0 > 0.95)
            return c0;
        double r = weight0 * c0.red() + (1-weight0) * c1.red();
        double g = weight0 * c0.green() + (1-weight0) * c1.green();
        double b = weight0 * c0.blue() + (1-weight0) * c1.blue();
        double a = Math.max(c0.alpha(), c1.alpha());

        return Color.valueOf((int) r, (int) g, (int) b, (int) a);
    }

    private Color getBackgroundColor(float percentToExit, boolean top) {
        final Color greenWM = Color.valueOf(17, 87, 64);
        final Color goldWM = Color.valueOf(145, 111, 65);
        final Color yellowWM = Color.valueOf(255, 255, 153);
        return top? blend(yellowWM, goldWM, percentToExit) :
                blend(Color.valueOf(Color.LTGRAY), greenWM, percentToExit);
    }

    public void addBackground(float percentToExit) {
        final int viewWidth  = 400;
        final int viewHeight = 400;
        // black rectangle in upper half of screen
//        paint.setColor(Color.BLACK);

        // dynamic color setting:
        setColor(getBackgroundColor(percentToExit, true).toArgb());
//		graphics.fillRect(0, 0, viewWidth, viewHeight/2);
        addFilledRectangle(0, 0, viewWidth, viewHeight/2, paint);
        // grey rectangle in lower half of screen
        // graphics.setColor(Color.darkGray);
        // dynamic color setting:
        setColor(getBackgroundColor(percentToExit, false).toArgb());
//		graphics.fillRect(0, viewHeight/2, viewWidth, viewHeight/2);
        addFilledRectangle(0, viewHeight/2, viewWidth, viewHeight/2, paint);

    }

    public void addFilledRectangle(int x, int y, int width, int height, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+width, y+height, paint);
    }

    public void addFilledPolygon(int[] xPoints, int[] yPoints, int nPoints, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        addPolygon(xPoints, yPoints, nPoints, paint);
    }

    public void addPolygon(int[] xPoints, int[] yPoints, int nPoints, Paint paint) {
        Path path = new Path();
        path.reset();
        path.moveTo(xPoints[0], yPoints[0]);
        for(int i = 1; i < nPoints; i++){
            path.lineTo(xPoints[i], yPoints[i]);
        }
        path.lineTo(xPoints[0], yPoints[0]);

        canvas.drawPath(path, paint);
    }

    public void addLine(int startX, int startY, int endX, int endY, Paint paint) {
        canvas.drawLine(startX, startY, endX, endY, paint);
    }


    public void addFilledOval(int x, int y, int width, int height, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(x, y, width, height, paint);
    }

    public void addArc(int x, int y, int width, int height, int startAngle, int arcAngle, Paint paint) {
        canvas.drawArc(x, y, width, height, startAngle, arcAngle, false, paint);
    }

    public void addMarker(float x, float y, String str, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        canvas.drawText(str, x, y, paint);
    }

    public void myTestImage(Canvas c){

        setColor(Color.RED);
        addFilledOval(100, 100, 200, 200, paint);
        setColor(Color.BLACK);
        addLine(10, 10, 400, 400, paint);
        setColor(Color.BLUE);
        addMarker(100, 100, "yo", paint);
        int[] xPoints = {400, 600, 1000};
        int[] yPoints = {600, 1000, 2000};
        addPolygon(xPoints, yPoints, 3, paint);
        update(c);
    }
}