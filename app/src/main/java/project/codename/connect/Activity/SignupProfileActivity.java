package project.codename.connect.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Custom.Profile_Custom_Dialog;
import project.codename.connect.R;

public class SignupProfileActivity extends AppCompatActivity {
    //프로필 설정 여부 확인 Profile_iscompleate
    private Boolean Profile_iscompleate = false;
    private TextView Set_Profile;
    private ImageButton Go_back;
    private CircleImageView Profile_Image;
    private TextView Nickname, Set_Nickname, Name, Set_Name;
    private RadioGroup Profile_RadioGroup;
    private String Profile_Gender_Result;
    private TextView BirthDay,Set_BirthDay;
    private Button Register_Button;
    private Profile_Custom_Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_profile);
        createcomponent();
        addcomponent();

    }/////oncreate

    private void createcomponent() {

        dialog = new Profile_Custom_Dialog(getApplicationContext());
        Go_back = findViewById(R.id.signup_profileactivity_imagebutton_backspacebutton);
        Set_Profile = findViewById(R.id.signup_profileactivity_textview_setprofile);
        Profile_Image = findViewById(R.id.signup_profileactivity_circleimageview_profile_image);
        Nickname = findViewById(R.id.signup_profileactivity_edittext_nickname);
        Set_Nickname = findViewById(R.id.signup_profileactivity_textview_setnickname);
        Name = findViewById(R.id.signup_profileactivity_textview_name);
        Set_Name = findViewById(R.id.signup_profileactivity_textview_setname);
        Profile_RadioGroup = findViewById(R.id.signup_profileactivity_radiogroup_setgender);
        BirthDay = findViewById(R.id.signup_profileactivity_textview_birthday);
        Set_BirthDay = findViewById(R.id.signup_profileactivity_textview_setbirthday);
        Register_Button = findViewById(R.id.signup_profileactivity_button_register);

    }//////

    private void addcomponent() {
        Go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupProfileActivity.this, "뒤로가기버튼클릭", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });/////Go_back
        Profile_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //프로필 이미지 클릭했을때 갤러리로 이동
            }
        });/////Profile_Image

        Set_Nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //닉네임 설정
                dialog.callFuntion(Nickname,"닉네임","닉네임을 설정하시면 다른사용자에게 닉네임으로 보여집니다.");

            }
        });/////Set_Nickname
        Set_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이름 설정 클릭했을때

                //닉네임 설정
                dialog.callFuntion(Name,"이름","이름은 반드시 실명을 입력해주세요.");
            }
        });/////Set_Name

        Profile_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int getid) {
                RadioButton radio_result = (RadioButton) findViewById(getid);
                Profile_Gender_Result = radio_result.getText().toString();
                Toast.makeText(SignupProfileActivity.this, Profile_Gender_Result, Toast.LENGTH_SHORT).show();
            }
        });/////Profile_RadioGroup

        Set_BirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.callFuntion(Name,"생년월일","생년월일을 입력해주세요.");
            }
        });

        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Nickname.getText().toString().length() > 0 && Nickname.getText() != null) {
                    if(Name.getText().toString().length() > 0 && Name.getText() != null){
                        if(Profile_Gender_Result.toString().length() > 0 && Profile_Gender_Result != null){
                            if(BirthDay.getText().toString().length() > 0 && BirthDay.getText() != null){
                                Toast.makeText(SignupProfileActivity.this, Nickname + "닉네임" + Name + "이름" + Profile_Gender_Result + "성별" + BirthDay + "입니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                //생년월일을 입력하지 않은경우
                                Toast.makeText(SignupProfileActivity.this, "생년월일을 설정해주세요.", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            //성별을 선택하지 않은경우
                            Toast.makeText(SignupProfileActivity.this, "성별을 설정해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //이름을 입력하지 않은경우
                        Toast.makeText(SignupProfileActivity.this, "이름을 설정해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //닉네임을 입력하지않은경우
                    Toast.makeText(SignupProfileActivity.this, "닉네임을 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }/////addcomponent()
}/////class
