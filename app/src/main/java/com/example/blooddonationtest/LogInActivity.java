package com.example.blooddonationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    Button logInButton;
    TextView signUpTextView,forgotPasswordTextView;
    EditText logInEmailEditText,logInPasswordEditText;
    CheckBox rememberCheckBox;
    // Write a message to the database
    FirebaseDatabase database ;
    DatabaseReference myRef;

    private FirebaseAuth mAuth;
    String signInEmail,signInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setTitle("Homepage");
        setContentView(R.layout.activity_log_in);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // view finding
        logInButton =findViewById(R.id.logIbButtonID);
        signUpTextView =findViewById(R.id.signUpTextViewId);
        forgotPasswordTextView =findViewById(R.id.forgotPasswordTextViewId);
        logInEmailEditText =findViewById(R.id.logInEmailEditTextId);
        logInPasswordEditText =findViewById(R.id.logInPasswordEditTextId);
        rememberCheckBox=findViewById(R.id.rememberCheckBoxId);


        logInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);




        // remember data retrieve and checking
        String emailValue= new SharePref().loadRememberEmail(LogInActivity.this);
        String passwordValue= new SharePref().loadRememberPassword(LogInActivity.this);

        if (!emailValue.isEmpty() && !passwordValue.isEmpty()){
            logInEmailEditText.setText(String.valueOf(emailValue));
            logInPasswordEditText.setText(String.valueOf(passwordValue));
            userLogin();
        }
        else {
            // Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }





    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
    case R.id.logIbButtonID:
            userLogin();
        break;
        case R.id.signUpTextViewId:
        Intent intent=new Intent(LogInActivity.this,Registration.class);
        startActivity(intent);
        break;
        case R.id.forgotPasswordTextViewId:
        break;

    } }



    public void userLogin(){
        signInEmail= logInEmailEditText.getText().toString();
        signInPassword=logInPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(signInEmail)){
            logInEmailEditText.setError("Enter your email");
            logInEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signInEmail).matches()){
            logInEmailEditText.setError("Enter a valid  email address");
            logInEmailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(signInPassword)){
            logInPasswordEditText.setError("Enter password");
            logInPasswordEditText.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "login success", Toast.LENGTH_SHORT).show();

                            String user_id = mAuth.getCurrentUser().getUid();

                           // sharePref=new SharePref();
                            if (rememberCheckBox.isChecked()){
                                new SharePref().rememberData(LogInActivity.this,signInEmail,signInPassword);
                               // Toast.makeText(LogInActivity.this, "success", Toast.LENGTH_SHORT).show();
                            }
                            SharePref sharePref=new SharePref();
                            sharePref.saveId(LogInActivity.this,user_id);
                            Intent intent =new Intent(LogInActivity.this,HomeActivity.class);
                            //intent.putExtra("userId",user_id);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LogInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

}

