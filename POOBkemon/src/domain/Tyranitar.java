package domain;

import java.util.ArrayList;
import java.util.List;

public class Tyranitar implements Pokemon {
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
    private boolean sandstreamActive;  // Estado de tormenta de arena
    
    public Tyranitar() {
        this.name = "Tyranitar";
        this.primaryType = Type.ROCK;
        this.secondaryType = Type.DARK;
        this.maxHP = 404;
        this.currentHP = 404;
        this.attack = 403;
        this.defense = 350;
        this.specialAttack = 317;
        this.specialDefense = 328;
        this.speed = 243;
        this.sandstreamActive = false;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Terremoto", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Avalancha", Type.ROCK, 75, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Triturar", Type.DARK, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Hiperrayo", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
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
        // La armadura rocosa de Tyranitar reduce el daño recibido
        int reducedAmount = (int)(amount * 0.9);
        currentHP = Math.max(0, currentHP - reducedAmount);
        
        // Si recibe mucho daño, puede desatar una tormenta de arena
        if (!sandstreamActive && amount > maxHP * 0.15) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡El ataque enfurece a Tyranitar y desata una tormenta de arena!");
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
    
    // Métodos específicos de Tyranitar
    public boolean isSandstreamActive() {
        return sandstreamActive;
    }
    
    public void activateSandstream() {
        if (!sandstreamActive) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡Tyranitar desata una violenta tormenta de arena! Su defensa aumenta.");
        } else {
            System.out.println("La tormenta de arena ya está activa.");
        }
    }
    
    public void mountainCrusher() {
        // Ataque que puede cambiar el terreno
        System.out.println("¡Tyranitar golpea el suelo con toda su fuerza, modificando el terreno!");
        this.attack = (int)(this.attack * 1.3);
        
        // En un juego real, esto podría afectar el campo de batalla
        if (sandstreamActive) {
            System.out.println("¡La combinación con la tormenta de arena hace que el impacto sea devastador!");
        }
    }
}