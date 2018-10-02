package project.codename.connect.Activity.MypageActivity_Package;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Activity.BookMarkActivity.BookmarkActivity;
import project.codename.connect.Activity.HomeActivity_Package.MainActivity;
import project.codename.connect.Activity.VideoActivity.VideoActivity;
import project.codename.connect.Adapter.Mypage_Post_Adapter;
import project.codename.connect.Adapter.Mypage_Tabmenu_Adapter;
import project.codename.connect.Connect_DAO.MypageDAO;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;
import project.codename.connect.Custom_Dialog.Custom_Dialog;

import project.codename.connect.R;

public class MypageActivity extends AppCompatActivity {

    private TabLayout Mypage_Tab_Menu;
    private ViewPager Mypage_ViewPager;
    private Mypage_Tabmenu_Adapter tab_adapter;
    private ImageButton Home_Button, Mypage_Button, Play_Button, Bookmark_Button;
    private MypageDAO Dao;
    private TextView Name, Message;
    private ImageView Background_Uri;
    private CircleImageView Profile_Uri;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private onGetuserInfo getInfo;
    private SpeedDialView speedDialView;





    public void setGetInfo(onGetuserInfo getInfo) {
        this.getInfo = getInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        loadUserInfo();
        createcomponent();
        addlistener();
        call_profile();
    }/////onCreate

    private void loadUserInfo() {

        //유저의 프로필 정보를 가지고 오는 콜백메서드
        if (getInfo == null) {
            //리스너가 널인경우 실행.
            getInfo = new onGetuserInfo() {
                @Override
                public void getuserInfo(Profile_RegisterDTO dto) {
                    if (dto != null) {
                        Name.setText(dto.getName());
                        Glide.with(getApplicationContext()).load(dto.getProfile_Background_Image()).into(Background_Uri);
                        Glide.with(getApplicationContext()).load(dto.getProfile_Image()).into(Profile_Uri);
                        Custom_Dialog.hideLoading();

                    }
                }
            };
        }else{
            getInfo = null;
        }

           }/////loadUserInfo

    private void call_profile() {

        Name.setText("");
        Message.setText("");

        if (user != null) {
            //유저가 로그인을 하고 마이페이지에 들어온경우.
            String email = user.getEmail();
            String emailcheck = email.substring(email.indexOf("@") + 1, email.indexOf("."));

            if (emailcheck.toLowerCase().equals("gmail")) {
                Name.setText(user.getDisplayName());
            } else {
                new Myapge_Asyctask().execute();
            }
        } else {
            //유저가 로그인을 하지않고 마이페이지에 들어온경우.

        }

    }/////


    private void createcomponent() {
        if (tab_adapter == null) {
            tab_adapter = new Mypage_Tabmenu_Adapter(getSupportFragmentManager());
        }
        Mypage_Tab_Menu = findViewById(R.id.mypage_tablayout_menu);
        Mypage_ViewPager = findViewById(R.id.mypage_menu_viewpager);
        Mypage_ViewPager.setAdapter(tab_adapter);
        Mypage_Tab_Menu.setupWithViewPager(Mypage_ViewPager);
        Home_Button = findViewById(R.id.main_bottom_home_button);
        Mypage_Button = findViewById(R.id.main_bottom_myPage_button);
        Mypage_Button.setSelected(true);
        Bookmark_Button = findViewById(R.id.main_bottom_notification_button);
        Play_Button = findViewById(R.id.main_bottom_searchbutton);
        Name = findViewById(R.id.mypage_textview_name);
        Message = findViewById(R.id.mypage_textview_profilecontent);
        Background_Uri = findViewById(R.id.mypage_imageview_backgroundImage);
        Profile_Uri = findViewById(R.id.mypage_circleimageview_profile);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        speedDialView = findViewById(R.id.speedDial);
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.write1, R.drawable.edit)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()))
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.write2, R.drawable.bookmark)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()))
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.write3, R.drawable.camera)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()))
                        .create()
        );
        /*  Writer = findViewById(R.id.write);*/
        if (Dao == null) {
            Dao = new MypageDAO();
        }



    }/////createcomponent

    private void addlistener() {
        Home_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });/////Home_Button

        Mypage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });/////Mypage_Button

        Bookmark_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                finish();

            }
        });/////Bookmark
        Play_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                finish();
            }
        });/////

       /* Writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PostActivity.class));
            }
        });*/

        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.write1:
                        startActivity(new Intent(getApplicationContext(), PostActivity.class));
                        finish();
                        return true;
                    case R.id.write2:
                        Toast.makeText(MypageActivity.this, "테스트입니다2", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.write3:
                        Toast.makeText(MypageActivity.this, "테스트입니다3", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;

                }

            }
        });

    }/////addcomponent


    public interface onGetuserInfo {
        void getuserInfo(Profile_RegisterDTO dto);
    }

    public class Myapge_Asyctask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Dao.Call_User_Profile(getInfo,getApplicationContext());
            return null;
        }///////

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Custom_Dialog.showLoading(MypageActivity.this);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        /*    new getPostContent().execute();*/
        }
    }



}/////MypageActivity


