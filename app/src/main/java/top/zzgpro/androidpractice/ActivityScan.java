package top.zzgpro.androidpractice;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.*;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ActivityScan extends AppCompatActivity {
//    private final String TestStr="https://blog.zzgpro.top";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Button button=findViewById(R.id.scan);
        Button scan=findViewById(R.id.scan1);
        ImageView imageView=findViewById(R.id.testimage);
         textView=findViewById(R.id.showdecode);
        EditText editText=findViewById(R.id.inputqrtext);
        button.setOnClickListener(view -> {
            Bitmap bitmap;
            try {
                if("".equals(editText.getText().toString())){
                    Toast.makeText(this,"请输入要生成二维码的内容",Toast.LENGTH_SHORT).show();
                    return;
                }
              bitmap= bitMatrixToBitmap(encode(editText.getText().toString())) ;
              imageView.setImageBitmap(bitmap);
              textView.setText(Optional.of(decode(bitmap)).orElse("二维码扫描出现问题"));
            } catch (WriterException e) {
                Log.e("SCANQR",e.getMessage());
//                e.printStackTrace();
            }
        });
        scan.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
            options.setPrompt("Scan QRCode");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(false);
            options.setBarcodeImageEnabled(true);
            options.setOrientationLocked(false);
            barcodeLauncher.launch(options);
        });
    }

    /**
     * 转换BitMatrix到Bitmap
     * @param bitMatrix
     * @return
     */
    private Bitmap bitMatrixToBitmap(BitMatrix bitMatrix) {
        final int width = bitMatrix.getWidth();
        final int height = bitMatrix.getHeight();

        final int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y * width + x] = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return bitmap;
    }

    /**
     * 生成二维码
     * @param contents 二维码的内容
     * @return BitMatrix 内容矩阵
     * @throws WriterException 抛出异常
     */
    private BitMatrix encode(String contents) throws WriterException {
        final Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        return new QRCodeWriter().encode(contents, BarcodeFormat.QR_CODE, 320, 320, hints);
    }
    private String decode(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        final int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        RGBLuminanceSource luminanceSource = new RGBLuminanceSource(width, height, pixels);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        try {
            final Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            hints.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            Result result = new QRCodeReader().decode(binaryBitmap, hints);

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    textView.setText(result.getContents());
                    Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

//
}