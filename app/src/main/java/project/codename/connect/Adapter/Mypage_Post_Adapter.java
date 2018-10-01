package project.codename.connect.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.R;

public class Mypage_Post_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PostDTO> items;

    public Mypage_Post_Adapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item1, parent, false);
        return new PostDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((PostDate) holder).Postitem_title.setText(items.get(position).getTitle());


    }/////onBindViewHolder

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void additem(PostDTO dto) {
        items.add(dto);
    }

    private class PostDate extends RecyclerView.ViewHolder {

        TextView Postitem_title, Postitem_Name, Postitem_Count;
        LinearLayout Postitems_images;
        ImageView Post_ImageItem1, Post_Imageitem2, Post_Imageitem3;
        CircleImageView Postitem_Profile_Image;

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

        }
    }
}/////Mypage_Post_Adapter
