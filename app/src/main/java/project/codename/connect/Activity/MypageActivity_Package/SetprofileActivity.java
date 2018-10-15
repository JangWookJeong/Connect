package project.codename.connect.Activity.MypageActivity_Package;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.R;

public class SetprofileActivity extends AppCompatActivity {
    private CircleImageView SetProfile_Image;
    private String Profile_Image_Url = "";
    private EditText SetProfile_Message;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprofile);
        gtintent();
        createcomponent();
        addlistener();
    }/////

    private void addlistener() {
        SetProfile_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void createcomponent() {
        SetProfile_Image = findViewById(R.id.setprofileactivity_circleimageview_profileimage);
        if (Profile_Image_Url != "") {
            Glide.with(getApplicationContext()).load(Profile_Image_Url).into(SetProfile_Image);
        } else {
            SetProfile_Image.setImageResource(R.drawable.profile_icon);
        }

        SetProfile_Message = findViewById(R.id.setprofileactivity_edittext_message);

    }

    private void gtintent() {
        Intent intent = getIntent();
        if (intent != null) {
            Profile_Image_Url = intent.getStringExtra("profile_image");
        }
    }
}//////
