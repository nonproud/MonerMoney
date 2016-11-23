package comsitejustdoitwhistle.google.sites.monermoney;

/**
 * Created by Kenn on 11/18/2016.
 */

public class Outcome {
    int money;
    String date, note;
    public Outcome(int money, String date, String note) {
        this.money = money;
        this.date = date;
        this.note = note;
    }
    public int getMoney() { return this.money; }
    public String getDate() { return this.date; }
    public String getNote() { return this.note; }
}
