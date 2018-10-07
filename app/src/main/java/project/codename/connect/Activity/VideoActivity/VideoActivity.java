package project.codename.connect.Activity.VideoActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import project.codename.connect.Activity.BookMarkActivity.BookmarkActivity;
import project.codename.connect.Activity.HomeActivity_Package.MainActivity;
import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.R;

public class VideoActivity extends AppCompatActivity {
    public static String URL1 = "https://firebasestorage.googleapis.com/v0/b/connect-d69f9.appspot.com/o/%EA%B4%80%EB%A6%AC%EC%9E%90%2F%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%2F%ED%9A%8C%EC%9B%90%2F7uAcn6c9bJWh6FH4Z0NSkwMuXg33%2F%EC%9D%B4%EB%AF%B8%EC%A7%80%2F%EC%A0%9C%ED%94%84%EB%B2%A0%EC%A1%B0%EC%8A%A4%20%EC%9D%B8%ED%8A%B8%EB%A1%9C.avi?alt=media&token=ec4d2fd9-4de9-4160-9614-472d636ba2e0";
    private ImageButton Home_Button, Mypage_Button, Bookmark_Button, Play_Button;
    VideoView videoView;
    MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        createcomponent();
        addlistener();

    }/////onCreate

    private void createcomponent() {
        Mypage_Button = findViewById(R.id.main_bottom_myPage_button);
        Bookmark_Button = findViewById(R.id.main_bottom_notification_button);
        Play_Button = findViewById(R.id.main_bottom_searchbutton);
        Play_Button.setSelected(true);
        videoView = (VideoView) findViewById(R.id.videoview1);
        controller = new MediaController(this);
        new videosyc().execute();Home_Button = findViewById(R.id.main_bottom_home_button);

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
            public void onClick(View view) {
            }
        });/////Play_Button
    }

    class videosyc extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            videoView.start();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            videoView.setMediaController(controller);
            controller.setAnchorView(videoView);
            Uri uri = Uri.parse(URL1);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                    mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {

                            videoView.setMediaController(controller);
                            /*
                             * and set its position on screen
                             */
                            controller.setAnchorView(videoView);

                            ((ViewGroup) controller.getParent()).removeView(controller);

                            ((FrameLayout) findViewById(R.id.videoViewWrapper))
                                    .addView(controller);
                            controller.setVisibility(View.VISIBLE);
                        }
                    });
                }
            });

            videoView.seekTo(0);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            videoView.resume();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}
