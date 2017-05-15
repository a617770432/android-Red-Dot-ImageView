package xyk.red.dot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Created by Administrator on 2017/5/6 0006.
 */

public class RedDotImageView extends ImageView {

    private Paint paint1;//绘制红点
    private final Paint paint2;//绘制数字
    private int msgNum;//数字值
    private Rect textBounds;//文字的bound
    private int redDotSize = 6;//红点大小

    public RedDotImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint1 = new Paint();
        paint2 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(getResources().getColor(android.R.color.holo_red_light));
        paint2.setColor(getResources().getColor(android.R.color.white));


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int radius;
        if (measuredHeight > measuredWidth) {
            radius = measuredWidth / redDotSize;

        } else {
            radius = measuredHeight / redDotSize;
        }
        paint2.setTextSize(radius);
        paint2.setTextAlign(Paint.Align.LEFT);
        textBounds = new Rect();
        paint2.getTextBounds(String.valueOf(msgNum), 0, String.valueOf(msgNum).length(), textBounds);

        canvas.drawCircle(measuredWidth - radius, radius, radius, paint1);
        Paint.FontMetricsInt fontMetrics = paint2.getFontMetricsInt();
        int baseline = (radius * 2 - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(String.valueOf(msgNum), getMeasuredWidth() - radius - textBounds.width() / 2, baseline, paint2);
    }

    public void setMsgNum(int msgNum) {
        if(msgNum > 99)
        {
            this.msgNum = 99;
        }
        else if(msgNum <0)
        {
            this.msgNum = 0;
        }else
        {
            this.msgNum = msgNum;
        }
        postInvalidate();
    }

    public void setRedDotSize(int redDotSize) {
        if(redDotSize >10 || redDotSize <=1)
            return;
        this.redDotSize = redDotSize;
        postInvalidate();
    }

}
