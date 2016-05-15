package xyz.oguzcelik.towinlist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PrizesActivity extends AppCompatActivity {

    TextView prizesGoldTxt;
    Button lunaparkBtn,sinemaBtn,oyuncakBtn,akvaryumBtn,dondurmaBtn,havuzBtn;
    ImageButton lunaparkImgBtn,sinemaImgBtn,oyuncakImgBtn,akvaryumImgBtn,dondurmaImgBtn,havuzImgBtn;
    Map prizeAmounts;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize);

        prizeAmounts = new HashMap();
        prizeAmounts.put("dondurma",200);
        prizeAmounts.put("oyuncak",350);
        prizeAmounts.put("sinema",400);
        prizeAmounts.put("lunapark",500);
        prizeAmounts.put("akvaryum",600);
        prizeAmounts.put("havuz",700);

        lunaparkBtn = (Button) findViewById(R.id.lunaparkButton);
        sinemaBtn = (Button) findViewById(R.id.sinemaButton);
        oyuncakBtn = (Button) findViewById(R.id.oyuncakButton);
        akvaryumBtn = (Button) findViewById(R.id.akvaryumButton);
        dondurmaBtn = (Button) findViewById(R.id.dondurmaButton);
        havuzBtn = (Button) findViewById(R.id.havuzButton);

        lunaparkImgBtn = (ImageButton) findViewById(R.id.lunaparkImage);
        sinemaImgBtn = (ImageButton) findViewById(R.id.sinemaImage);
        oyuncakImgBtn = (ImageButton) findViewById(R.id.oyuncakImage);
        akvaryumImgBtn = (ImageButton) findViewById(R.id.akvayumImage);
        dondurmaImgBtn = (ImageButton) findViewById(R.id.dondurmaImage);
        havuzImgBtn = (ImageButton) findViewById(R.id.havuzImage);

        LinearLayout.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = v.getTag().toString() + " : " + (int) prizeAmounts.get(v.getTag().toString());
                alert("Emin misin ? ",message, (int) prizeAmounts.get(v.getTag().toString()));
            }
        };

        lunaparkBtn.setOnClickListener(onClickListener);
        sinemaBtn.setOnClickListener(onClickListener);
        oyuncakBtn.setOnClickListener(onClickListener);
        akvaryumBtn.setOnClickListener(onClickListener);
        dondurmaBtn.setOnClickListener(onClickListener);
        havuzBtn.setOnClickListener(onClickListener);

        lunaparkImgBtn.setOnClickListener(onClickListener);
        sinemaImgBtn.setOnClickListener(onClickListener);
        oyuncakImgBtn.setOnClickListener(onClickListener);
        akvaryumImgBtn.setOnClickListener(onClickListener);
        dondurmaImgBtn.setOnClickListener(onClickListener);
        havuzImgBtn.setOnClickListener(onClickListener);

        prizesGoldTxt = (TextView) findViewById(R.id.prizesGoldTxt);

        firebase = new Firebase(MainActivity.firebaseURL);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.prizes = (Map) dataSnapshot.getValue();
                long gold = (long)MainActivity.prizes.get("gold");
                prizesGoldTxt.setText(gold+"");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void alert(String title, String Message, final int price){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebase.child("gold").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        firebase.child("gold").setValue((long)dataSnapshot.getValue() - price);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(),"İyi eğlenceler !", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"İptal Edildi.", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
