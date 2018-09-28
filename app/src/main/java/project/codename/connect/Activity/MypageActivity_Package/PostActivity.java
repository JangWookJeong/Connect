package project.codename.connect.Activity.MypageActivity_Package;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.irshulx.Editor;
import com.github.irshulx.EditorListener;
import com.github.irshulx.models.EditorContent;
import com.github.irshulx.models.EditorTextStyle;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import project.codename.connect.Connect_DAO.PostDAO;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Method;
import project.codename.connect.R;
import top.defaults.colorpicker.ColorPickerPopup;


public class PostActivity extends AppCompatActivity {
    private Editor editor;
    private TextView Post_Upload, Post_Cancel;
    private String checked_ImagePath;
    private Uri uri;
    private PostDTO dto;
    private PostDAO dao;
    private  String Register_Post;
    private EditText Post_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        editor = (Editor) findViewById(R.id.editor);

        //bitmap image get..
       /* Glide.with(getApplicationContext()).asBitmap().load("https://firebasestorage.googleapis.com/v0/b/connect-d69f9.appspot.com/o/%EA%B4%80%EB%A6%AC%EC%9E%90%2F%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%2F%ED%9A%8C%EC%9B%90%2F%E3%85%A1%E3%84%B4%E3%84%B7%E3%84%B9%2F20180923_163758.jpg?alt=media&token=05aa8d12-4ee3-42a0-97af-be4ef4e35b40")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        editor.insertImage(resource);
                        //할일

                    }
                });*/


        setUpEditor();

        createpomponent();

        addlistener();

    }

    private void addlistener() {
        Post_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_Upload.setTextColor(Color.BLUE);
                Register_Post = editor.getContentAsHTML();
                if (Post_Title.getText().length() != 0){
                    if(editor.getContentAsHTML() != null){
                      /*  new PostAsyncTask().execute();*/
                        Toast.makeText(PostActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                        finish();
                    }else{
                        Toast.makeText(PostActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }else{
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

        if(dto == null){
            dto = new PostDTO();
        }
        if(dao == null){
            dao = new PostDAO();
        }

    }/////

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
                                Toast.makeText(PostActivity.this, "picked" + colorHex(color), Toast.LENGTH_LONG).show();
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
            checked_ImagePath = Method.UrigetPath(PostActivity.this,data.getData());


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

    public class PostAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }/////

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dto.setPost(Register_Post);
            dto.setTitle(Post_Title.getText().toString());
            dao.register_Post(dto);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(PostActivity.this, "업로드 성공.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}