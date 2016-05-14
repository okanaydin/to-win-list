package xyz.oguzcelik.towinlist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {

    ListView listItemView;
    String[] listItemsValue = new String[] {"Deneme1","Deneme2","Deneme3","Deneme4","Deneme5","Deneme6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        listItemView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItemsValue);
        listItemView.setAdapter(adapter);
        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), listItemsValue[position], Toast.LENGTH_SHORT).show();
                alert("Title", "Message");
            }
        });

    }



        public void alert(String title, String Message){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_LONG).show();
                }
            });
            builder.show();
        }


    }
