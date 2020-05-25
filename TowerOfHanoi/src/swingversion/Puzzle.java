package swingversion;

import javax.swing.*;

public class Puzzle extends JFrame {
	private Tower tower;

	public Puzzle(String title) {
		setTitle(title);
		build();
	}

	private void build() {
		JMenu gameMenu = new JMenu("Game");

		JMenuItem newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(event -> {
			Object values[] = { 2, 3, 4, 5, 6, 7, 8, 9 };
			Object val = JOptionPane.showInputDialog(this, "No. Of Disks : ", "Input", JOptionPane.INFORMATION_MESSAGE,
					null, values, values[0]);

			if ((int) val != JOptionPane.CANCEL_OPTION) {
				tower.init((int) val);
			}
		});

		JMenuItem bestTimeItem = new JMenuItem("Best Time");

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(event -> {
			System.exit(0);
		});

		gameMenu.add(newGameItem);
		gameMenu.add(bestTimeItem);
		gameMenu.add(exitItem);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new JMenuItem("About..."));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

		tower = new Tower();
		add(tower);

		setSize(660, 280);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle("Tower Of Hanoi");
		puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		puzzle.setVisible(true);
	}
}
