package domain;


public class Move {
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int pp;
    private int priority;

    public Move(String name, Type type, int power, int accuracy, int pp, int priority) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.priority = priority;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPP() {
        return pp;
    }

    public void use() throws PoobkemonException {
        if (pp <= 0) {
            throw new PoobkemonException("No PP left for this move!");
        }
        pp--;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}

