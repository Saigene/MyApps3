package com.example.myapps.Login;
import com.example.myapps.DatabaseClass.UserDatabaseClass;
import com.example.myapps.MainActivity;
import com.example.myapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText mname, memail, mpassword, mpnumer;
    Button btnregister, btncancel;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    DatabaseReference dbuser;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        mname=(EditText)findViewById(R.id.CreateName);
        memail=(EditText)findViewById(R.id.CreateEmail);
        mpassword=(EditText)findViewById(R.id.CreatePass);
        mpnumer=(EditText)findViewById(R.id.CreateNo);
        btnregister=(Button)findViewById(R.id.btncreateAccount);
        btncancel=(Button)findViewById(R.id.btnCreateCancel);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        fauth=FirebaseAuth.getInstance();


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                final String username=mname.getText().toString().trim();
                final String userno=mpnumer.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required");
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(password.length() < 6){
                    mpassword.setError("Password must greater 6 character");
                    progressBar.setVisibility(View.INVISIBLE);
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccount.this,"User Created",Toast.LENGTH_SHORT).show();
                            userId=fauth.getCurrentUser().getUid();
                            dbuser=FirebaseDatabase.getInstance().getReference("User");
                            UserDatabaseClass userDatabaseClass=new UserDatabaseClass(username,userno,userId,email);
                            dbuser.child(userId).setValue(userDatabaseClass);
                            finish();
                        }else{
                            Toast.makeText(CreateAccount.this,"Error"+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(CreateAccount.this, Login.class);
                startActivity(intent);*/
                finish();
            }
        });
    }
}
