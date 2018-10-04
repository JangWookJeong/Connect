package project.codename.connect.Activity.MypageActivity_Package;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.irshulx.Editor;
import com.github.irshulx.EditorListener;
import com.github.irshulx.models.EditorContent;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Class.Method;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.R;

public class ContentActivity extends AppCompatActivity {
    /*
         유저가 자신이 작성한 글을 보는 액티비티
     */
    private Editor editor;
    private TextView Content_CommentSize, Content_Edit, Content_Title, Content_RegisterDates, Content_Nickname;
    private ImageButton Content_Vert;
    private CircleImageView Content_Profile;
    private EditText Content_Comment;
    private Intent intent;
    private List<PostDTO> items;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        createcomponent();
        getdata();
        addlistener();
    }/////onCreate

    private void addcomponent() {


    }/////addcomponent

    private void getdata() {

        if (getIntent() != null) {
            intent = getIntent();
            System.out.println(intent.getStringExtra("name"));
            System.out.println(intent.getStringExtra("title"));
            System.out.println(intent.getStringExtra("name"));
            System.out.println(intent.getStringExtra("register_date"));
            System.out.println(intent.getStringExtra("profile_url"));
            System.out.println(intent.getStringExtra("content"));
            position = intent.getIntExtra("position", 0);
            Content_Nickname.setText(intent.getStringExtra("name"));
            Content_Title.setText(intent.getStringExtra("title"));
            Content_RegisterDates.setText(intent.getStringExtra("register_date"));
            System.out.println(Method.change_address(intent.getStringExtra("content"))+"content");
            /*editor.render(intent.getStringExtra("content"));*/
            editor.render(intent.getStringExtra("content"));
            Glide.with(getApplicationContext()).load(intent.getStringExtra("profile_url")).into(Content_Profile);

           /* Glide.with(getApplicationContext()).asBitmap().load("https://firebasestorage.googleapis.com/v0/b/connect-d69f9.appspot.com/o/%EA%B4%80%EB%A6%AC%EC%9E%90%2F%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%2F%ED%9A%8C%EC%9B%90%2F7uAcn6c9bJWh6FH4Z0NSkwMuXg33%2F%EC%9D%B4%EB%AF%B8%EC%A7%80%2F20181005_033406.jpg?alt=media&token=dd41a522-803a-45c1-b1bc-10df81662d12")
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            editor.insertImage(resource);
                            //할일

                        }
                    });*/

        } else {
            Toast.makeText(this, "인텐트가 널입니다.", Toast.LENGTH_SHORT).show();
        }
        System.out.println(position + "기본값설정후");
    }//getdata

    private void addlistener() {
        editor.setEditorListener(new EditorListener() {
            @Override
            public void onTextChanged(EditText editText, Editable text) {
                Toast.makeText(ContentActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpload(Bitmap image, String uuid) {
                //Toast.makeText(PostActivity.this, uuid, Toast.LENGTH_LONG).show();
                /**
                 * TODO do your upload here from the bitmap received and all onImageUploadComplete(String url); to insert the result url to
                 * let the editor know the upload has completed
                 */

                editor.onImageUploadComplete("asd", uuid);
                //editor.onImageUploadFailed(uuid);
            }
        });

        editor.render();
    }////addlistener

    private void createcomponent() {
        editor = findViewById(R.id.contentactivity_texteditor);
        Content_Nickname = findViewById(R.id.contentactivity_textview_nickname);
        Content_Title = findViewById(R.id.contentactivity_textview_title);
        Content_RegisterDates = findViewById(R.id.contentactivity_textview_register_date);
        Content_Edit = findViewById(R.id.contentactivity_imagebutton_edit);
        Content_CommentSize = findViewById(R.id.comtentactivity_textview_commentsize);
        Content_Vert = findViewById(R.id.contentactivity_imagebutton_vert);
        Content_Comment = findViewById(R.id.contentactivity_edittext_comment);
        Content_Profile = findViewById(R.id.contentactivity_circleimageview_profile);


    }/////createcomponet


}/////ContentActivity
