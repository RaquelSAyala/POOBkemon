package domain;
import java.util.List;

public class Pokemon {
    private String name;
    private Type primaryType;
    private Type secondaryType;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private List<Move> moves;

    public Pokemon(String name, Type primaryType, Type secondaryType, int maxHP, int attack, int defense, int specialAttack, int specialDefense, int speed, List<Move> moves) {
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.moves = moves;
    }

    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
    }

    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }

    public boolean isFainted() {
        return currentHP == 0;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }
}
