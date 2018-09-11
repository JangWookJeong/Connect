package project.codename.connect.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import project.codename.connect.R;

public class MainActivity extends AppCompatActivity {
    /*
        메인화면 레이아웃 변경 및 기본틀 구성.

        2018.9.11 수정
     */

    private RecyclerView Mainactivity_RecyclerView;
    private SwipeRefreshLayout Mainactivity_Refreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createcomponent();
        addcomponent();
    }/////onCreate

    private void createcomponent() {
        Mainactivity_RecyclerView = findViewById(R.id.mainactivity_recyclerview);
        Mainactivity_Refreshlayout = findViewById(R.id.mainactivity_refreshlayout);
    }///createcomponent

    private void addcomponent() {

        Mainactivity_RecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }/////addcomponent


}/////MainActivity
