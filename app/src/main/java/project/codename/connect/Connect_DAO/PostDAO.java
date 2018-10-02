package project.codename.connect.Connect_DAO;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Activity.MypageActivity_Package.PostActivity;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;

public class PostDAO {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage Storage;
    private List<Profile_RegisterDTO> list_value;
    private List<String> list_key;
    private StorageReference StorageRef;
    private Uri Background_File, Profile_File;
    private String Name;
    Uri background_Url, Profile_Url;

    public PostDAO() {

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Storage = FirebaseStorage.getInstance();
        list_value = new ArrayList<>();
        list_key = new ArrayList<>();
    }

    public void register_Post(PostDTO dto, final Context context) {

        database.getReference().child("Connect").child("회원관리").child("회원정보").child(auth.getCurrentUser().getUid()).child("게시물관리").child("포스트").push().setValue(dto).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }/////register_Post

    public void Call_User_Profile(final PostActivity.onPost_GetuserInfo post_getInfo) {

        database.getReference().child("Connect").child("회원관리").child("회원정보").child(auth.getCurrentUser().getUid()).child("기본프로필").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                post_getInfo.getuserInfo(dataSnapshot.getValue(Profile_RegisterDTO.class));
                PostActivity mc = new PostActivity();
                mc.setPost_getInfo((PostActivity.onPost_GetuserInfo) post_getInfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }


}/////PostDAO
