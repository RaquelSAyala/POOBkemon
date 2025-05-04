package domain;

public class Item {
    private String name;
    private String effect; // "heal20", "heal50", "heal200", "revive"

    public Item(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public void apply(Pokemon target) throws PoobkemonException {
        if (effect.equals("revive")) {
            if (!target.isFainted()) {
                throw new PoobkemonException("You can only use Revive on fainted Pokémon!");
            }
            target.heal(target.getCurrentHP() / 2);
        } else if (effect.startsWith("heal")) {
            int healAmount = Integer.parseInt(effect.substring(4));
            if (target.isFainted()) {
                throw new PoobkemonException("Cannot heal a fainted Pokémon!");
            }
            target.heal(healAmount);
        }
    }

    public String getName() {
        return name;
    }
}
