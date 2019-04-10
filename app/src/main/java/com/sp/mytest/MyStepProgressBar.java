package com.sp.mytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * @author zgq
 * @version 1.0
 * @describe -- 自定义步骤式进度条
 * @date 2019/4/10
 */
public class MyStepProgressBar extends View {

    private Paint bgPaint;
    private Paint forePaint;
    private Paint fontPaint;
    private List<MyStepProgressBarBean> datas;
    private int lineH;
    private int radius;
    private int marginTop;
    private int itemWidth;
    private int selIndex;

    public MyStepProgressBar(Context context) {
        super(context);
        init();
    }

    public MyStepProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyStepProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lineH = 5;
        radius = 20;
        marginTop  = 15;
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.parseColor("#DDDDDD"));
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setStrokeWidth(lineH);

        forePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        forePaint.setColor(Color.parseColor("#00C569"));
        forePaint.setStyle(Paint.Style.FILL);
        forePaint.setStrokeWidth(lineH);

        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setColor(Color.parseColor("#000000"));
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(30);
        fontPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void setDatas(List<MyStepProgressBarBean> datas) {
        this.datas = datas;
    }

    public void setSelIndex(int selIndex) {
        this.selIndex = selIndex;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (datas != null && datas.size() > 0) {
            itemWidth = (getWidth() - radius * 2) / (datas.size()-1);
            canvas.drawLine(radius, radius, getWidth(), radius, bgPaint);
            for (int i = 0; i < datas.size(); i++) {
                if (i <= selIndex){
                    canvas.drawLine(radius, radius, i * itemWidth, radius, forePaint);
                    canvas.drawCircle(radius + itemWidth * i , radius, radius, forePaint);
                }else {
                    canvas.drawCircle(radius + itemWidth * i , radius, radius, bgPaint);
                }

                //绘制文字
                //时间
                Rect rect = new Rect();
                fontPaint.getTextBounds(datas.get(i).getTiem(), 0, datas.get(i).getTiem().length(), rect);
                int timeWidth = rect.width();
                int timeHeight = rect.height();
                canvas.drawText(datas.get(i).getTiem(), (radius + itemWidth * i) - timeWidth/2
                        , radius *2 + timeHeight+marginTop, fontPaint);

                //内容
                fontPaint.getTextBounds(datas.get(i).getContent(), 0, datas.get(i).getContent().length(), rect);
                int textWidth = rect.width();
                int textHeight = rect.height();
                canvas.drawText(datas.get(i).getContent(), (radius + itemWidth * i) - textWidth/2
                        , radius *2 + (timeHeight+textHeight)+(marginTop*2), fontPaint);
            }
        }
    }



}
