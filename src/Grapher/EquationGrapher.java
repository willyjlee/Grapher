package Grapher;

import java.lang.Object.*;
import java.awt.Graphics2D;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import java.lang.Math.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.lang.Comparable;
import java.util.Scanner;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.lang.Integer.*;

/**
 * Write a description of class GraphSheet here.
 * 
 * @author William Lee
 * @version (1)
 */
public class EquationGrapher {
	// instance variables - replace the example below with your own
	private Color col;
	private int Option;
	private double a, b, c, d;
	private boolean flag;
	private double minxongraph;
	private double maxxongraph;
	private RootFinder sam;
	private boolean firstin, secondin;
	private GraphingCalculator calculus;

	/**
	 * Constructor for objects of class GraphSheet
	 */
	public EquationGrapher(GraphingCalculator g) {
		// initialise instance variables
		calculus = g;
//		Object[] options = { "ax+b", "ax^2 + bx + c", " a/(bx+c) + d" };
		Object[] options = { "ax+b", "ax^2 + bx + c", " a*sin(b*x + c) + d" };

		Option = JOptionPane.showOptionDialog(null,
				"Please select the form of equation", "Equations",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, null);
		Color[] colopts = { Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE,
				Color.CYAN, Color.PINK, Color.YELLOW };
		Object[] colors = { "Blue", "Green", "Red", "Orange", "Cyan", "Pink",
				"Yellow" };
		int colopt = JOptionPane.showOptionDialog(null,
				"Please select a fun color!", "Color",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, colors, colors[0]);
		col = colopts[colopt];
		if (Option == 0) {
			a = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"a\". Please: a can't be 0"
							+ " " + options[0], "first coefficient",
					JOptionPane.PLAIN_MESSAGE));
			b = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"b\"" + " " + options[0],
					"second coefficient", JOptionPane.PLAIN_MESSAGE));
			JOptionPane
					.showMessageDialog(
							null,
							"Use arrow keys to adjust the view! Type \"x\" to find the roots! Type \"d\" for new dimensions! Have Fun!!!!!!!");
		} else if (Option == 1) {
			a = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"a\". Please: a can't be 0"
							+ " " + options[1], "first coefficient",
					JOptionPane.PLAIN_MESSAGE));
			b = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"b\"" + " " + options[1],
					"second coefficient", JOptionPane.PLAIN_MESSAGE));
			c = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"c\"" + " " + options[1],
					"third coefficient", JOptionPane.PLAIN_MESSAGE));
			JOptionPane
					.showMessageDialog(
							null,
							"Use arrow keys to adjust the view! Type \"x\" to find the roots! Type \"d\" for new dimensions! Have Fun!!!!!!!");
		} else {
			a = Double
					.parseDouble(JOptionPane
							.showInputDialog(
									null,
									"Please the give the value for \"a\". Please: a can't be 0",
									"first coefficient",
									JOptionPane.PLAIN_MESSAGE));
			b = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"b\"" + " " + options[2],
					"second coefficient", JOptionPane.PLAIN_MESSAGE));
			c = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"c\"" + " " + options[2],
					"third coefficient", JOptionPane.PLAIN_MESSAGE));
			d = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Please the give the value for \"d\"" + " " + options[2],
					"fourth coefficient", JOptionPane.PLAIN_MESSAGE));
			JOptionPane
					.showMessageDialog(
							null,
							"Use arrow keys to adjust the view! Type \"x\" to find the roots! Type \"d\" for new dimensions! Have Fun!!!!!!!");
		}
		sam = new RootFinder(Option, a, b, c, d, calculus.getminX(),
				calculus.getmaxX(), calculus.getminY(), calculus.getmaxY());
	}

	public static double function(double a, double b, double x) {
		double ans = a * x + b + 0.0;
		return ans;
	}

	public static double function(double a, double b, double c, double x) {
		double ans = a * Math.pow(x, 2) + b * x + c + 0.0;
		// double ans=a*Math.pow(b,x)*1.0+c+0.0;
		return ans;
	}

	public static double function(double a, double b, double c, double d,
			double x) {
		double ans = a * Math.sin(b * x + c) + d;
		// double ans = ((a + 0.0) / ((b * x * 1.0) + c + 0.0)) + d + 0.0;
		return ans;
	}

	public ArrayList<Double> setRoots() {
		sam.setminxongraph(minxongraph);
		sam.setmaxxongraph(maxxongraph);

		sam.setminx(calculus.getminX());
		sam.setmaxx(calculus.getmaxX());
		sam.setminy(calculus.getminY());
		sam.setmaxy(calculus.getmaxY());

		sam.setinc(calculus.getInc());
		return sam.FindRoots();
	}

	public void drawGraph(Graphics2D gr) {
		drawGraphLines(gr, calculus.getminX(), Option);
	}

	public void drawGraphLines(Graphics2D gr, double minx, int option) {
		if (option == 0) {
			drawGraphLinesPart0(gr, minx);
		}
		if (option == 1) {
			drawGraphLinesPart1(gr, minx);
		}
		if (option == 2) {
			drawGraphLinesPart2(gr, minx);
		}
		flag = false;
	}

	public void drawGraphLinesPart0(Graphics2D gr, double minx) {
		if (minx > calculus.getmaxX()) {
			return;
		}
		double y = function(a, b, minx);
		double firsty = y;
		Point2D.Double point1 = new Point2D.Double(
				(minx - (calculus.getminX())) * calculus.gethorizScale() * 1.0,
				(calculus.getmaxY() - y) * calculus.getvertScale() * 1.0);
		double nextminx = minx + calculus.getInc() + 0.0;
		y = function(a, b, nextminx);
		double secondy = y;
		Point2D.Double point2 = new Point2D.Double(
				(nextminx - (calculus.getminX())) * calculus.gethorizScale()
						* 1.0, (calculus.getmaxY() - y)
						* calculus.getvertScale() * 1.0);
		Line2D.Double onetotwo = new Line2D.Double(point1, point2);
		firstin = (firsty <= calculus.getmaxY() && firsty >= calculus.getminY()
				&& minx <= calculus.getmaxX() && minx >= calculus.getminX());
		secondin = (secondy <= calculus.getmaxY()
				&& secondy >= calculus.getminY()
				&& nextminx <= calculus.getmaxX() && nextminx >= calculus
				.getminX());
		if (firstin || secondin) {
			if (firstin && !flag) {
				minxongraph = minx;
				flag = true;
			}
			if (secondin && !flag) {
				minxongraph = nextminx;
				flag = true;
			}
			if (firstin && !secondin) {
				maxxongraph = minx;
			} else if (!firstin && secondin) {
				maxxongraph = nextminx;
			}
			gr.setColor(getColor());
			gr.draw(onetotwo);
			drawGraphLinesPart0(gr, nextminx);
		} else if (!firstin && !secondin) {
			drawGraphLinesPart0(gr, nextminx);
		}
	}

	public void drawGraphLinesPart1(Graphics2D gr, double minx) {
		if (minx > calculus.getmaxX()) {
			return;
		}
		double y = function(a, b, c, minx);
		double firsty = y;
		Point2D.Double point1 = new Point2D.Double(
				(minx - (calculus.getminX())) * calculus.gethorizScale() * 1.0,
				(calculus.getmaxY() - y) * calculus.getvertScale() * 1.0);
		double nextminx = minx + calculus.getInc() + 0.0;
		y = function(a, b, c, nextminx);
		double secondy = y;
		Point2D.Double point2 = new Point2D.Double(
				(nextminx - (calculus.getminX())) * calculus.gethorizScale()
						* 1.0, (calculus.getmaxY() - y)
						* calculus.getvertScale() * 1.0);
		Line2D.Double onetotwo = new Line2D.Double(point1, point2);
		firstin = (firsty <= calculus.getmaxY() && firsty >= calculus.getminY()
				&& minx <= calculus.getmaxX() && minx >= calculus.getminX());
		secondin = (secondy <= calculus.getmaxY()
				&& secondy >= calculus.getminY()
				&& nextminx <= calculus.getmaxX() && nextminx >= calculus
				.getminX());
		if (firstin || secondin) {
			if (firstin && !flag) {
				minxongraph = minx;
				flag = true;
			}
			if (secondin && !flag) {
				minxongraph = nextminx;
				flag = true;
			}
			if (firstin && !secondin) {
				maxxongraph = minx;
			} else if (!firstin && secondin) {
				maxxongraph = nextminx;
			}
			gr.setColor(getColor());
			gr.draw(onetotwo);
			drawGraphLinesPart1(gr, nextminx);
		} else if (!firstin && !secondin) {
			drawGraphLinesPart1(gr, nextminx);
		}
	}

	public void drawGraphLinesPart2(Graphics2D gr, double minx) {
		if (minx > calculus.getmaxX()) {
			return;
		}
		double y = function(a, b, c, d, minx);
		double firsty = y;
		Point2D.Double point1 = new Point2D.Double(
				(minx - (calculus.getminX())) * calculus.gethorizScale() * 1.0,
				(calculus.getmaxY() - y) * calculus.getvertScale() * 1.0);
		double nextminx = minx + calculus.getInc() + 0.0;
		y = function(a, b, c, d, nextminx);
		double secondy = y;
		Point2D.Double point2 = new Point2D.Double(
				(nextminx - (calculus.getminX())) * calculus.gethorizScale()
						* 1.0, (calculus.getmaxY() - y)
						* calculus.getvertScale() * 1.0);
		Line2D.Double onetotwo = new Line2D.Double(point1, point2);
		firstin = (firsty <= calculus.getmaxY() && firsty >= calculus.getminY()
				&& minx <= calculus.getmaxX() && minx >= calculus.getminX());
		secondin = (secondy <= calculus.getmaxY()
				&& secondy >= calculus.getminY()
				&& nextminx <= calculus.getmaxX() && nextminx >= calculus
				.getminX());
		if (firstin || secondin) {
			if (firstin && !flag) {
				minxongraph = minx;
				flag = true;
			}
			if (secondin && !flag) {
				minxongraph = nextminx;
				flag = true;
			}
			if (firstin && !secondin) {
				maxxongraph = minx;
			} else if (!firstin && secondin) {
				maxxongraph = nextminx;
			}
			gr.setColor(getColor());
			gr.draw(onetotwo);
			drawGraphLinesPart2(gr, nextminx);
		} else if (!firstin && !secondin) {
			drawGraphLinesPart2(gr, nextminx);
		}
	}

	public double getminxongraph() {
		return minxongraph;
	}

	public double getmaxxongraph() {
		return maxxongraph;
	}

	public void setColor(Color color) {
		col = color;
	}

	public Color getColor() {
		return col;
	}

	public int getOption() {
		return Option;
	}

	public void setOption(int opt) {
		Option = opt;
	}
}
