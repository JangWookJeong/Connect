package project.codename.connect.Adapter;

import android.content.ClipData;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import project.codename.connect.Activity.MypageActivity_Package.ContentActivity;
import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Fragment.Mypage_Post_Fragment;
import project.codename.connect.R;

public class Mypage_Post_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String FIRST_TYPE = "R.layout.post_item2";
    public static final String MULTI_TYPE = "R.layout.post_item1";
    private List<PostDTO> items;
    private Fragment fm;
    private int count;
    private List<String> Image_Size;

    private Intent intent;

    public Mypage_Post_Adapter() {
        items = new ArrayList<>();
        Image_Size = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item1, parent, false);


        switch (viewType) {
            case R.layout.post_item2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item2, parent, false);
                return new FirstDate(view);
            case R.layout.post_item3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item3, parent, false);
                return new ThreeDate(view);
        }


        return new PostDate(view);
    }/////createviewholder

    @Override
    public int getItemViewType(int position) {


        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getImage_Size() != 0) {
                if (Image_Size.size() >= items.size()) {
                    break;
                } else {
                    Image_Size.add(String.valueOf(items.get(i).getImage_Size()));
                }
            }
        }

        switch (Integer.parseInt(Image_Size.get(position))) {

            case 1:
                return R.layout.post_item3;
            case 2:
                return R.layout.post_item2;
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        System.out.println(Integer.parseInt(Image_Size.get(position)) + "숫자");
        switch (Integer.parseInt(Image_Size.get(position))) {
            case 1:
                Glide.with(fm.getContext()).load(items.get(position).getProfile_Image_Url()).into(((ThreeDate) holder).PostItem3_Profile);
                ((ThreeDate) holder).PostItem3_Nickname.setText(items.get(position).getName());
                ((ThreeDate) holder).PostItem3_resisterdate.setText(items.get(position).getReguster_date());
                ((ThreeDate) holder).PostItem3_Title.setText(items.get(position).getTitle());
              /*  ((ThreeDate) holder).PostItem3_ViewCount.setText(0);
                ((ThreeDate) holder).PostItem3_CommentSize.setText(0);
                ((ThreeDate) holder).PostItem3_LikeSize.setText(0);*/
                ((ThreeDate) holder).PostItem3_Relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(fm.getContext(), "내용보기로 이동합니다.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(fm.getContext(), ContentActivity.class);
                        send_User_Profile(position);
                    }
                });
                break;


            case 2:

                ((FirstDate) holder).Postitem2_name.setText(items.get(position).getName());
                ((FirstDate) holder).Postitem2_title.setText(items.get(position).getTitle());
                Glide.with(fm.getContext()).load(items.get(position).getProfile_Image_Url()).into(((FirstDate) holder).Postitem2_Proflie);
                System.out.println(position + "position");
                ((FirstDate) holder).Post_Relative_Layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(fm.getContext(), "내용보기로 이동합니다.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(fm.getContext(), ContentActivity.class);
                        send_User_Profile(position);


                        fm.getContext().startActivity(intent);
                    }
                });
                break;

            case 3:

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
                        intent = new Intent(fm.getContext(), ContentActivity.class);
                        send_User_Profile(position);
                    }
                });

                break;

            default:
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
                        intent = new Intent(fm.getContext(), ContentActivity.class);
                        send_User_Profile(position);
                    }
                });

                break;


        }


    }/////onBindViewHolder

    private void send_User_Profile(int position) {
        intent.putExtra("position", position);
        intent.putExtra("name", items.get(position).getName());
        intent.putExtra("title", items.get(position).getTitle());
        intent.putExtra("content", items.get(position).getPost());
        intent.putExtra("register_date", items.get(position).getReguster_date());
        intent.putExtra("profile_url", items.get(position).getProfile_Image_Url());
        fm.getContext().startActivity(intent);
    }

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

    private class FirstDate extends RecyclerView.ViewHolder {
        TextView Postitem2_title, Postitem2_name;
        CircleImageView Postitem2_Proflie;
        RelativeLayout Post_Relative_Layout;

        public FirstDate(View view) {
            super(view);
            System.out.println("FirstDate in");
            Postitem2_name = view.findViewById(R.id.postitems_2_name);
            Postitem2_title = view.findViewById(R.id.postitems_2_title);
            Postitem2_Proflie = view.findViewById(R.id.postitem_2_profileimage);
            Post_Relative_Layout = view.findViewById(R.id.postitem_2_relativelayout);
        }
    }

    private class ThreeDate extends RecyclerView.ViewHolder {
        RelativeLayout PostItem3_Relativelayout;
        CircleImageView PostItem3_Profile;
        TextView PostItem3_Nickname, PostItem3_resisterdate, PostItem3_Title, PostItem3_ViewCount, PostItem3_LikeSize, PostItem3_CommentSize;
        ImageButton PostItem3_Vert, PostItem3_LikeButton, PostItem3_CommentButton;
        ImageView PostItem3_Imagecontent;
        TextView PostItem3_InputComment;

        public ThreeDate(View itemView) {
            super(itemView);
            PostItem3_Profile = itemView.findViewById(R.id.postitem3_circleimageview);
            PostItem3_Nickname = itemView.findViewById(R.id.postitem3_textview_nickname);
            PostItem3_resisterdate = itemView.findViewById(R.id.postitem3_textview_registerdate);
            PostItem3_Vert = itemView.findViewById(R.id.postitem3_imagebutton_vert);
            PostItem3_LikeButton = itemView.findViewById(R.id.postitem3_imagebutton_likebutton);
            PostItem3_CommentButton = itemView.findViewById(R.id.postitem3_imagebutton_commentbutton);
            PostItem3_ViewCount = itemView.findViewById(R.id.postitem3_textview_viewcount);
            PostItem3_LikeSize = itemView.findViewById(R.id.postitem3_textview_likesize);
            PostItem3_Title = itemView.findViewById(R.id.postitem3_textview_title);
            PostItem3_CommentSize = itemView.findViewById(R.id.postitem3_textview_commentsize);
            PostItem3_Imagecontent = itemView.findViewById(R.id.postitem3_imageview_image);
            PostItem3_InputComment = itemView.findViewById(R.id.postitem3_edittext_comment);
            PostItem3_Relativelayout = itemView.findViewById(R.id.postitem3_relativelayout);
        }
    }/////
}/////Mypage_Post_Adapter
