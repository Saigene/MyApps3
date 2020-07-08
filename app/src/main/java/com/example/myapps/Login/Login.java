package com.example.myapps.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapps.MainActivity;
import com.example.myapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    CardView btnlogin;
    TextView createaccount;
    EditText mpassword,muser;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    private long backpresstime;
    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mpassword=(EditText)findViewById(R.id.userpassword);
        muser=(EditText)findViewById(R.id.userlogin);
        btnlogin=(CardView) findViewById(R.id.btnlogin);
        createaccount=(TextView)findViewById(R.id.crateaccount);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        fauth=FirebaseAuth.getInstance();

   /*     if(fauth.getCurrentUser()!=null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
*/
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=muser.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    muser.setError("Email Required");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user

                fauth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this,"Error"+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    }
                });
            }
        });

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        if(backpresstime + 2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }else{
            backtoast=Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backpresstime=System.currentTimeMillis();
    }
}
