package butmed.barcode;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {
    TextView txtView1;
    ImageView myImageView;
    Bitmap myBitmap;
    BarcodeDetector detector;
    Frame frame;
    SparseArray<Barcode> barcodes;
    TextView txtView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView1 = (TextView) findViewById(R.id.txtview1);
                myImageView = (ImageView) findViewById(R.id.imgview);
                myBitmap = BitmapFactory.decodeResource(
                        getApplicationContext().getResources(),
                        R.drawable.poppy);
                myImageView.setImageBitmap(myBitmap);

                detector =
                        new BarcodeDetector.Builder(getApplicationContext())
                                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.CODE_39)
                                .build();
                if(!detector.isOperational()){
                    txtView1.setText("Could not set up the detector!");
                    return;
                }

                frame = new Frame.Builder().setBitmap(myBitmap).build();
                barcodes = detector.detect(frame);

                Barcode thisCode = barcodes.valueAt(0);
                txtView= (TextView) findViewById(R.id.txtContent);
                txtView.setText(thisCode.rawValue);

            }
        });


    }
}