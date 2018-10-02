package project.codename.connect.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Fragment.Mypage_Post_Fragment;
import project.codename.connect.R;

public class Mypage_Post_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PostDTO> items;
    private Fragment fm;
    private View view;
    private int count;
    private int Items_Size;

    public Mypage_Post_Adapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getImage_Size() != 0) {
                Items_Size = items.get(i).getImage_Size();
            }
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item1, parent, false);


        return new PostDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ((PostDate) holder).Postitem_title.setText(items.get(position).getTitle());
        ((PostDate) holder).Postitem_Name.setText(items.get(position).getName());
        ((PostDate) holder).Postitem_register_Date.setText("등록일 : " + items.get(position).getReguster_date());
        Glide.with(fm.getContext()).load(items.get(position).getProfile_Image_Url()).into(((PostDate) holder).Postitem_Profile_Image);
        ((PostDate) holder).Post_ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(fm.getContext(), "여기서는 수정 삭제 등을 할수있습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        ((PostDate) holder).Post_Relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(fm.getContext(), "내용보기로 이동합니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }/////onBindViewHolder

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void additem(List<PostDTO> setlist, Mypage_Post_Fragment mypage_post_fragment) {
        this.items = setlist;
        this.fm = mypage_post_fragment;
    }/////additem

    private class PostDate extends RecyclerView.ViewHolder {

        TextView Postitem_title, Postitem_Name, Postitem_Count, Postitem_register_Date;
        LinearLayout Postitems_images;
        ImageView Post_ImageItem1, Post_Imageitem2, Post_Imageitem3;
        CircleImageView Postitem_Profile_Image;
        ImageButton Post_ImageButton;
        RelativeLayout Post_Relativelayout;
        TextView postitem2_title, postitem2_name;


        public PostDate(View view) {
            super(view);

            Postitem_title = view.findViewById(R.id.postitems_title);
            Postitem_Name = view.findViewById(R.id.postitems_name);
            Postitem_Count = view.findViewById(R.id.postitems_viewcount);
            Postitems_images = view.findViewById(R.id.postitems_images);
            Post_ImageItem1 = view.findViewById(R.id.postitems_first_imageview);
            Post_Imageitem2 = view.findViewById(R.id.postitems_two_imageview);
            Post_Imageitem3 = view.findViewById(R.id.postitems_three_imageview);
            Postitem_Profile_Image = view.findViewById(R.id.postitems_profile_image);
            Postitem_register_Date = view.findViewById(R.id.postitems_register_date);
            Post_ImageButton = view.findViewById(R.id.postitems_vert_button);
            Post_Relativelayout = view.findViewById(R.id.Post_Relativelayout);


        }
    }/////PostDate
}/////Mypage_Post_Adapter
