package comsitejustdoitwhistle.google.sites.monermoney;

/**
 * Created by Kenn on 11/18/2016.
 */

public class Outcome {
    double money;
    String date, note;
    public Outcome(double money, String date, String note) {
        this.money = money;
        this.date = date;
        this.note = note;
    }
    public double getMoney() { return this.money; }
    public String getDate() { return this.date; }
    public String getNote() { return this.note; }
}
