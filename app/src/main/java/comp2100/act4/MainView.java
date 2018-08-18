package comp2100.act4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View implements View.OnTouchListener, Runnable{
    class Target {
        float x, y, r;
        boolean movingLeft = false;
        Target(float x, float y, float r) {
            this.x = x; this.y = y; this.r = r;
        }
    }

    Handler timer;
    Bitmap image;
    public Integer score = 0;

    MainView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.setOnTouchListener(this);
        timer = new Handler();
        timer.postDelayed(this, 10);
    }

    Target target = new Target(540.0f, 600, 100.0f);
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.MAGENTA);
        p.setStrokeWidth(10.0f);
        canvas.drawCircle(target.x, target.y, target.r, p);
        p.setColor(Color.GRAY);
        canvas.drawCircle(touchTarget.x, touchTarget.y, touchTarget.r, p);
        p.setTextSize(120);
        canvas.drawText(Integer.toString(score), 40, 160, p);
    }

    public void updateTarget() {
        if (target.x - target.r <= 0) {
            target.movingLeft = false;
        } else if (target.x + target.r >= 1080) {
            target.movingLeft = true;
        }

        if (target.movingLeft) {
            target.x -= 12;
        } else {
            target.x += 12;
        }
    }

    Target touchTarget = new Target(0, 0, 10);
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            touchTarget.x = motionEvent.getX();
            touchTarget.y = motionEvent.getY();

            if (touchTarget.y > 1000) {
                score = 0;
                return true;
            }

            if (touchTarget.x < target.x + target.r
                    && touchTarget.x > target.x - target.r
                    && touchTarget.y < target.y + target.r
                    && touchTarget.y > target.y - target.r) {
                score ++;
            }
            this.invalidate();
        }
        return true;
    }

    @Override
    public void run() {
        updateTarget();
        this.invalidate();
        timer.postDelayed(this, 10);
    }
}
