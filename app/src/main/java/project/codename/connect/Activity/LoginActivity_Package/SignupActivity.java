package project.codename.connect.Activity.LoginActivity_Package;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import project.codename.connect.R;

public class SignupActivity extends AppCompatActivity {
    private Button Next_Button;
    private EditText Signup_Email, Signup_Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        createcomponent();
        addlistener();
    }

    private void createcomponent() {
        Next_Button = findViewById(R.id.signupactivity_button_next);
        Signup_Email = findViewById(R.id.signupactivity_edittext_email);
        Signup_Password = findViewById(R.id.signupactivity_edittext_password);

        mAuth = FirebaseAuth.getInstance();
    }/////

    private void addlistener() {

        Next_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //회원가입후 프로필 설정으로 이동

                if (Signup_Email.getText().length() != 0) {

                    //이메일주소를 입력한경우

                        //이메일 형식을 준수한경우
                        if(Signup_Email.getText().toString().contains("@") && Signup_Email.getText().toString().contains(".")){

                            if (Signup_Password.getText().length() >= 6) {
                                //패스워드를 6자 이상 입력한경우
                                mAuth.createUserWithEmailAndPassword(Signup_Email.getText().toString(), Signup_Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            //회원가입을 성공한경우
                                            startActivity(new Intent(getApplicationContext(), SignupProfileActivity.class));
                                            finish();
                                        } else {
                                            //회원가입을 실패 한경우
                                            Toast.makeText(SignupActivity.this, "회원가입을 실패 했습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                //패스워드를 6미만 입력한경우
                                Toast.makeText(SignupActivity.this, "비밀번호는 6자리 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignupActivity.this, "이메일 형식으로 입력해주세요", Toast.LENGTH_SHORT).show();
                        }

                } else {
                    //이메일주소를 입력하지 않은경우
                    Toast.makeText(SignupActivity.this, "이메일 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }/////onClick
        });

    }/////
}

