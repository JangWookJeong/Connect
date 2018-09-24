package project.codename.connect.Connect_DAO;

import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

import project.codename.connect.Connect_DTO.Profile_RegisterDTO;

public class MypageDAO {
    private FirebaseAuth auth;
    private FirebaseDatabase Database;
    private FirebaseStorage Storage;
    private List<Profile_RegisterDTO> list_value;
    private List<String> list_key;
    private StorageReference StorageRef;
    private Uri Background_File, Profile_File;
    private String Name;

    public MypageDAO() {
        auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance();
        Storage = FirebaseStorage.getInstance();
        list_value = new ArrayList<>();
        list_key = new ArrayList<>();
    }///constructor

    public void Register_Profile(final Profile_RegisterDTO dto) {
        //프로필 데이터베이스 등록

        StorageRef = Storage.getReferenceFromUrl("gs://connect-d69f9.appspot.com/");
        Background_File = Uri.fromFile(new File(dto.getProfile_Background_Image()));
        Profile_File = Uri.fromFile(new File(dto.getProfile_Image()));

        final StorageReference riverRef1 = StorageRef.child("관리자").child("회원관리").child("회원").child(dto.getName() + "/" + Background_File.getLastPathSegment());
        final StorageReference riverRef2 = StorageRef.child("관리자").child("회원관리").child("회원").child(dto.getName() + "/" + Profile_File.getLastPathSegment());

        final UploadTask task1 = riverRef1.putFile(Background_File);
        final UploadTask task2 = riverRef2.putFile(Profile_File);

        task1.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                task2.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dto.setProfile_Background_Image(riverRef1.getDownloadUrl().toString());
                        dto.setProfile_Image(riverRef2.getDownloadUrl().toString());
                        dto.setProfile_isRegister(true);
                        Database.getReference().child("Connect").child("관리자").child("회원관리").child("회원정보").child(auth.getCurrentUser().getUid()).child(dto.getName()).setValue(dto).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });
    }/////Register_Profile

    public void Call_User_Profile() {

    }/////Call_User_Profile

}///MypageDAO
