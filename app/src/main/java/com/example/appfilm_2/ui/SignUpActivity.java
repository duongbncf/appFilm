package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appfilm_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUserNameorPassword;
    EditText edtPasswordCreate;
    ImageView btnRegister;
    ProgressDialog progressDialog;
    EditText edtConfirmPassword;
    private String fullName = "";
    private String image = "";
    private String gmail = "";
    private String password = "";
    private String passwordAgain = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtPasswordCreate = findViewById(R.id.edtPasswordCreate);
        edtUserNameorPassword = findViewById(R.id.edtUserNameorPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        if (edtPasswordCreate.length() < 8) {
            edtPasswordCreate.setError("Mật khẩu tối thiểu 8 ký tự");
        } else {
            edtPasswordCreate.setError(null);
        }
        if (edtPasswordCreate == edtConfirmPassword) {
            edtConfirmPassword.setError(null);
        } else {
            edtConfirmPassword.setError("Mật khẩu không trùng khớp");
        }
        initListener();
    }

    private void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }

            private void onClickSignUp() {
                String strEmail = edtUserNameorPassword.getText().toString().trim();
                String strPassword = edtPasswordCreate.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(strEmail,strPassword)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

        });
    }
})
    ;}
}