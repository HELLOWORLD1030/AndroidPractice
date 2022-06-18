package top.zzgpro.androidpractice.View;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundView extends View {
    private Paint paint1, paint2;

    private int CurrentX=100;
    private int CurrentY=30;
    public RoundView(Context context) {
        super(context);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
