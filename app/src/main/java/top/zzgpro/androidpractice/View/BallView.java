package top.zzgpro.androidpractice.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BallView extends View {

    public float currentX=60;
    public float currentY=60;

    //定义并创建画笔
    Paint paint=new Paint();

    public BallView(Context context) {
        super(context);
    }

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色
        paint.setColor(Color.RED);
        //画一个圆
        canvas.drawCircle(currentX,currentY,20,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //修改 currentX，currentY的值
        currentX=event.getX();
        currentY=event.getY();

        //通知当前组件重新绘制自己
        invalidate();
        //表明该处理方法已经处理了该事件
        return true;
    }
}