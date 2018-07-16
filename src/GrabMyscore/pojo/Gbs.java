package GrabMyscore.pojo;

public class Gbs {

    private String tname;
    private String bookname;
    private String status;

    public Gbs(String tname, String bookname, String status)
    {

        this.tname =tname;
        this.bookname=bookname;
        this.status=status;

    }
    public Gbs(){

    }

    public String getTname() {
        return tname;
    }
    public String getBookname() {
        return bookname;
    }

    public String getStatus() {
        return status;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
