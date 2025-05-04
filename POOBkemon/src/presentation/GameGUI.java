package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameGUI extends JFrame {
    private Game game;
    private JPanel battlePanel;
    private JPanel infoPanel;
    private JPanel movesPanel;
    private JLabel trainer1Label;
    private JLabel trainer2Label;
    private JLabel pokemon1Label;
    private JLabel pokemon2Label;
    private JComboBox<String> movesCombo;
    private JButton attackButton;
    private JButton changeButton;
    private JButton itemButton;
    private JButton nextTurnButton;

    private String mode; // "PvsP", "PvsM" o "MvsM"

    public GameGUI(Game game, String mode) {
        this.game = game;
        this.mode = mode;
        setTitle("POOBkemon Battle");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        updateScreen();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Battle Area
        battlePanel = new JPanel(new GridLayout(1, 2));
        trainer1Label = new JLabel("", SwingConstants.CENTER);
        trainer2Label = new JLabel("", SwingConstants.CENTER);
        trainer1Label.setOpaque(true);
        trainer2Label.setOpaque(true);
        pokemon1Label = new JLabel("", SwingConstants.CENTER);
        pokemon2Label = new JLabel("", SwingConstants.CENTER);
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(trainer1Label, BorderLayout.NORTH);
        leftPanel.add(pokemon1Label, BorderLayout.CENTER);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(trainer2Label, BorderLayout.NORTH);
        rightPanel.add(pokemon2Label, BorderLayout.CENTER);
        battlePanel.add(leftPanel);
        battlePanel.add(rightPanel);
        add(battlePanel, BorderLayout.CENTER);

        // Info Panel
        infoPanel = new JPanel();
        add(infoPanel, BorderLayout.NORTH);

        // Moves Panel
        movesPanel = new JPanel();
        movesCombo = new JComboBox<>();
        attackButton = new JButton("Attack");
        changeButton = new JButton("Change Pokémon");
        itemButton = new JButton("Use Item");
        nextTurnButton = new JButton("Next Turn (Skip)");
        movesPanel.add(movesCombo);
        movesPanel.add(attackButton);
        movesPanel.add(changeButton);
        movesPanel.add(itemButton);
        movesPanel.add(nextTurnButton);
        add(movesPanel, BorderLayout.SOUTH);

        // Listeners
        attackButton.addActionListener(e -> attack());
        changeButton.addActionListener(e -> changePokemon());
        itemButton.addActionListener(e -> useItem());
        nextTurnButton.addActionListener(e -> nextTurn());
    }

    private void updateScreen() {
        Trainer current = game.getCurrentTrainer();
        Trainer waiting = game.getWaitingTrainer();

        trainer1Label.setText(game.getCurrentTrainer() == game.getTrainer1() ? "▶ " + game.getTrainer1().getName() : game.getTrainer1().getName());
        trainer2Label.setText(game.getCurrentTrainer() == game.getTrainer2() ? "▶ " + game.getTrainer2().getName() : game.getTrainer2().getName());

        // Fondos de colores
        trainer1Label.setBackground(game.getTrainer1().getColor());
        trainer2Label.setBackground(game.getTrainer2().getColor());

        // Mostrar pokémon y vida
        pokemon1Label.setText(game.getTrainer1().getCurrentPokemon().getName() + " HP: " + game.getTrainer1().getCurrentPokemon().getCurrentHP());
        pokemon2Label.setText(game.getTrainer2().getCurrentPokemon().getName() + " HP: " + game.getTrainer2().getCurrentPokemon().getCurrentHP());

        // Mostrar movimientos y PP
        movesCombo.removeAllItems();
        List<Move> moves = current.getCurrentPokemon().getMoves();
        for (Move move : moves) {
            movesCombo.addItem(move.getName() + " (PP: " + move.getPP() + ")");
        }

        // Si es Machine vs Machine, hacer movimientos automáticos
        if (mode.equals("MvsM") || (mode.equals("PvsM") && current == game.getTrainer2())) {
            Timer timer = new Timer(1000, e -> {
                randomAction();
                ((Timer) e.getSource()).stop();
            });
            timer.start();
        }
    }

    private void attack() {
        try {
            Trainer current = game.getCurrentTrainer();
            Trainer opponent = game.getWaitingTrainer();
            int index = movesCombo.getSelectedIndex();
            Move move = current.getCurrentPokemon().getMoves().get(index);
            move.use();
            opponent.getCurrentPokemon().takeDamage(move.getPower()); // Simplificación: daño = potencia
            animateAttack();
            checkEnd();
            game.nextTurn();
            updateScreen();
        } catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void animateAttack() {
        JLabel label = game.getCurrentTrainer() == game.getTrainer1() ? pokemon2Label : pokemon1Label;
        Color old = label.getForeground();
        label.setForeground(Color.RED);
        Timer timer = new Timer(500, e -> {
            label.setForeground(old);
            ((Timer) e.getSource()).stop();
        });
        timer.start();
    }

    private void changePokemon() {
        // Simplificado: Cambia al siguiente pokémon disponible
        try {
            Trainer current = game.getCurrentTrainer();
            for (int i = 0; i < current.getTeam().size(); i++) {
                if (!current.getTeam().get(i).isFainted() && current.getCurrentPokemon() != current.getTeam().get(i)) {
                    current.switchPokemon(i);
                    break;
                }
            }
            game.nextTurn();
            updateScreen();
        } catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void useItem() {
        try {
            Trainer current = game.getCurrentTrainer();
            if (!current.getItems().isEmpty()) {
                current.useItem(current.getItems().get(0));
                JOptionPane.showMessageDialog(this, "Item used successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No items available!");
            }
            game.nextTurn();
            updateScreen();
        } catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nextTurn() {
        game.nextTurn();
        updateScreen();
    }

    private void randomAction() {
        Random random = new Random();
        int action = random.nextInt(3);
        if (action == 0) {
            attack();
        } else if (action == 1) {
            changePokemon();
        } else {
            useItem();
        }
    }

    private void checkEnd() {
        if (game.isOver()) {
            JOptionPane.showMessageDialog(this, "Game Over!");
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        // Pedir modalidad de juego
        String[] options = {"PvsP", "PvsM", "MvsM"};
        String mode = (String) JOptionPane.showInputDialog(null, "Seleccione el modo de juego", "POOBkemon",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (mode == null) {
            System.exit(0); // Si no elige nada, salir
        }

        // Crear Pokémon básicos
        List<Move> moves1 = new ArrayList<>();
        moves1.add(new Move("Flamethrower", domain.Type.FIRE, 90, 100, 15, 0));
        moves1.add(new Move("Scratch", domain.Type.FIRE, 40, 100, 35, 0));
        Pokemon charizard = new Pokemon("Charizard", domain.Type.FIRE, domain.Type.FLYING, 360, 293, 280, 348, 295, 328, moves1);

        List<Move> moves2 = new ArrayList<>();
        moves2.add(new Move("Surf", domain.Type.WATER, 90, 100, 15, 0));
        moves2.add(new Move("Tackle", domain.Type.WATER, 40, 100, 35, 0));
        Pokemon blastoise = new Pokemon("Blastoise", domain.Type.WATER, null, 362, 291, 328, 295, 339, 280, moves2);

        // Crear Ítems básicos
        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("Potion", "heal20"));
        items1.add(new Item("SuperPotion", "heal50"));

        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("HyperPotion", "heal200"));
        items2.add(new Item("Revive", "revive"));

        // Crear entrenadores
        Trainer trainer1 = new Trainer("Ash", Color.BLUE, List.of(charizard), items1);
        Trainer trainer2 = new Trainer("Misty", Color.RED, List.of(blastoise), items2);

        // Crear el juego
        Game game = new Game(trainer1, trainer2);

        // Crear la GUI
        SwingUtilities.invokeLater(() -> new GameGUI(game, mode));
    }
}
