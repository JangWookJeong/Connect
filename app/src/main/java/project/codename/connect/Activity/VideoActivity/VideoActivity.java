package project.codename.connect.Activity.VideoActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import project.codename.connect.Activity.BookMarkActivity.BookmarkActivity;
import project.codename.connect.Activity.HomeActivity_Package.MainActivity;
import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.R;

public class VideoActivity extends AppCompatActivity {

    private ImageButton Home_Button, Mypage_Button, Bookmark_Button, Play_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        createcomponent();
        addlistener();

    }/////onCreate

    private void createcomponent() {
        Home_Button = findViewById(R.id.main_bottom_home_button);
        Mypage_Button = findViewById(R.id.main_bottom_myPage_button);
        Bookmark_Button = findViewById(R.id.main_bottom_notification_button);
        Play_Button = findViewById(R.id.main_bottom_searchbutton);
        Play_Button.setSelected(true);
    }/////

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
        Play_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });/////Play_Button
    }
}
