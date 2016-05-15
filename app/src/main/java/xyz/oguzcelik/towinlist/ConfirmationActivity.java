package xyz.oguzcelik.towinlist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmationActivity extends AppCompatActivity {

    ListView listItemView;
    List<Report> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        reportList = new ArrayList<Report>();
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + "towinlist" + File.separator + "jobsDone.txt");
        try {
            if(file.exists()!=true) {
                Toast.makeText(getApplicationContext(),"Yapılanlar listesi boş",Toast.LENGTH_SHORT).show();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                reportList = (List<Report>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        listItemView = (ListView) findViewById(R.id.listView);

        final ReportAdapter adapter = new ReportAdapter(this,R.layout.list_item_report, reportList);
        listItemView.setAdapter(adapter);
        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                final Dialog dialog = new Dialog(ConfirmationActivity.this);
                dialog.setContentView(R.layout.alert_dialog);
                dialog.setTitle("Onaylıyor musunuz ?");
                final Report report = (Report) parent.getItemAtPosition(position);
                TextView text = (TextView) dialog.findViewById(R.id.alertDescription);
                ImageView image = (ImageView) dialog.findViewById(R.id.alertImage);
                Button positiveButton = (Button) dialog.findViewById(R.id.positiveBtn);
                Button negativeButton = (Button) dialog.findViewById(R.id.negativeBtn);

                text.setText(report.getDescription());
                image.setImageBitmap(ReportActivity
                        .decodeSampledBitmapFromFile(report.getPhotoUrl(),750,425));
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeAndPersistTask(report);
                        // do something
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeAndPersistTask(report);
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });

    }

    public void removeAndPersistTask(Report report) {
        reportList.remove(report);
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + "towinlist" + File.separator + "jobsDone.txt");


        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(reportList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//        public void alert(String title, String Message){
//            AlertDialog.Builder builder=new AlertDialog.Builder(this);
//            builder.setCancelable(true);
//            builder.setView(R.layout.alert_dialog);
//
//            builder.setPositiveButton("Onay", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
//                }
//            });
//            builder.setNegativeButton("Ret", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_LONG).show();
//                }
//            });
//            builder.show();
//        }


    }
