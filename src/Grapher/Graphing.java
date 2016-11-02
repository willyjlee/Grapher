package Grapher;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.Comparable;
import java.util.Scanner;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.lang.Object.*;
import java.lang.Integer.*;
import java.util.*;
import javax.script.*;

/**
 * Class Graphing - write a description of the class here
 * 
 * @author William Lee
 * @version (1)
 */
public class Graphing extends Applet implements KeyListener {
	// instance variables - replace the example below with your own
	private GraphingCalculator bob;
	private int num;
	/** num is private that represent key codes for this applet */
	public static final double ratio = 4000.0;
	private static String newD, newGraph;
	private static Color c;

	/**
	 * Called by the browser or applet viewer to inform this JApplet that it has
	 * been loaded into the system. It is always called before the first time
	 * that the start method is called.
	 */
	public void init() {
		// this is a workaround for a security conflict with some browsers
		// including some versions of Netscape & Internet Explorer which do
		// not allow access to the AWT system event queue which JApplets do
		// on startup to check access. May not be necessary with your browser.
		// provide any code required to run each time
		// web page is visited
		this.setBackground(Color.black);
		double minx = -getWidth() / 2.0;// Double.parseDouble(JOptionPane.showInputDialog(null,"Please the give the value for minimum x","min x",JOptionPane.PLAIN_MESSAGE));//
		double maxx = getWidth() / 2.0;// Double.parseDouble(JOptionPane.showInputDialog(null,"Please the give the value for maximum x","max x",JOptionPane.PLAIN_MESSAGE));//
		double miny = -getHeight() / 2.0;// Double.parseDouble(JOptionPane.showInputDialog(null,"Please the give the value for minimum y","min y",JOptionPane.PLAIN_MESSAGE));//
		double maxy = getHeight() / 2.0;// Double.parseDouble(JOptionPane.showInputDialog(null,"Please the give the value for maximum y","max y",JOptionPane.PLAIN_MESSAGE));//
		double inc = getWidth() / ratio;
		num = -1;
		JOptionPane.showMessageDialog(null,
				"Welcome to Graph-o-matico, by William Lee");
		addKeyListener(this);
		bob = new GraphingCalculator(inc, maxy, miny, maxx, minx, 1.0, 1.0);// /**option
																			// 0
																			// is
																			// linear
																			// and
																			// has
																			// 1
																			// root
																			// at
																			// most,
																			// etc.*/
		newD = "Press \"d\" for changing dimensions!!!";
		newGraph = "Press \"n\" for new graphs!!!";
		//
		// //take input a,b,c,d in combinations;
		// //construct graphing calculator
		// //call caculator to graph based on input;
	}

	/**
	 * Called by the browser or applet viewer to inform this JApplet that it
	 * should start its execution. It is called after the init method and each
	 * time the JApplet is revisited in a Web page.
	 */
	public void start() {

	}

	/**
	 * Called by the browser or applet viewer to inform this JApplet that it
	 * should stop its execution. It is called when the Web page that contains
	 * this JApplet has been replaced by another page, and also just before the
	 * JApplet is to be destroyed.
	 */
	public void stop() {
		// provide any code that needs to be run when page
		// is replaced by another page or before JApplet is destroyed
	}

	public void newDimensions() {
		bob.adjustminX(Double.parseDouble(JOptionPane.showInputDialog(null,
				"Please give minimum x", JOptionPane.PLAIN_MESSAGE)));
		bob.adjustmaxX(Double.parseDouble(JOptionPane.showInputDialog(null,
				"Please give maximum x", JOptionPane.PLAIN_MESSAGE)));
		bob.adjustminY(Double.parseDouble(JOptionPane.showInputDialog(null,
				"Please give minimum y", JOptionPane.PLAIN_MESSAGE)));
		bob.adjustmaxY(Double.parseDouble(JOptionPane.showInputDialog(null,
				"Please give maximum y", JOptionPane.PLAIN_MESSAGE)));
		bob.setInc((bob.getmaxX() - bob.getminX()) / ratio);
		double vertline = bob.getmaxY() - bob.getminY() + 0.0;
		bob.setvertScale(getHeight() / vertline);
		double horizline = bob.getmaxX() - bob.getminX() + 0.0;
		bob.sethorizScale(getWidth() / horizline);
	}

	/**
	 * Paint method for applet.
	 * 
	 * @param
	 *            the Graphics object for this applet
	 */
	public void paint(Graphics gr) {
		// simple text displayed on applet
		Graphics2D g = (Graphics2D) gr;
		c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString(newD, getWidth() - 210, getHeight() - 20);
		g.drawString(newGraph, getWidth() - 210, getHeight() - 7);
		g.setColor(c);
		double adjustment = 50.0 * bob.getInc();
		if (num == 0) {
			drawRoots(g);
		} else if (num == 1) {
			bob.adjustminX(bob.getminX() + adjustment);
			bob.adjustmaxX(bob.getmaxX() + adjustment);
		} else if (num == 2) {
			bob.adjustminY(bob.getminY() + adjustment);
			bob.adjustmaxY(bob.getmaxY() + adjustment);
		} else if (num == 3) {
			bob.adjustminX(bob.getminX() - adjustment);
			bob.adjustmaxX(bob.getmaxX() - adjustment);
		} else if (num == 4) {
			bob.adjustminY(bob.getminY() - adjustment);
			bob.adjustmaxY(bob.getmaxY() - adjustment);
		} else if (num == 5) {
			bob.drawGraph(g);
			newDimensions();
			reset(g);
		} else if (num == 6) {
			bob.drawGraph(g);
			bob.addNewGraph();
			reset(g);
		}
		bob.drawGraph(g);
		drawMinAndMaxes(g);
	}

	public void reset(Graphics2D g) {
		g.setColor(Color.black);
		g.fill(new Rectangle2D.Double(0.0, 0.0, getWidth(), getHeight()));
	}

	public void drawRoots(Graphics2D g) {
		int dist = 0;
		String strRoot;
		for (int i = 0; i < bob.setRoots().size(); i++) {
			g.setColor(bob.getGraphs().get(i).getColor());
			for (double root : bob.setRoots().get(i)) {
				strRoot = "" + root;

				g.drawString("(" + strRoot + "," + "0.0" + ")", 5, 10 + dist);
				dist += 15;
			}
			dist += 30;
		}
	}

	public void drawMinAndMaxes(Graphics2D g) {
		int dist = 10;
		int i = 0;
		for (EquationGrapher r : bob.getGraphs()) {
			g.setColor(bob.getGraphs().get(i).getColor());
			g.drawString("min x on graph = " + r.getminxongraph(),
					getWidth() - 250, dist);
			g.drawString("max x on graph = " + r.getmaxxongraph(),
					getWidth() - 250, dist + 15);
			dist += 30;
			i++;
		}

	}

	/**
	 * 
	 * Called by the browser or applet viewer to inform this JApplet that it is
	 * being reclaimed and that it should destroy any resources that it has
	 * allocated. The stop method will always be called before destroy.
	 */
	public void destroy() {
		// provide code to be run when JApplet is about to be destroyed.
		JOptionPane.showMessageDialog(null, "Graph-o-matico Turning Off...",
				"Applet Termination!", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Returns information about this applet. An applet should override this
	 * method to return a String containing information about the author,
	 * version, and copyright of the JApplet.
	 *
	 * @return a String representation of information about this JApplet
	 */
	public String getAppletInfo() {
		// provide information about the applet
		return "This is a little fun grapher applet...";
	}

	/**
	 * Returns parameter information about this JApplet. Returns information
	 * about the parameters than are understood by this JApplet. An applet
	 * should override this method to return an array of Strings describing
	 * these parameters. Each element of the array should be a set of three
	 * Strings containing the name, the type, and a description.
	 *
	 * @return a String[] representation of parameter information about this
	 *         JApplet
	 */
	public String[][] getParameterInfo() {
		// provide parameter information about the applet
		String paramInfo[][] = {
				{ "firstParameter", "1-10", "description of first parameter" },
				{ "status", "boolean", "description of second parameter" },
				{ "images", "url", "description of third parameter" } };
		return paramInfo;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_X) {
			num = 0;

			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			num = 1;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			num = 2;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			num = 3;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			num = 4;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			num = 5;

			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_N) {
			num = 6;

			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_X) {
			num = 0;

			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			num = 1;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			num = 2;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			num = 3;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			num = 4;

			java.awt.Toolkit.getDefaultToolkit().beep();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			num = 5;

			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_N) {
			num = 6;

			repaint();
		}
	}
}
