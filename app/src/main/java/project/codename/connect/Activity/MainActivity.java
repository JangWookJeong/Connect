package project.codename.connect.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import project.codename.connect.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView Mainactivity_RecyclerView;
    private SwipeRefreshLayout Mainactivity_Refreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createcomponent();
    }/////onCreate

    private void createcomponent() {
        Mainactivity_RecyclerView = findViewById(R.id.mainactivity_recyclerview);
        Mainactivity_Refreshlayout = findViewById(R.id.mainactivity_refreshlayout);
    }///createcomponent
}/////MainActivity
