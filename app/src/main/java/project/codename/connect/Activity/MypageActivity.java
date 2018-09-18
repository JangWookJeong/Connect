package project.codename.connect.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import project.codename.connect.Adapter.Mypage_Tabmenu_Adapter;
import project.codename.connect.Fragment.Mypage_content_Fragment;
import project.codename.connect.R;

public class MypageActivity extends AppCompatActivity {

    private TabLayout Mypage_Tab_Menu;
    private ViewPager Mypage_ViewPager;
    private Mypage_Tabmenu_Adapter tab_adapter;
    private ImageButton Home_Button,Mypage_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        createcomponent();
        addcomponent();
    }/////onCreate



    private void createcomponent() {
        tab_adapter = new Mypage_Tabmenu_Adapter(getSupportFragmentManager());
        Mypage_Tab_Menu = findViewById(R.id.mypage_tablayout_menu);
        Mypage_ViewPager = findViewById(R.id.mypage_menu_viewpager);

        Mypage_ViewPager.setAdapter(tab_adapter);
        Mypage_Tab_Menu.setupWithViewPager(Mypage_ViewPager);

        Home_Button = findViewById(R.id.main_bottom_home_button);
        Mypage_Button = findViewById(R.id.main_bottom_myPage_button);

    }/////createcomponent

    private void addcomponent() {
        Home_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });/////Home_Button

        Mypage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MypageActivity.this, "현재페이지 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }/////addcomponent
}/////MypageActivity
