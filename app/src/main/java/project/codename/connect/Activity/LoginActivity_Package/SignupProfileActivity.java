package project.codename.connect.Activity.LoginActivity_Package;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Activity.HomeActivity_Package.MainActivity;
import project.codename.connect.Connect_DAO.MypageDAO;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;
import project.codename.connect.Custom_Dialog.Custom_Dialog;
import project.codename.connect.Custom_Dialog.Profile_Custom_Dialog;
import project.codename.connect.R;

public class SignupProfileActivity extends AppCompatActivity {
    //프로필 설정 여부 확인 Profile_iscompleate

    public static final int PROFILE_IMAGE_CODE = 200;
    public static final int PROFILE_BACKGROUND_IMAGE_CODE = 201;
    private Boolean Profile_iscompleate = false, Profile_Image_iscompleate = false;
    private TextView Set_Profile;
    private ImageButton Go_back;
    private CircleImageView Profile_Circle_Image;
    private TextView Nickname, Set_Nickname, Name, Set_Name, Gender, Set_Gender;
    private RadioGroup Profile_RadioGroup;
    private String Profile_Gender_Result;
    private TextView BirthDay, Set_BirthDay;
    private Button Register_Button;
    private Profile_Custom_Dialog dialog;
    private DatePickerDialog dateDialog;
    private DatePickerDialog.OnDateSetListener listener;
    private String Profile_Image_Path, Profile_Background_Image_Path;
    private ImageView Profile_Background_Image;
    private Dialog Profile_dialog, Profile_Checkbox_dialog;
    private TextView Profile_Change_Button, Profile_Background_Change_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_profile);
        createcomponent();
        addlistener();

    }/////oncreate

    private void createcomponent() {

        if (dialog == null) {
            dialog = new Profile_Custom_Dialog(SignupProfileActivity.this);
        }

        if (Profile_dialog == null) {
            Profile_dialog = new Dialog(SignupProfileActivity.this);
        }

        if (Profile_Checkbox_dialog == null) {
            Profile_Checkbox_dialog = new Dialog(SignupProfileActivity.this);
        }


        Profile_dialog.setContentView(R.layout.profile_custom_imagechange_button);
        Profile_Checkbox_dialog.setContentView(R.layout.profile_custom_checkbox);
        Go_back = findViewById(R.id.signup_profileactivity_imagebutton_backspacebutton);
        Set_Profile = findViewById(R.id.signup_profileactivity_textview_setprofile);
        Profile_Circle_Image = findViewById(R.id.signup_profileactivity_circleimageview_profile_image);
        Nickname = findViewById(R.id.signup_profileactivity_edittext_nickname);
        Set_Nickname = findViewById(R.id.signup_profileactivity_textview_setnickname);
        Name = findViewById(R.id.signup_profileactivity_textview_name);
        Set_Name = findViewById(R.id.signup_profileactivity_textview_setname);
        Profile_RadioGroup = Profile_Checkbox_dialog.findViewById(R.id.signup_profileactivity_radiogroup_setgender);
        BirthDay = findViewById(R.id.signup_profileactivity_textview_birthday);
        Set_BirthDay = findViewById(R.id.signup_profileactivity_textview_setbirthday);
        Register_Button = findViewById(R.id.signup_profileactivity_button_register);
        Profile_Background_Image = findViewById(R.id.signup_profileactivity_Imageview_backgroundimage);
        Gender = findViewById(R.id.signup_profileactivity_textview_gender);
        Set_Gender = findViewById(R.id.signup_profileactivity_textview_setgender);
        Profile_Change_Button = Profile_dialog.findViewById(R.id.profile_image_change);
        Profile_Background_Change_Button = Profile_dialog.findViewById(R.id.profile_background_image_change);


    }//////

    private void addlistener() {
        Go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupProfileActivity.this, "뒤로가기버튼클릭", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });/////Go_back
        Profile_Circle_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //프로필 이미지 클릭했을때 갤러리로 이동
                openProfile();
            }
        });/////Profile_Image

        Profile_Background_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openProfileBackground();
            }
        });

        Set_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile_dialog.show();
            }
        });/////

        Set_Nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //닉네임 설정
                dialog.callFuntion(Nickname, "닉네임", "※ 닉네임을 설정하시면 다른사용자에게　　닉네임으로 보여집니다.\r\n※ 부적합한 닉네임 사용시 이용제한됩니다.");

            }
        });/////Set_Nickname
        Set_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이름 설정 클릭했을때

                //닉네임 설정
                dialog.callFuntion(Name, "이름", " ※ 이름이 실명과 다를경우 수익창출이 불가 　　합니다.");
            }
        });/////Set_Name

        Profile_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int getid) {
                RadioButton radio_result = (RadioButton) Profile_Checkbox_dialog.findViewById(getid);
                Profile_Gender_Result = radio_result.getText().toString();
                Gender.setText(Profile_Gender_Result);
                Profile_Checkbox_dialog.dismiss();
            }
        });/////Profile_RadioGroup

        Set_BirthDay.setOnClickListener(new View.OnClickListener() {

            //생년월일을 입력받을 데이트 피커 다이얼로그
            @Override
            public void onClick(View view) {
                if (dateDialog == null) {
                    dateDialog = new DatePickerDialog(SignupProfileActivity.this, android.R.style.Theme_Holo_Dialog);
                }
                dateDialog.getDatePicker().setCalendarViewShown(false);
                dateDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dateDialog.show();

                dateDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        BirthDay.setText(year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일");
                    }
                });

            }


        });/////

        Register_Button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (Nickname.getText().toString().length() > 0 && Nickname.getText() != null) {
                    if (Name.getText().toString().length() > 0 && Name.getText() != null) {
                        if (Profile_Gender_Result.toString().length() > 0 && Profile_Gender_Result != null) {
                            if (BirthDay.getText().toString().length() > 0 && BirthDay.getText() != null) {

                                new Profile_Asyctask().execute();
                            } else {
                                //생년월일을 입력하지 않은경우
                                Toast.makeText(SignupProfileActivity.this, "생년월일을 설정해주세요.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            //성별을 선택하지 않은경우
                            Toast.makeText(SignupProfileActivity.this, "성별을 설정해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //이름을 입력하지 않은경우
                        Toast.makeText(SignupProfileActivity.this, "이름을 설정해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //닉네임을 입력하지않은경우
                    Toast.makeText(SignupProfileActivity.this, "닉네임을 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });/////

        Profile_Change_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openProfile();
                Profile_dialog.dismiss();
            }
        });/////
        Profile_Background_Change_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileBackground();
                Profile_dialog.dismiss();
            }
        });/////Profile_Background_Change

        Set_Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile_Checkbox_dialog.show();
            }
        });

    }/////addcomponent()

    private void openProfileBackground() {
        //프로필 백그라운드 이미지 설정
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PROFILE_BACKGROUND_IMAGE_CODE);
    }

    private void openProfile() {
        //프로필 이미지 설정
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PROFILE_IMAGE_CODE);
    }////profile_Change

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PROFILE_IMAGE_CODE) {
            if (data != null) {
                //이미지 경로 얻어서 사진 설정
                Profile_Image_Path = getPath(data.getData());
                File file = new File(Profile_Image_Path);
                Profile_Circle_Image.setImageURI(Uri.fromFile(file));
                Profile_Image_iscompleate = true;

            } else {
                Toast.makeText(this, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show();

                return;
            }//////PROFILE_IMAGE

        }/////requestCode 확인

        if (requestCode == PROFILE_BACKGROUND_IMAGE_CODE) {
            if (data != null) {
                Profile_Background_Image_Path = getPath(data.getData());
                File file = new File(Profile_Background_Image_Path);
                Profile_Background_Image.setImageURI(Uri.fromFile(file));

            } else {
                Toast.makeText(this, "배경 이미지를 선택해주세요", Toast.LENGTH_SHORT).show();
            }
        }/////PROFILE_BACKGROUND_IMAGE_CODE

    }/////onActivityResult

    public String getPath(Uri uri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }///////////////////////////getPath

    public class Profile_Asyctask extends AsyncTask<Void, Void, Void> {

        private List<Profile_RegisterDTO> list;
        private Profile_RegisterDTO ProfileDTO;
        private MypageDAO dao;


        @Override
        protected Void doInBackground(Void... voids) {
            ProfileDTO.setProfile_Background_Image(Profile_Background_Image_Path);
            ProfileDTO.setProfile_Image(Profile_Image_Path);
            ProfileDTO.setNickName(Nickname.getText().toString());
            ProfileDTO.setName(Name.getText().toString());
            ProfileDTO.setGender(Gender.getText().toString());
            ProfileDTO.setBirthday(BirthDay.getText().toString());
            ProfileDTO.setKrw(0);
            dao.Register_Profile(ProfileDTO);
            Profile_Background_Image_Path = "";
            Profile_Image_Path = "";

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            ProfileDTO = new Profile_RegisterDTO();
            dao = new MypageDAO();
            Custom_Dialog.showLoading(SignupProfileActivity.this);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Custom_Dialog.hideLoading();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }/////Asyc
}/////class
