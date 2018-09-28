package project.codename.connect.Connect_DAO;

import android.net.Uri;
import android.support.annotation.NonNull;

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

    public PostDAO(){

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Storage = FirebaseStorage.getInstance();
        list_value = new ArrayList<>();
        list_key = new ArrayList<>();
    }

    public void register_Post(PostDTO dto){




    }/////register_Post
}/////PostDAO
