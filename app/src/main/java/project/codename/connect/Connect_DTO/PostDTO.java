package project.codename.connect.Connect_DTO;

import android.net.Uri;

import java.util.List;

public class PostDTO {
    //입력받은 포스트 데이터를 저장하는 DTO
    private String Post;
    private String title;
    private String Tag;
    private String Reguster_date;
    private String Name;
    private String Profile_Image_Url;
    private int Image_Size;
    private List<String> image_Urls;
    private List<String> Image_Address;

    public List<String> getImage_Address() {
        return Image_Address;
    }

    public void setImage_Address(List<String> image_Address) {
        Image_Address = image_Address;
    }

    public List<String> getImage_Urls() {
        return image_Urls;
    }

    public void setImage_Urls(List<String> image_Urls) {
        this.image_Urls = image_Urls;
    }

    public int getImage_Size() {
        return Image_Size;
    }

    public void setImage_Size(int image_Size) {
        Image_Size = image_Size;
    }

    public String getReguster_date() {
        return Reguster_date;
    }

    public String getName() {
        return Name;
    }

    public String getProfile_Image_Url() {
        return Profile_Image_Url;
    }

    public void setReguster_date(String reguster_date) {
        Reguster_date = reguster_date;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setProfile_Image_Url(String profile_Image_Url) {
        Profile_Image_Url = profile_Image_Url;
    }

    public String getDate() {
        return Reguster_date;
    }

    public void setDate(String date) {
        this.Reguster_date = date;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getPost() {
        return Post;
    }

    public String getTitle() {
        return title;
    }

    public void setPost(String post) {
        Post = post;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}/////
