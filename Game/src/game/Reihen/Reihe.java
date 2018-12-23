package game.Reihen;

public abstract class Reihe {

    public int rechenart;
    public int reihe;
    public int[] aufgaben;

    public Reihe(int rechenart) {
        aufgaben = new int[] {1,2,3,4,5,6,7,8,9,10};
        this.rechenart = rechenart;
    }

    public abstract String Aufgabe1();
    public abstract String Aufgabe2();
    public abstract String Aufgabe3();
    public abstract String Aufgabe4();
    public abstract String Aufgabe5();
    public abstract String Aufgabe6();
    public abstract String Aufgabe7();
    public abstract String Aufgabe8();
    public abstract String Aufgabe9();
    public abstract String Aufgabe10();




}
