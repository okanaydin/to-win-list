package xyz.oguzcelik.towinlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageButton imageButton;
    String jobTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        jobTag = getIntent().getStringExtra("tag");

        imageButton = (ImageButton) findViewById(R.id.jobImage);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = new File(Environment.getExternalStorageDirectory()
                            +File.separator + jobTag + ".jpg");
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
                    startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + jobTag + ".jpg");
            Bitmap imageBitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(),750,425);
            imageButton.setImageBitmap(imageBitmap);
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }




}
