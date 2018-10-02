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
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Adapter.Mypage_Post_Adapter;
import project.codename.connect.Connect_DAO.MypageDAO;
import project.codename.connect.Connect_DAO.PostDAO;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.R;

public class Mypage_Post_Fragment extends Fragment {

    private RecyclerView Mypage_Post_Recyclerview;
    private Mypage_Post_Adapter adapter;
    private List<PostDTO> list;
    private MypageDAO dao;
    private TextView Mypage_Notcontent;

    private onGetusercontentListener listener;


    public Mypage_Post_Fragment() {
        if (adapter == null) {
            adapter = new Mypage_Post_Adapter();
        }
        if (this.list == null) {
            this.list = new ArrayList<>();
        }

        if (dao == null) {
            dao = new MypageDAO();
        }

    }

    public void setListener(Mypage_Post_Fragment.onGetusercontentListener listener) {
        this.listener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_mypage_content_fragment, container, false);
        new getPostContent().execute();
        Mypage_Post_Recyclerview = view.findViewById(R.id.mypage_recyclerview_content);
        Mypage_Notcontent = view.findViewById(R.id.mypage_post_fragment_notcontent);
        Mypage_Post_Recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {

                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                } else {

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //scrolling up
                    System.out.print("스크롤업");
                } else {
                    //scrolling down
                    System.out.print("스크롤다운");
                }
            }
        });

        if (listener == null) {
            listener = new onGetusercontentListener() {
                @Override
                public void onGetusercontent(List<PostDTO> list) {

                        if (list != null) {
                            adapter.additem(list,Mypage_Post_Fragment.this);
                            Mypage_Post_Recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                            Mypage_Post_Recyclerview.setAdapter(adapter);
                        } else {
                            Mypage_Post_Recyclerview.setVisibility(View.INVISIBLE);
                            Mypage_Notcontent.setVisibility(View.VISIBLE);
                    }
                }
            };
        }else{
            System.out.println("asdasdasda");
        }

        return view;
    }

    public static Mypage_Post_Fragment newInstance() {

        Bundle args = new Bundle();

        Mypage_Post_Fragment fragment = new Mypage_Post_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public class getPostContent extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dao.getPost_Mycontent(listener);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }

    public interface onGetusercontentListener {
        void onGetusercontent(List<PostDTO> dto);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
