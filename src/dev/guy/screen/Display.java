package dev.guy.screen;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Display extends JFrame {
	JFrame window;
	Canvas canvas;
	public Display(String title, int w, int h) {
		window = new JFrame(title);
		window.setSize(w, h);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		canvas = new Canvas();
//		canvas.setPreferredSize(new Dimension(w,h));
//		canvas.setMinimumSize(new Dimension(w,h));
//		canvas.setMaximumSize(new Dimension(w,h));
		canvas.setSize(window.getSize());
		window.add(canvas);
		window.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
