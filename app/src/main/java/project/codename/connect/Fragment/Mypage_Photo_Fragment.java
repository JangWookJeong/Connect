package project.codename.connect.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.R;

public class Mypage_Photo_Fragment extends android.support.v4.app.Fragment {
    private RecyclerView PhotoFragment_Recyclerview;
    private TextView PhotoFragment_Notphoto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_myapge_photo_fragment, container, false);
        PhotoFragment_Notphoto = view.findViewById(R.id.mypage_photofragment_textview_notphoto);
        PhotoFragment_Recyclerview = view.findViewById(R.id.mypage_photofragment_recyclerview);

        return view;
    }/////

    public interface onGetuserPhototListener {
        void onGetuserPhoto(List<PostDTO> dto);
    }/////

    public static Mypage_Photo_Fragment newInstance() {

        Bundle args = new Bundle();

        Mypage_Photo_Fragment fragment = new Mypage_Photo_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}/////Mypage_Photo_Fragment
