package project.codename.connect.Activity.MypageActivity_Package;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
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

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Activity.BookMarkActivity.BookmarkActivity;
import project.codename.connect.Activity.HomeActivity_Package.MainActivity;
import project.codename.connect.Activity.VideoActivity.VideoActivity;
import project.codename.connect.Adapter.Mypage_Tabmenu_Adapter;
import project.codename.connect.Connect_DAO.MypageDAO;
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
    private ProgressDialog LoginDialog;
    private FloatingActionButton Writer;


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
        }
    }/////loadUserInfo

    private void call_profile() {


        String email = user.getEmail();
        String emailcheck = email.substring(email.indexOf("@") + 1, email.indexOf("."));
        Name.setText("");
        Message.setText("");
        if (emailcheck.toLowerCase().equals("gmail")) {
            Name.setText(user.getDisplayName());
        } else {
            new Myapge_Asyctask().execute();
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
        Writer = findViewById(R.id.write);
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

        Writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PostActivity.class));
            }
        });
    }/////addcomponent


    public interface onGetuserInfo {
        void getuserInfo(Profile_RegisterDTO dto);
    }


    public class Myapge_Asyctask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Dao.Call_User_Profile(getInfo);
            return null;
        }///////

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Custom_Dialog.showLoading(MypageActivity.this);
            /*LoginDialog.show();*/
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}/////MypageActivity


