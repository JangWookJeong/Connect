package project.codename.connect.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.codename.connect.R;

public class Mypage_content_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_mypage_content_fragment,container,false);
        return view;
    }

    public static Mypage_content_Fragment newInstance() {

        Bundle args = new Bundle();

        Mypage_content_Fragment fragment = new Mypage_content_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
