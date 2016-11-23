package comsitejustdoitwhistle.google.sites.monermoney;


public class Income {
    double money;
    String date, note;
    public Income(double money, String date, String note) {
        this.money = money;
        this.date = date;
        this.note = note;
    }
    public double getMoney() { return this.money; }
    public String getDate() { return this.date; }
    public String getNote() { return this.note; }
}
