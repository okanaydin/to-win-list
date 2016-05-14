package xyz.oguzcelik.towinlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.6));

        login = (Button)findViewById(R.id.login);
        password = (EditText) findViewById(R.id.editText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(getApplicationContext(),ConfirmationActivity.class);
                if(password.getText().toString().equals("1234")) {
                    startActivity(login);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Şifre Yanlış !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
