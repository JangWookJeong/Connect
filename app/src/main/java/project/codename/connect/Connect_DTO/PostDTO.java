package project.codename.connect.Connect_DTO;

public class PostDTO {
    //입력받은 포스트 데이터를 저장하는 DTO
    private String Post;
    private String title;
    private String Tag;
    private String Reguster_date;

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
