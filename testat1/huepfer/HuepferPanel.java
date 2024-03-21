package huepfer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class HuepferPanel extends JPanel {

	/** initiale Dimension des Zeichen-Panels (GKOS) */
	private int width = 500;
	private int height = 500;

	/** Konstruktor für das Zeichen-Panel */
	public HuepferPanel() {
		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Inhalt des Zeichen-Panels.
	 * 
	 * @param graphics Zum Zeichnen verwendetes Graphics-Objekt.
	 */
	public void paintComponent(Graphics graphics) {
		this.setBackground(Color.BLACK);
		super.paintComponent(graphics);
		graphics.setColor(Color.WHITE);
		Huepfer h = new Huepfer(graphics, //
				width, //
				height, //
				-150.0, // xMin
				500.0, // xMax
				-150.0, // yMin
				500.0, // yMax
				366.0, // a
				0.07, // b
				50.0, // c
				1000000 // num
		);
		h.render();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Huepfer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HuepferPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
