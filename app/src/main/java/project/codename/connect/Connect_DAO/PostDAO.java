package project.codename.connect.Connect_DAO;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import project.codename.connect.Activity.MypageActivity_Package.MypageActivity;
import project.codename.connect.Activity.MypageActivity_Package.PostActivity;
import project.codename.connect.Connect_DTO.PostDTO;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;
import project.codename.connect.Custom_Dialog.Custom_Dialog;

public class PostDAO {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage Storage;
    private List<Profile_RegisterDTO> list_value;
    private List<String> list_key;
    private StorageReference StorageRef;
    private Uri Background_File, Profile_File;
    private Uri[] Image_Urls;
    private String Name;
    UploadTask[] task;

    Uri background_Url, Profile_Url;
    List<String> Urls;
    List<String> Image_Address;
    private int index;

    public PostDAO() {

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Storage = FirebaseStorage.getInstance();
        list_value = new ArrayList<>();
        list_key = new ArrayList<>();
        Urls = new ArrayList<>();
        Image_Address = new ArrayList<>();


    }

    public void register_Post(final PostDTO dto, final Context context) {

        if (dto.getImage_Urls().size() != 0) {
            //이미지를 등록한경우
            StorageRef = Storage.getReferenceFromUrl("gs://connect-d69f9.appspot.com/");

            Urls = dto.getImage_Urls();
            Image_Urls = new Uri[Urls.size()];
            task = new UploadTask[Urls.size()];
            if (Urls != null) {

                for (int i = 0; i < Urls.size(); i++) {

                    System.out.println(i + "번째 등록중");
                    Image_Urls[i] = Uri.fromFile(new File(Urls.get(i)));
                    final StorageReference riverRef1 = StorageRef.child("관리자").child("회원관리").child("회원").child(auth.getCurrentUser().getUid()).child("이미지" + "/" + Image_Urls[i].getLastPathSegment());
                    task[i] = riverRef1.putFile(Image_Urls[i]);

                    Task<Uri> uriTask = task[i].continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return riverRef1.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Image_Address.add(task.getResult().toString());
                            dto.setImage_Address(Image_Address);


                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if (Image_Address.size() == Urls.size()) {
                                dto.setImage_Urls(null);
                                database.getReference().child("Connect").child("회원관리").child("회원정보").child(auth.getCurrentUser().getUid()).child("게시물관리").child("포스트").push().setValue(dto).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Custom_Dialog.hideLoading();
                                        Toast.makeText(context, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                        context.startActivity(new Intent(context, MypageActivity.class));
                                        ((PostActivity) context).finish();

                                    }
                                });
                            }
                        }
                    });
                }/////for
            }
        }else{
            //이미지를 등록 안한경우
            database.getReference().child("Connect").child("회원관리").child("회원정보").child(auth.getCurrentUser().getUid()).child("게시물관리").child("포스트").push().setValue(dto).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Custom_Dialog.hideLoading();
                    context.startActivity(new Intent(context, MypageActivity.class));
                    ((PostActivity) context).finish();

                }
            });
        }

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
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}/////PostDAO
