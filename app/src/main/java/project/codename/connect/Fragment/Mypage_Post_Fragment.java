package project.codename.connect.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.codename.connect.Adapter.Mypage_Post_Adapter;
import project.codename.connect.Connect_DAO.MypageDAO;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;
import project.codename.connect.Custom_Dialog.Custom_Dialog;
import project.codename.connect.R;

public class Mypage_Post_Fragment extends Fragment {

    private RecyclerView Mypage_Post_Recyclerview;
    private Mypage_Post_Adapter adapter;
    private PostDTO dto;


    public Mypage_Post_Fragment() {
        adapter = new Mypage_Post_Adapter();
    }

    public void init(PostDTO Dto) {
       adapter.additem(dto);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_mypage_content_fragment, container, false);
        Mypage_Post_Recyclerview = view.findViewById(R.id.mypage_recyclerview_content);
        Mypage_Post_Recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Mypage_Post_Recyclerview.setAdapter(adapter);
        return view;
    }

    public static Mypage_Post_Fragment newInstance() {

        Bundle args = new Bundle();

        Mypage_Post_Fragment fragment = new Mypage_Post_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
