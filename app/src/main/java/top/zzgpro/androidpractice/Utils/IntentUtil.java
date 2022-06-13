package top.zzgpro.androidpractice.Utils;
import android.content.Context;
import android.content.Intent;

/**
 * @author zzg
 * 意图跳转工具类
 */
public class IntentUtil {
    private Context source;
    private Class dest;
    private Intent intent;
    private IntentUtil(){

    }
    public static Intent getIntent(Context source,Class dest){
        return new Intent(source,dest);
    }
}
