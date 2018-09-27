package project.codename.connect.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.codename.connect.R;

public class Bookmark_Message_Fragment extends Fragment {
    //BookMark_Message_Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.bookmark_message_fragment,container,false);
        return view;
    }


    public static Bookmark_Message_Fragment newInstance() {
        
        Bundle args = new Bundle();
        
        Bookmark_Message_Fragment fragment = new Bookmark_Message_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

   
}
