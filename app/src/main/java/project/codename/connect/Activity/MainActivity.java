package project.codename.connect.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import project.codename.connect.R;

public class MainActivity extends AppCompatActivity {
    /*
        메인화면 레이아웃 변경 및 기본틀 구성.

        2018.9.17 수정
     */

    private RecyclerView Mainactivity_RecyclerView;
    private SwipeRefreshLayout Mainactivity_Refreshlayout;
    private TextView Mainactivity_Textview_Login;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login_Checked();
        createcomponent();
        addcomponent();
    }/////onCreate

    private void Login_Checked() {
        //로그인여부 체크
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Mainactivity_Textview_Login.setVisibility(View.INVISIBLE);
                }
            }
        };
    }/////Login_Checked

    private void createcomponent() {
        Mainactivity_Textview_Login = findViewById(R.id.mainactivity_textview_login);
        mAuth = FirebaseAuth.getInstance();

      /*  Mainactivity_RecyclerView = findViewById(R.id.mainactivity_recyclerview);
        Mainactivity_Refreshlayout = findViewById(R.id.mainactivity_refreshlayout);*/
    }///createcomponent

    private void addcomponent() {
        Mainactivity_Textview_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });/////

    }/////addcomponent

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }/////onStart

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}/////MainActivity
