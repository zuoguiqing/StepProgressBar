package com.sp.mytest;

import android.content.Context;
import android.content.res.TypedArray;
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
public class StepProgressBar extends View {

    private Paint bgPaint;
    private Paint forePaint;
    private Paint fontPaint;
    private List<StepProgressBarBean> datas;
    private int lineH;
    private int radius;
    private int marginTop;
    private int itemWidth;
    private int selIndex;
    private int fontSize;
    private int fontColor;
    private int bgColor;
    private int foreColor;
    private int timeHeight;
    private int textHeight;

    public StepProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepProgressBar);
        lineH = a.getDimensionPixelSize(R.styleable.StepProgressBar_step_lineHeight, 5);
        radius = a.getDimensionPixelSize(R.styleable.StepProgressBar_step_radius, 24);
        marginTop = a.getDimensionPixelSize(R.styleable.StepProgressBar_step_marginTop, 15);
        fontSize = a.getDimensionPixelSize(R.styleable.StepProgressBar_step_textSize, 30);
        fontColor = a.getColor(R.styleable.StepProgressBar_step_textColor, Color.BLACK);
        bgColor = a.getColor(R.styleable.StepProgressBar_step_barBgColor, Color.GRAY);
        foreColor = a.getColor(R.styleable.StepProgressBar_step_barForeColor, Color.GREEN);
        init();
    }

    private void init() {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setStrokeWidth(lineH);

        forePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        forePaint.setColor(foreColor);
        forePaint.setStyle(Paint.Style.FILL);
        forePaint.setStrokeWidth(lineH);

        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setColor(fontColor);
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(fontSize);
        fontPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void setDatas(List<StepProgressBarBean> datas) {
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
                timeHeight = rect.height();
                canvas.drawText(datas.get(i).getTiem(), (radius + itemWidth * i) - timeWidth/2
                        , radius *2 + timeHeight +marginTop, fontPaint);

                //内容
                fontPaint.getTextBounds(datas.get(i).getContent(), 0, datas.get(i).getContent().length(), rect);
                int textWidth = rect.width();
                textHeight = rect.height();
                canvas.drawText(datas.get(i).getContent(), (radius + itemWidth * i) - textWidth/2
                        , radius *2 + (timeHeight + textHeight)+(marginTop*2), fontPaint);
            }
        }
    }

//    /** 重新计算view的高度 */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        //自定义视图高度 = bar圆高度 + 两排文字的上间距 + 文本内容高度
//        int height = (radius * 2) + (marginTop * 2) + (textHeight + timeHeight);
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
//    }
}
