package domain;

import java.util.ArrayList;
import java.util.List;

public class Gengar implements Pokemon {
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
    private boolean shadowForm;  // Estado de forma sombra
    
    public Gengar() {
        this.name = "Gengar";
        this.primaryType = Type.GHOST;
        this.secondaryType = Type.POISON;
        this.maxHP = 324;
        this.currentHP = 324;
        this.attack = 251;
        this.defense = 240;
        this.specialAttack = 394;
        this.specialDefense = 273;
        this.speed = 350;
        this.shadowForm = false;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Bola Sombra", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Puño Sombra", Type.GHOST, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Psíquico", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hipnosis", Type.PSYCHIC, 0, 60, MoveCategory.STATUS));
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Type getPrimaryType() {
        return primaryType;
    }
    
    @Override
    public Type getSecondaryType() {
        return secondaryType;
    }
    
    @Override
    public int getMaxHP() {
        return maxHP;
    }
    
    @Override
    public int getCurrentHP() {
        return currentHP;
    }
    
    @Override
    public int getAttack() {
        return attack;
    }
    
    @Override
    public int getDefense() {
        return defense;
    }
    
    @Override
    public int getSpecialAttack() {
        return specialAttack;
    }
    
    @Override
    public int getSpecialDefense() {
        return specialDefense;
    }
    
    @Override
    public int getSpeed() {
        return speed;
    }
    
    @Override
    public List<Move> getMoves() {
        return moves;
    }
    
    @Override
    public void takeDamage(int amount) {
        // Si está en forma sombra, recibe menos daño de ataques físicos
        if (shadowForm && amount > 0) {
            amount = (int)(amount * 0.7);
            System.out.println("¡La forma sombra de Gengar reduce el daño recibido!");
        }
        
        currentHP = Math.max(0, currentHP - amount);
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera completamente, puede volver a usar su forma sombra
        if (currentHP == maxHP) {
            shadowForm = false;
        }
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Gengar
    public boolean isInShadowForm() {
        return shadowForm;
    }
    
    public void enterShadowForm() {
        if (!shadowForm && currentHP > maxHP * 0.3) {
            shadowForm = true;
            this.speed = (int)(this.speed * 1.2);
            this.specialAttack = (int)(this.specialAttack * 1.1);
            System.out.println("¡Gengar se sumerge en las sombras! Su velocidad y ataque especial aumentan.");
        } else {
            System.out.println("Gengar no tiene suficiente energía para entrar en forma sombra.");
        }
    }
    
    public void stealSoul() {
        // Ataque especial que drena vida del oponente
        System.out.println("¡Gengar intenta robar el alma de su oponente!");
        
        // Aumenta temporalmente su ataque especial
        this.specialAttack = (int)(this.specialAttack * 1.3);
        
        // Este método simula un efecto, en un sistema real recuperaría PS del oponente
        int healAmount = maxHP / 5;
        heal(healAmount);
        System.out.println("¡Gengar recupera " + healAmount + " PS!");
    }
}