package domain;

import java.util.ArrayList;
import java.util.List;

public class Blastoise implements Pokemon {
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
    private boolean cannonsPowered;  // Estado de los cañones
    
    public Blastoise() {
        this.name = "Blastoise";
        this.primaryType = Type.WATER;
        this.secondaryType = null;
        this.maxHP = 362;
        this.currentHP = 362;
        this.attack = 291;
        this.defense = 328;
        this.specialAttack = 295;
        this.specialDefense = 339;
        this.speed = 280;
        this.cannonsPowered = true;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Hidrobomba", Type.WATER, 110, 80, MoveCategory.SPECIAL));
        moves.add(new Move("Rayo Hielo", Type.ICE, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Cabezazo", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Puño Certero", Type.FIGHTING, 150, 100, MoveCategory.PHYSICAL));
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
        currentHP = Math.max(0, currentHP - amount);
        
        // Si los PS bajan demasiado, la potencia de los cañones disminuye
        if (currentHP < maxHP * 0.2) {
            cannonsPowered = false;
            System.out.println("¡Los cañones de Blastoise pierden presión!");
        }
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera lo suficiente, los cañones recuperan potencia
        if (currentHP > maxHP * 0.5) {
            cannonsPowered = true;
            System.out.println("¡Los cañones de Blastoise recuperan su presión normal!");
        }
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Blastoise
    public boolean areCannonsPowered() {
        return cannonsPowered;
    }
    
    public void shellDefense() {
        // Incrementa temporalmente la defensa
        this.defense = (int)(this.defense * 1.5);
        System.out.println("¡Blastoise se refugia en su caparazón! Su defensa aumenta considerablemente.");
    }
    
    public void hydroCannon() {
        if (cannonsPowered) {
            // Disparo concentrado que aumenta el poder de ataque de agua
            for (Move move : moves) {
                if (move.getType() == Type.WATER) {
                    move.setPower((int)(move.getPower() * 1.3));
                }
            }
            System.out.println("¡Blastoise prepara sus cañones para un disparo concentrado!");
        } else {
            System.out.println("Los cañones de Blastoise no tienen suficiente presión para un disparo concentrado.");
        }
    }
}