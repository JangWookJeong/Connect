package project.codename.connect.Activity.HomeActivity_Package;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import project.codename.connect.Activity.BookMarkActivity.BookmarkActivity;
import project.codename.connect.Activity.LoginActivity_Package.LoginActivity;
import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Activity.VideoActivity.VideoActivity;
import project.codename.connect.R;

public class MainActivity extends AppCompatActivity {
    /*
        메인화면 레이아웃 변경 및 기본틀 구성.

        2018.9.17 수정
     */

    private RecyclerView Mainactivity_RecyclerView;
    private SwipeRefreshLayout Mainactivity_Refresh;
    private TextView Mainactivity_Textview_Login;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth mAuth;
    private ImageButton Home_Button, Mypage_Button, Main_apps, Bookmark_Button, Play_Button;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Checked();
        createcomponent();
        addlistener();
    }/////onCreate

    private void Login_Checked() {
        //로그인여부 체크
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //유저가 로그인을 한경우
                    Mainactivity_Textview_Login.setVisibility(View.INVISIBLE);
                    Main_apps.setVisibility(View.VISIBLE);
                }
            }
        };
    }/////Login_Checked

    public void asdasdasd(){

        Toast.makeText(this, "asdasd", Toast.LENGTH_SHORT).show();

    }

    private void createcomponent() {
        Mainactivity_Textview_Login = findViewById(R.id.mainactivity_textview_login);
        mAuth = FirebaseAuth.getInstance();
        Home_Button = findViewById(R.id.main_bottom_home_button);
        Home_Button.setSelected(true);
        Mypage_Button = findViewById(R.id.main_bottom_myPage_button);
        Main_apps = findViewById(R.id.main_activity_imagebutton_apps);
        Mainactivity_Refresh = findViewById(R.id.mainactivity_refreshlayout);
        Bookmark_Button = findViewById(R.id.main_bottom_notification_button);
        Main_apps = findViewById(R.id.main_activity_imagebutton_apps);
        Play_Button = findViewById(R.id.main_bottom_searchbutton);

      /*  Mainactivity_RecyclerView = findViewById(R.id.mainactivity_recyclerview);
        Mainactivity_Refreshlayout = findViewById(R.id.mainactivity_refreshlayout);*/
    }///createcomponent

    private void addlistener() {

        Mainactivity_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh 기능 무한반복 중지
                Mainactivity_Refresh.setRefreshing(false);
            }
        });/////Mainactivity_Refresh
        Mainactivity_Textview_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });/////Mainactivity_Textview_Login
        Home_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });/////Home_Button
        Mypage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                finish();
            }
        });/////Mypage_Button

        Bookmark_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                finish();
            }
        });/////Bookmark_Button

        Main_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                //유저가 로그아웃을 한경우
                Mainactivity_Textview_Login.setVisibility(View.VISIBLE);
                Main_apps.setVisibility(View.INVISIBLE);
            }
        });/////Main_apps

        Play_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                finish();
            }
        });

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
    }/////
}/////MainActivity
