package e.rionadmin.guiactivity4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class mainView extends View implements View.OnTouchListener, Runnable{

    float xt = 0.0f, yt = 0.0f;
    Handler timer;
    Bitmap image;

    public mainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        image = BitmapFactory.decodeResource(getResources(), R.drawable.blob);
        timer = new Handler();
        timer.postDelayed(this, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(10.0f);
        canvas.drawLine(canvas.getWidth()/2.0f, 0.0f, xt, yt, p);
        canvas.drawBitmap();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void run() {

    }
}
