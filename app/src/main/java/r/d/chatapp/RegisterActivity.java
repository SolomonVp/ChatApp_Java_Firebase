package r.d.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextTextEmail;
    private EditText editTextTextPassword;
    private TextView textViewHaveAnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        textViewHaveAnAccount = findViewById(R.id.textViewHaveAnAccount);

        textViewHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickRegister(View view) {
        String email = editTextTextEmail.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Ошибка: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}