package xyz.oguzcelik.towinlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    Button disButton,ellerButton,kitapButton,meyveButton,odaButton,odevButton,sutButton,yemekButton;
    Button odul,login;
    ImageButton disImage,ellerImage,kitapImage,meyveImage,odaImage,odevImage,sutImage,yemekImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disButton=(Button)findViewById(R.id.disButton);
        ellerButton=(Button)findViewById(R.id.elButton);
        kitapButton=(Button)findViewById(R.id.kitapButton);
        meyveButton=(Button)findViewById(R.id.meyveButton);
        odaButton=(Button)findViewById(R.id.odaButton);
        odevButton=(Button)findViewById(R.id.odevButton);
        sutButton=(Button)findViewById(R.id.sutButton);
        yemekButton=(Button)findViewById(R.id.yemekButton);

        odul=(Button)findViewById(R.id.odul);
        login=(Button)findViewById(R.id.login);

        disImage=(ImageButton) findViewById(R.id.disImage);
        ellerImage=(ImageButton) findViewById(R.id.elImage);
        kitapImage=(ImageButton) findViewById(R.id.kitapImage);
        meyveImage=(ImageButton) findViewById(R.id.meyveImage);
        odaImage=(ImageButton) findViewById(R.id.odaImage);
        odevImage=(ImageButton) findViewById(R.id.odevImage);
        sutImage=(ImageButton) findViewById(R.id.sutImage);
        yemekImage=(ImageButton) findViewById(R.id.yemekImage);

        odul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),PrizesActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giris=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(giris);
            }
        });



        Button.OnClickListener click=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getTag().toString();
                Intent i=new Intent(getApplicationContext(),ReportActivity.class);
                i.putExtra("tag",v.getTag().toString());
                startActivity(i);
            }
        };

        disButton.setOnClickListener(click);
        ellerButton.setOnClickListener(click);
        kitapButton.setOnClickListener(click);
        kitapButton.setOnClickListener(click);
        meyveButton.setOnClickListener(click);
        odaButton.setOnClickListener(click);
        odevButton.setOnClickListener(click);
        sutButton.setOnClickListener(click);
        yemekButton.setOnClickListener(click);

        disImage.setOnClickListener(click);
        ellerImage.setOnClickListener(click);
        kitapImage.setOnClickListener(click);
        meyveImage.setOnClickListener(click);
        odaImage.setOnClickListener(click);
        odevImage.setOnClickListener(click);
        sutImage.setOnClickListener(click);
        yemekImage.setOnClickListener(click);
    }
}
