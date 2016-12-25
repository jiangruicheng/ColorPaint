package com.cndll.colorpaint;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kongqing on 16-12-24.
 */
public class ColorPaintView extends View {
    public ColorPaintView(Context context) {
        super(context);
    }

    public ColorPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int[] colors = new int[]{Color.rgb(0xdd, 0x48, 0xff), Color.rgb(0xf3, 0xcc, 0x8d), Color.rgb(0xff, 0x91, 0x6c)};
    private int[] colors_red = new int[]{Color.rgb(0x4d, 0xeb, 0xb2), Color.rgb(0xf5, 0x88, 0xd5)};

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient linearGradient = new LinearGradient(0, 0, getWidth(), 0, colors_red, null, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(getWidth() / 80);
        drawScale(canvas, paint, 64);
        //drawGuide(canvas, paint, 45);
        drawGuide(canvas, paint, 45+90);
        drawGuide(canvas, paint, 45 + 90 * 2);
        //drawGuide(canvas, paint, 45 + 90 * 3);
    }

    private void drawScale(Canvas canvas, Paint paint, int scalenub) {
        for (int i = 0; i < scalenub + 1; i++) {
            drawLine(canvas,
                    getWidth() / 2,
                    getHeight() / 2,
                    getWidth() / 2,
                    getWidth() / 25,
                    45 + ((float) 270 / (float) scalenub) * i,
                    paint);
        }
    }

    private void drawGuide(Canvas canvas, Paint paint, float rate) {
        Path path = new Path();
        path.moveTo(getLineX(getWidth() / 2,
                getWidth() / 2 - getWidth() / 8, rate),
                getLineY(getHeight() / 2,
                        getWidth() / 2 - getWidth() / 8,
                        rate));
        path.lineTo(getLineX(getWidth() / 2, getWidth() / 60, rate + 90),
                getLineY(getHeight() / 2,
                        getWidth() / 60,
                        rate + 90));
        path.lineTo(getLineX(getWidth() / 2,
                getWidth() / 18, rate + 180),
                getLineY(getHeight() / 2,
                        getWidth() / 18,
                        rate + 180));
        path.lineTo(getLineX(getWidth() / 2,
                getWidth() / 60, rate + 90 + 180),
                getLineY(getHeight() / 2,
                        getWidth() / 60,
                        rate + 90 + 180));
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLine(Canvas canvas, int x, int y, int R, int length, float rate, Paint p) {
        canvas.drawLine(getLineX(x, R, rate),
                getLineY(y, R, rate),
                getLineX(x, R - length, rate),
                getLineY(y, R - length, rate),
                p);
    }

    private float getLineX(int x, int R, float rate) {
        float x1 = 0;
        if (rate >= 0 && rate <= 180) {
            if (rate <= 90) {
                double a = Math.sin((rate) * Math.PI / 180);
                Log.d("sin", "" + a);
                x1 = x - (float) (R * Math.sin((rate) * Math.PI / 180));
            } else {
                x1 = x - (float) (R * Math.cos((rate - 90) * Math.PI / 180));
            }
        } else {
            if (rate <= 270) {
                x1 = x + (float) (R * Math.sin((rate - 180) * Math.PI / 180));
            } else {
                x1 = x + (float) (R * Math.cos((rate - 270) * Math.PI / 180));
            }
        }
        Log.d("x", x1 + "");
        return x1;
    }

    private float getLineY(int x, int R, float rate) {
        float x1 = 0;
        if (rate >= 90 && rate <= 270) {
            if (rate >= 90 && rate < 180) {
                x1 = x - (float) (R * Math.sin((rate - 90) * Math.PI / 180));
            } else {
                x1 = x - (float) (R * Math.cos((rate - 180) * Math.PI / 180));
            }
        } else {
            if (rate < 90) {
                x1 = x + (float) (R * Math.cos((rate) * Math.PI / 180));
            } else {
                x1 = x + (float) (R * Math.sin((rate - 270) * Math.PI / 180));
            }
        }
        Log.d("y", x1 + "");
        return x1;
    }


}

