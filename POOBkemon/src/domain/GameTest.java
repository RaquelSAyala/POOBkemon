package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.util.List;
import static org.junit.Assert.*;


public class GameTest {

    private Game game;
    private Trainer trainer1;
    private Trainer trainer2;
    private Pokemon poke1;
    private Pokemon poke2;

    @BeforeEach
    public void setup() {
        Move move = new Move("Tackle", Type.NORMAL, 40, 100, 35, 0);
        poke1 = new Pokemon("Pikachu", Type.ELECTRIC, null, 100, 50, 40, 50, 40, 90, List.of(move));
        poke2 = new Pokemon("Charmander", Type.FIRE, null, 100, 50, 40, 50, 40, 65, List.of(move));

        trainer1 = new Trainer("Ash", Color.BLUE, List.of(poke1), List.of());
        trainer2 = new Trainer("Gary", Color.RED, List.of(poke2), List.of());

        game = new Game(trainer1, trainer2);
    }

    @Test
    public void testInitialTrainers() {
        assertEquals(trainer1, game.getTrainer1());
        assertEquals(trainer2, game.getTrainer2());
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());
    }

    @Test
    public void testNextTurnSwapsTrainers() {
        game.nextTurn();
        assertEquals(trainer2, game.getCurrentTrainer());
        assertEquals(trainer1, game.getWaitingTrainer());

        game.nextTurn();
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());
    }

    @Test
    public void testGameIsNotOverIfOnePokemonIsAlive() {
        poke1.takeDamage(100); // Faint trainer1's Pokémon
        assertTrue(poke1.isFainted());
        assertFalse(poke2.isFainted());

        assertFalse(game.isOver()); // Only one Pokémon is fainted
    }

    @Test
    public void testGameIsOverIfBothPokemonFainted() {
        poke1.takeDamage(200);
        poke2.takeDamage(200);

        assertTrue(poke1.isFainted());
        assertTrue(poke2.isFainted());

        assertTrue(game.isOver());
    }

    @Test
    public void testGameIsNotOverIfNoPokemonIsFainted() {
        assertFalse(poke1.isFainted());
        assertFalse(poke2.isFainted());

        assertFalse(game.isOver());
    }
}
