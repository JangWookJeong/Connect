package project.codename.connect.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import project.codename.connect.R;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1001;
    private Button Signin_Button, Signup_Button;
    private int PermissionCheck;
    private SignInButton Google_LoginButton;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private EditText UserEmail, UserPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        permissioncheck();
        createcomponent();
        addlistener();
    }/////

    private void permissioncheck() {
        /*
            권한 체크
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        PermissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

    }/////permissioncheck

    private void createcomponent() {
        /*
            컴포넌트 생성 및 부착
         */
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        UserEmail = findViewById(R.id.loginactivity_edittext_useremail);
        UserPassword = findViewById(R.id.loginactivity_edittext_userpassword);


        Google_LoginButton = findViewById(R.id.loginactivity_signin_button);
        Signin_Button = findViewById(R.id.loginactivity_button_signin);
        Signup_Button = findViewById(R.id.loginactivity_button_signup);

    }/////

    private void addlistener() {
        /*
            컴포넌트 리스너 부착
         */

        Google_LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Google_Login_Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(Google_Login_Intent, RC_SIGN_IN);
            }/////onclick
        });/////Google_LoginButton

        Signin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_Login(UserEmail.getText().toString(), UserPassword.getText().toString());

            }
        });/////Login_Button

        Signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입으로 이동
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                finish();
            }
        });/////Joining_Button

    }/////addlistener

    private void start_Login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {

                        }
                    }
                });
    }/////start_Login

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {

            }
        }/////
    }/////onActivityResult

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //구글 로그인 성공
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            //구글로그인 실패
                            Toast.makeText(getApplicationContext(), "구글 로그인 실패", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }/////firebaseAuthWithGoogle

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }/////onConnectionFailed
}/////
