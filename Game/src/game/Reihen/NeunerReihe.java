package game.Reihen;

public class NeunerReihe extends Reihe{

    public String help;

    public NeunerReihe(int rechenart) {
        super(rechenart);
        this.reihe = 9;
    }

    public String Aufgabe1() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[0] * reihe) + help + reihe;
        }
        return " " + aufgaben[0]+ " " + help + " "+ reihe;
    }

    public String Aufgabe2() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[1] * reihe) + help + reihe;
        }
        return " " + aufgaben[1] + " "+ help + " "+ reihe;
    }

    public String Aufgabe3() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[2] * reihe) + help + reihe;
        }
        return " " + aufgaben[2] + " "+ help + " "+ reihe;
    }

    public String Aufgabe4() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[3] * reihe) + help + reihe;
        }
        return " " + aufgaben[3]+ " "+ help + " "+ reihe;
    }

    public String Aufgabe5() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[4] * reihe) + help + reihe;
        }
        return " " + aufgaben[4] + " "+ help + " "+ reihe;
    }

    public String Aufgabe6() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[5] * reihe) + help + reihe;
        }
        return " " + aufgaben[5] + " "+ help + " "+ reihe;
    }

    public String Aufgabe7() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[6] * reihe) + help + reihe;
        }
        return " " + aufgaben[6] + " " + help + " " + reihe;
    }

    public String Aufgabe8() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[7] * reihe) + help + reihe;
        }
        return " " + aufgaben[7]+ " " + help + " "+ reihe;
    }

    public String Aufgabe9() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[8] * reihe) + help + reihe;
        }
        return " " + aufgaben[8] + " " + help + " " +reihe;
    }

    public String Aufgabe10() {
        if (rechenart == 0) help = "+";
        if (rechenart == 1) help = "*";
        if (rechenart == 2) help = "-";
        if (rechenart == 3) {
            help = "/";
            return " " + (aufgaben[9] * reihe) + help + reihe;
        }
        return " " + aufgaben[9] + " " + help + " " +  reihe;
    }
}