package project.codename.connect.Connect_DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import project.codename.connect.Activity.LoginActivity_Package.LoginActivity;
import project.codename.connect.Connect_DTO.Profile_RegisterDTO;

public class LoginDAO {
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private List<Profile_RegisterDTO> list_value;
    private List<String> list_key;
    private String key;
    private Profile_RegisterDTO dto;
    LoginActivity loginActivity;


    public LoginDAO() {
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        list_value = new ArrayList<>();
        list_key = new ArrayList<>();
        dto = new Profile_RegisterDTO();
        loginActivity = new LoginActivity();
    }

}/////LoginDAO
