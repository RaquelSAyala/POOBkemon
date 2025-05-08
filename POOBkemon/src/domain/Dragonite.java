package domain;

import java.util.ArrayList;
import java.util.List;

public class Dragonite implements Pokemon {
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
    private boolean flyingMode;  // Estado de vuelo
    
    public Dragonite() {
        this.name = "Dragonite";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 386;
        this.currentHP = 386;
        this.attack = 403;
        this.defense = 317;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flyingMode = false;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Hiperrayo", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Cometa Draco", Type.DRAGON, 130, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Vuelo", Type.FLYING, 90, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Puño Trueno", Type.ELECTRIC, 75, 100, MoveCategory.PHYSICAL));
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
        // Si está en modo vuelo, esquiva algunos ataques
        if (flyingMode && Math.random() > 0.7) {
            System.out.println("¡Dragonite evade el ataque gracias a su vuelo!");
            return;
        }
        
        currentHP = Math.max(0, currentHP - amount);
        
        // Si recibe mucho daño, sale del modo vuelo
        if (flyingMode && amount > maxHP * 0.2) {
            flyingMode = false;
            System.out.println("¡Dragonite pierde su estabilidad aérea y desciende!");
        }
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Dragonite
    public boolean isFlying() {
        return flyingMode;
    }
    
    public void takeFlightMode() {
        if (!flyingMode && currentHP > maxHP * 0.3) {
            flyingMode = true;
            this.speed = (int)(this.speed * 1.5);
            System.out.println("¡Dragonite levanta el vuelo! Su velocidad aumenta considerablemente.");
        } else if (flyingMode) {
            System.out.println("Dragonite ya está volando.");
        } else {
            System.out.println("Dragonite no tiene suficiente energía para volar.");
        }
    }
    
    public void dragonDance() {
        // Movimiento de danza que aumenta estadísticas
        this.attack = (int)(this.attack * 1.2);
        this.speed = (int)(this.speed * 1.2);
        System.out.println("¡Dragonite realiza una Danza Dragón! Su ataque y velocidad aumentan.");
    }
    
    public void rescueOperation() {
        // Simula la capacidad de Dragonite para rescatar a náufragos
        System.out.println("Dragonite busca a personas en peligro para rescatarlas.");
        // Este método podría tener diferentes efectos en un sistema de juego real
    }
}