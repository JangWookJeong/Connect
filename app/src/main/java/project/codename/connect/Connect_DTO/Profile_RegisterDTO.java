package project.codename.connect.Connect_DTO;

public class Profile_RegisterDTO {
     /*
        회원가입시 작성하는 프로필 등록 DTO
     */

    private String Profile_Background_Image;
    private String Profile_Image;
    private String NickName;
    private String Name;
    private String Gender;
    private String Birthday;
    private Boolean Profile_isRegister = false;
    private int Krw = 0;

    public void setKrw(int krw) {
        Krw = krw;
    }

    public int getKrw() {

        return Krw;
    }

    public String getProfile_Background_Image() {
        return Profile_Background_Image;
    }

    public String getProfile_Image() {
        return Profile_Image;
    }

    public String getNickName() {
        return NickName;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public Boolean getProfile_isRegister() {
        return Profile_isRegister;
    }

    public void setProfile_Background_Image(String profile_Background_Image) {
        Profile_Background_Image = profile_Background_Image;
    }

    public void setProfile_Image(String profile_Image) {
        Profile_Image = profile_Image;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setProfile_isRegister(Boolean profile_isRegister) {
        Profile_isRegister = profile_isRegister;
    }
}/////Profile_RegisterDTO
