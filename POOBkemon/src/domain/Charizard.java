package domain;

import java.util.ArrayList;
import java.util.List;

public class Charizard implements Pokemon {
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
    private boolean flameLit;  // Estado de la llama de la cola
    
    public Charizard() {
        this.name = "Charizard";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 360;
        this.currentHP = 360;
        this.attack = 293;
        this.defense = 280;
        this.specialAttack = 348;
        this.specialDefense = 295;
        this.speed = 328;
        this.flameLit = true;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Lanzallamas", Type.FIRE, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Garra Dragón", Type.DRAGON, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Vuelo", Type.FLYING, 90, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Terremoto", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
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
        
        // Si los PS bajan demasiado, la llama de la cola podría debilitarse
        if (currentHP < maxHP * 0.1) {
            flameLit = false;
        }
        
        // Si la llama se apaga y los PS llegan a 0, Charizard no puede revivir
        if (!flameLit && currentHP == 0) {
            System.out.println("¡La llama de Charizard se ha apagado! No puede ser revivido.");
        }
    }
    
    @Override
    public void heal(int amount) {
        if (!flameLit && currentHP == 0) {
            System.out.println("No se puede revivir a Charizard, su llama se ha apagado.");
            return;
        }
        
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera lo suficiente, la llama vuelve a encenderse
        if (currentHP > maxHP * 0.3) {
            flameLit = true;
        }
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Charizard
    public boolean isFlameActive() {
        return flameLit;
    }
    
    public void powerUpFireMoves() {
        // Si ha participado en duros combates, sus ataques de fuego se fortalecen
        for (Move move : moves) {
            if (move.getType() == Type.FIRE) {
                // Incrementa el poder de los movimientos de fuego
                move.setPower((int)(move.getPower() * 1.2));
            }
        }
    }
    
    public void dragonRage() {
        // Habilidad especial que incrementa temporalmente el ataque
        this.attack = (int)(this.attack * 1.5);
        System.out.println("¡Charizard entra en estado de furia! Su ataque aumenta considerablemente.");
    }
}