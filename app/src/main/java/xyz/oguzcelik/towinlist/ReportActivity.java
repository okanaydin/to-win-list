package xyz.oguzcelik.towinlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    Button sendButton;
    EditText description;

    ImageButton imageButton;
    String jobTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        jobTag = getIntent().getStringExtra("tag");

        sendButton = (Button) findViewById(R.id.btnSend);
        imageButton = (ImageButton) findViewById(R.id.jobImage);
        description = (EditText) findViewById(R.id.jobDescription);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(cameraIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = new File(Environment.getExternalStorageDirectory()
                            +File.separator + "towinlist" + File.separator + jobTag + ".jpg");


                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext()
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    int cameraPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext()
                            , Manifest.permission.CAMERA);
                    if(permissionCheck == android.content.pm.PackageManager.PERMISSION_GRANTED
                            && cameraPermissionCheck == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
                    }

                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String photoPath = Environment.getExternalStorageDirectory()
                        + File.separator + "towinlist" + File.separator + jobTag + ".jpg";
                Report report = new Report(jobTag,description.getText().toString(), (long) MainActivity.prizes.get(jobTag),photoPath);
                try {
                    File file = new File(Environment.getExternalStorageDirectory()
                            + File.separator + "towinlist" + File.separator + "jobsDone.txt");
                    List<Report> reports = null;
                    if(!file.exists()) {
                        file.createNewFile();
                    } else {
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        reports = (List<Report>) ois.readObject();
                        ois.close();
                        fis.close();
                    }

                    if(reports == null) {
                        reports = new ArrayList<Report>();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    reports.add(report);
                    oos.writeObject(reports);
                    oos.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(),"Başarıyla gönderildi.",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "towinlist" + File.separator + jobTag + ".jpg");
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
