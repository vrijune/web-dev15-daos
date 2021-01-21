package ictgradschool.web.jdbc.ex01;

public class Article {

    // TODO Exercise one step 1: Implement this.
    private Integer artid;
    private String title;
    private String text;

    public Article() {
    }




    public Article(Integer artid, String title , String text)    {
        this.artid = artid;
        this.title = title ;
        this.text = text ;

}


public Article(String title, String text){
        this.title = title;
        this.text = text;

}

    public Integer getArtid() {
        return artid;
    }

    public void setArtid(Integer artid) {
        this.artid = artid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artid=" + artid +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }


}