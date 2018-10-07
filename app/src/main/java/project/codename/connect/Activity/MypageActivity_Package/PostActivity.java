package project.codename.connect.Activity.MypageActivity_Package;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.irshulx.Editor;
import com.github.irshulx.EditorListener;
import com.github.irshulx.models.EditorTextStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Connect_DAO.MypageDAO;
import project.codename.connect.Connect_DAO.PostDAO;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Class.Method;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;
import project.codename.connect.Custom_Dialog.Custom_Dialog;
import project.codename.connect.R;
import top.defaults.colorpicker.ColorPickerPopup;


public class PostActivity extends AppCompatActivity {
    private Editor editor;
    private TextView Post_Upload, Post_Cancel, Post_date, Post_Name;
    private String checked_ImagePath;
    private CircleImageView Post_Profile_Image;
    private Uri uri;
    private PostDTO dto;
    private PostDAO dao;
    private String Register_Post;
    private EditText Post_Title;
    private onPost_GetuserInfo Post_getInfo;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private EditText Post_Tag;
    private String Profile_Image_Url;
    private int image_Size;
    private ImageButton button;
    private List<String> Image_Urls;


    public void setPost_getInfo(onPost_GetuserInfo post_getInfo) {
        Post_getInfo = post_getInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        editor = (Editor) findViewById(R.id.editor);
        button = editor.findViewById(R.id.btn_remove);

        Post_loadUserInfo();
        setUpEditor();
        createpomponent();
        addlistener();
        Post_call_profile();

    }

    private void Post_loadUserInfo() {

        //유저의 프로필 정보를 가지고 오는 콜백메서드
        if (Post_getInfo == null) {
            //리스너가 널인경우 실행.
            Post_getInfo = new onPost_GetuserInfo() {
                @Override
                public void getuserInfo(Profile_RegisterDTO dto) {
                    if (dto != null) {
                        Post_Name.setText(dto.getName());
                        Post_date.setText(dto.getRegistration_date());
                        Profile_Image_Url = dto.getProfile_Image();
                        Glide.with(getApplicationContext()).load(Profile_Image_Url).into(Post_Profile_Image);
                        Custom_Dialog.hideLoading();

                    }
                }
            };
        }
    }/////loadUserInfo

    private void addlistener() {
        Post_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_Upload.setTextColor(Color.BLUE);
                Register_Post = editor.getContentAsHTML();
                if (Post_Title.getText().length() != 0) {
                    if (editor.getContentAsHTML() != null) {
                        Image_Urls = Method.string_Divide(Register_Post);
                        if (Image_Urls != null) {
                            image_Size = Image_Urls.size();
                            new PostAsyncTask().execute();

                        } else {
                            Toast.makeText(PostActivity.this, "Null", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(PostActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PostActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Post_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_Cancel.setTextColor(Color.BLUE);
                startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                finish();
            }
        });
    }/////addlistener

    private void createpomponent() {
        Post_Upload = findViewById(R.id.postactivity_textview_upload);
        Post_Cancel = findViewById(R.id.postactivity_textview_cancel);
        Post_Title = findViewById(R.id.postactivity_edittext_title);
        Post_date = findViewById(R.id.postactivity_textview_date);
        Post_Name = findViewById(R.id.postactivity_textview_nickname);
        Post_Profile_Image = findViewById(R.id.postactivity_circleimageview_profileimage);
        Post_date = findViewById(R.id.postactivity_textview_date);
        Post_Tag = findViewById(R.id.postactivity_edittext_tag);
        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();
        if (dto == null) {
            dto = new PostDTO();
        }
        if (dao == null) {
            dao = new PostDAO();
        }

    }/////createpomponent


    private void setUpEditor() {

        findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H1);
            }
        });

        findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H2);
            }
        });

        findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H3);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.BOLD);
            }
        });

        findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.ITALIC);
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.INDENT);
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.OUTDENT);
            }
        });

        findViewById(R.id.action_bulleted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(false);
            }
        });

        findViewById(R.id.action_unordered_numbered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(true);
            }
        });

        findViewById(R.id.action_hr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertDivider();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.openImagePicker();
            }
        });


        findViewById(R.id.action_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(PostActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("확인")
                        .cancelTitle("취소")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(findViewById(android.R.id.content), new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {

                                editor.updateTextColor(colorHex(color));
                            }

                            @Override
                            public void onColor(int color, boolean fromUser) {
                            }
                        });


            }
        });


        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertLink();
            }
        });

        findViewById(R.id.action_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertMap();
            }
        });

        findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor.clearAllContents();
            }
        });
        editor.setFontFace(R.string.fontFamily__serif);
        Map<Integer, String> headingTypeface = getHeadingTypeface();
        Map<Integer, String> contentTypeface = getContentface();
        editor.setHeadingTypeface(headingTypeface);
        editor.setContentTypeface(contentTypeface);
        editor.setDividerLayout(R.layout.tmpl_divider_layout);
        editor.setEditorImageLayout(R.layout.tmpl_image_view);
        editor.setListItemLayout(R.layout.tmpl_list_item);
        //editor.setNormalTextSize(10);
        // editor.setEditorTextColor("#FF3333");
        //editor.StartEditor();
        editor.setEditorListener(new EditorListener() {
            @Override
            public void onTextChanged(EditText editText, Editable text) {
                // Toast.makeText(EditorTestActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpload(Bitmap image, String uuid) {
                //Toast.makeText(PostActivity.this, uuid, Toast.LENGTH_LONG).show();
                /**
                 * TODO do your upload here from the bitmap received and all onImageUploadComplete(String url); to insert the result url to
                 * let the editor know the upload has completed
                 */


                editor.onImageUploadComplete(checked_ImagePath, uuid);
                //editor.onImageUploadFailed(uuid);
            }
        });
        render();
        editor.render();  // this method must be called to start the editor

        /*findViewById(R.id.btnRender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retrieve the content as serialized, you could also say getContentAsHTML();

                String text = editor.getContentAsSerialized();
                Intent intent = new Intent(getApplicationContext(), RenderTestActivity.class);
                intent.putExtra("content", text);
                startActivity(intent);
            }
        });*/
    }

    private String colorHex(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "#%02X%02X%02X", r, g, b);
    }

    public static void setGhost(Button button) {
        int radius = 4;
        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);
        background.setStroke(4, Color.WHITE);
        background.setCornerRadius(radius);
        button.setBackgroundDrawable(background);
    }

    private void render() {

    }/////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == editor.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            checked_ImagePath = Method.UrigetPath(PostActivity.this, data.getData());

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                editor.insertImage(bitmap);

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
            Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            // editor.RestoreState();
        } else if (requestCode == editor.MAP_MARKER_REQUEST) {
            editor.insertMap(data.getStringExtra("cords"));
        }

    }/////

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("글작성을 취소하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                        finish();
                    }

                })
                .setNegativeButton("아니오", null)
                .show();
    }

    public Map<Integer, String> getHeadingTypeface() {
        Map<Integer, String> typefaceMap = new HashMap<>();
        typefaceMap.put(Typeface.NORMAL, "fonts/GreycliffCF-Bold.ttf");
        typefaceMap.put(Typeface.BOLD, "fonts/GreycliffCF-Heavy.ttf");
        typefaceMap.put(Typeface.ITALIC, "fonts/GreycliffCF-Heavy.ttf");
        typefaceMap.put(Typeface.BOLD_ITALIC, "fonts/GreycliffCF-Bold.ttf");
        return typefaceMap;
    }

    public Map<Integer, String> getContentface() {
        Map<Integer, String> typefaceMap = new HashMap<>();
        typefaceMap.put(Typeface.NORMAL, "fonts/Lato-Medium.ttf");
        typefaceMap.put(Typeface.BOLD, "fonts/Lato-Bold.ttf");
        typefaceMap.put(Typeface.ITALIC, "fonts/Lato-MediumItalic.ttf");
        typefaceMap.put(Typeface.BOLD_ITALIC, "fonts/Lato-BoldItalic.ttf");
        return typefaceMap;
    }


    public class PostAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dao.register_Post(dto, PostActivity.this);
            return null;
        }/////

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dto.setPost(Register_Post);
            dto.setDate(Method.getDate());
            dto.setTitle(Post_Title.getText().toString());
            dto.setName(Post_Name.getText().toString());
            dto.setProfile_Image_Url(Profile_Image_Url);
            dto.setImage_Size(image_Size);
            dto.setImage_Urls(Image_Urls);
            if (Post_Tag.getText().length() != 0) {
                if (Post_Tag.getText().toString().contains("#")) {
                    dto.setTag(Post_Tag.getText().toString());
                } else {
                    dto.setTag("#" + Post_Tag.getText().toString());
                }
            }
            Custom_Dialog.showLoading(PostActivity.this);
        }//////

    }/////

    public class PostgetUserInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dao.Call_User_Profile(Post_getInfo);
            return null;
        }/////doInBackground

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }/////getUserInfo

    private void Post_call_profile() {

        if (user != null) {
            //유저가 로그인을 하고 Post 에 들어온경우
            new PostgetUserInfo().execute();

        } else {
            //유저가 로그인을 하지않고 마이페이지에 들어온경우.

        }

    }/////

//" <img src = \" http://biuugames.huya.com/5-160222145918.jpg \ " > <br> <br> "
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao = null;
        dto = null;
    }/////onDestroy

    public interface onPost_GetuserInfo {
        void getuserInfo(Profile_RegisterDTO dto);
    }
}/////class

  /*String x = "<h2 id=\"installation\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color:#c00000;background-color:#333;text-align:center; margin-top: -80px !important;\">Installation</h2>" +
                "<h3 id=\"requires-html5-doctype\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color:#ff0000; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Requires HTML5 doctype</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">Bootstrap uses certain HTML elements and CSS properties which require HTML5 doctype. Include&nbsp;<code style=\"font-size: 12.6px;\">&lt;!DOCTYPE html&gt;</code>&nbsp;in the beginning of all your projects.</p>" +
                "<img src=\"https://firebasestorage.googleapis.com/v0/b/connect-d69f9.appspot.com/o/%EA%B4%80%EB%A6%AC%EC%9E%90%2F%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%2F%ED%9A%8C%EC%9B%90%2F7uAcn6c9bJWh6FH4Z0NSkwMuXg33%2F%EC%9D%B4%EB%AF%B8%EC%A7%80%2F20181005_033406.jpg?alt=media&token=dd41a522-803a-45c1-b1bc-10df81662d12\" />" +
                "<h2 id=\"integration\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-top: -80px !important;\">Integration</h2>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">3rd parties available in django, rails, angular and so on.</p>" +
                "<h3 id=\"django\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Django</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">Handy update for your django admin page.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: #c00000;\">django-summernote</li><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://pypi.python.org/pypi/django-summernote\" target=\"_blank\">summernote plugin for Django</a></li></ul>" +
                "<h3 id=\"ruby-on-rails\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Ruby On Rails</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">This gem was built to gemify the assets used in Summernote.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://github.com/summernote/summernote-rails\" target=\"_blank\">summernote-rails</a></li></ul>" +
                "<h3 id=\"angularjs\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">AngularJS</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">AngularJS directive to Summernote.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://github.com/summernote/angular-summernote\">angular-summernote</a></li></ul>" +
                "<h3 id=\"apache-wicket\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Apache Wicket</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">Summernote widget for Wicket Bootstrap.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"http://wb-mgrigorov.rhcloud.com/summernote\" target=\"_blank\">demo</a></li><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://github.com/l0rdn1kk0n/wicket-bootstrap/tree/4f97ca783f7279ca43f9e2ee790703161f59fa40/bootstrap-extensions/src/main/java/de/agilecoders/wicket/extensions/markup/html/bootstrap/editor\" target=\"_blank\">source code</a></li></ul>" +
                "<h3 id=\"webpack\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Webpack</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">Example about using summernote with webpack.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://github.com/hackerwins/summernote-webpack-example\" target=\"_blank\">summernote-webpack-example</a></li></ul>" +
                "<h3 id=\"meteor\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin-bottom: 8px; margin-right: 0px; margin-left: 0px;\">Meteor</h3>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\">Example about using summernote with meteor.</p>" +
                "<ul style=\"color: rgb(51, 51, 51);\"><li style=\"font-size: 14px; color: rgb(104, 116, 127);\"><a href=\"https://github.com/hackerwins/summernote-meteor-example\" target=\"_blank\">summernote-meteor-example</a></li></ul>" +
                "<p style=\"font-size: 14px; color: rgb(104, 116, 127);\"><br></p>";
        editor.render(x);*/