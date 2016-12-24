package com.cndll.colorpaint;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient linearGradient = new LinearGradient(0, 0, getWidth(), 0, colors, null, Shader.TileMode.MIRROR);
        Paint          paint          = new Paint();
        paint.setShader(linearGradient);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(getWidth() / 90);
        drawLine(canvas,
                getWidth() / 2,
                getHeight() / 2,
                getWidth() / 2,
                getWidth() / 10,
                1 ,
                paint);
        //canvas.translate(getWidth() / 2, getHeight() / 2);
       /*for (int i = 0; i < 65; i++) {
            drawLine(canvas,
                    getWidth() / 2,
                    getHeight() / 2,
                    getWidth() / 2,
                    getWidth() / 40,
                    45 + ((float) 270 / (float) 64) * i,
                    paint);
        }
*/

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
                x1 = x - (float) (R * Math.sin((rate) * 180 / Math.PI));
            } else {
                x1 = x - (float) (R * Math.cos((rate - 90) * 180 / Math.PI));
            }
        } else {
            if (rate <= 270) {
                x1 = x + (float) (R * Math.sin((rate - 180) * 180 / Math.PI));
            } else {
                x1 = x + (float) (R * Math.cos((rate - 270) * 180 / Math.PI));
            }
        }
        return x1;
    }

    private float getLineY(int x, int R, float rate) {
        float x1 = 0;
        if (rate >= 90 && rate <= 270) {
            if (rate >= 90 && rate < 180) {
                x1 = x - (float) (R * Math.sin((rate - 90) * 180 / Math.PI));
            } else {
                x1 = x - (float) (R * Math.cos((rate - 180) * 180 / Math.PI));
            }
        } else {
            if (rate < 90) {
                x1 = x +(float) (R * Math.cos((rate) /** 180 / Math.PI*/));
            }else {
                x1 = x + (float) (R * Math.sin((rate-270) /** 180 / Math.PI*/));
            }
        }
        return x1;
    }


}

