package Grapher;

import java.util.*;
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
 * Write a description of class RootFinder here. A root finder for graphing
 * calculator!!!!!!!!
 * 
 * @author William Lee
 * @version (1)
 */
public class RootFinder {
	// instance variables - replace the example below with your own
	private int Option;
	private double minxongraph, maxxongraph, inc, a, b, c, d;
	private double minx, maxx, miny, maxy;
	private static final double EPSILON = 0.00000000000001;
	private double func, nextf;
	/** x-val separated by inc */
	ArrayList<Double> roots;

	public RootFinder(int opt, double first, double second, double third,
			double fourth, double mix, double max, double miy, double may) {
		Option = opt;
		a = first;
		b = second;
		c = third;
		d = fourth;
		minx = mix;
		maxx = max;
		miny = miy;
		maxy = may;
		roots = new ArrayList<Double>();
	}

	public void setminx(double i) {
		minx = i;
	}

	public void setmaxx(double i) {
		maxx = i;
	}

	public void setminy(double i) {
		miny = i;
	}

	public void setmaxy(double i) {
		maxy = i;
	}

	public void setminxongraph(double min) {
		minxongraph = min + 0.0;
	}

	public void setmaxxongraph(double max) {
		maxxongraph = max + 0.0;
	}

	public void setinc(double increment) {
		inc = increment;
	}

	public static boolean equals(double first, double second)/** in machine terms */
	{ // return
		// Math.abs(first-second)<=EPSILON*Math.max(Math.abs(first),Math.abs(second));
		return Math.abs(first - second) < EPSILON;
	}

	public ArrayList<Double> FindRoots() {
		double pos;
		roots = new ArrayList<Double>();
		if (Option == 0) {
			for (pos = minxongraph; pos <= maxxongraph - inc; pos += inc) {
				func = 1.0 * EquationGrapher.function(a, b, pos) + 0.0;
				nextf = 1.0 * EquationGrapher.function(a, b, pos + inc) + 0.0;
				if (func > maxy || func < miny || nextf > maxy || nextf < miny)
					continue;
				if (equals(func, 0.0)) {
					roots.add(pos);
				}
				if (equals(nextf, 0.0)) {
					roots.add(pos + inc);
				}
				if ((func < 0.0 && nextf > 0.0) || (func > 0.0 && nextf < 0.0)) {
					roots.add(findRoot0(pos, pos + inc));
				}
			}
		} else if (Option == 1) {
			for (pos = minxongraph; pos <= maxxongraph - inc; pos += inc) {
				func = 1.0 * EquationGrapher.function(a, b, c, pos) + 0.0;
				nextf = 1.0 * EquationGrapher.function(a, b, c, pos + inc) + 0.0;
				if (func > maxy || func < miny || nextf > maxy || nextf < miny)
					continue;
				if (equals(func, 0.0)) {
					roots.add(pos);
					// JOptionPane.showMessageDialog(null,"1");
				}
				if (equals(nextf, 0.0)) {
					roots.add(pos + inc);
					// JOptionPane.showMessageDialog(null,"2");
				}
				if ((func < 0.0 && nextf > 0.0) || (func > 0.0 && nextf < 0.0)) {
					// JOptionPane.showMessageDialog(null,"before3");
					roots.add(findRoot1(pos, pos + inc));
					// JOptionPane.showMessageDialog(null,"3");
				}
			}
		} else {
			// JOptionPane.showMessageDialog(null,
			// minxongraph + " "+maxxongraph+ " inc="+inc);
			for (pos = minxongraph; pos <= maxxongraph - inc; pos += inc) {

				func = 1.0 * EquationGrapher.function(a, b, c, d, pos) + 0.0;
				nextf = 1.0 * EquationGrapher.function(a, b, c, d, pos + inc) + 0.0;
				if (func > maxy || func < miny || nextf > maxy || nextf < miny)
					continue;
				if (equals(func, 0.0)) {
					roots.add(pos);
				}
				if (equals(nextf, 0.0)) {
					roots.add(pos + inc);
				}
				if ((func < 0.0 && nextf > 0.0) || (func > 0.0 && nextf < 0.0)) {
					// JOptionPane.showMessageDialog(null,
					// "pos=" + pos);
					roots.add(findRoot2(pos, pos + inc));
				}
			}
		}
		return roots;
	}

	public double findRoot0(double first, double last) {
		double middle = (first + last) / 2.0;
		while (!equals(EquationGrapher.function(a, b, middle), 0.0)) {
			if (EquationGrapher.function(a, b, first) < 0.0
					&& EquationGrapher.function(a, b, last) > 0.0) {
				if (EquationGrapher.function(a, b, middle) > 0.0) {
					last = middle;
				} else {
					first = middle;
				}
			} else {
				if (EquationGrapher.function(a, b, middle) > 0.0) {
					first = middle;
				} else {
					last = middle;
				}
			}
			middle = (first + last) / 2.0;
		}
		return middle;
	}

	public double findRoot1(double first, double last) {
		double middle = (first + last) / 2.0;
		while (!equals(EquationGrapher.function(a, b, c, middle), 0.0)) {
			if (EquationGrapher.function(a, b, c, first) < 0.0
					&& EquationGrapher.function(a, b, c, last) > 0.0) {
				if (EquationGrapher.function(a, b, c, middle) > 0.0) {
					last = middle;
				} else {
					first = middle;
				}
			} else {
				if (EquationGrapher.function(a, b, c, middle) > 0.0) {
					first = middle;
				} else {
					last = middle;
				}
			}
			middle = (first + last) / 2.0;
		}
		return middle;
	}

	public double findRoot2(double first, double last) {
		double middle = (first + last) / 2.0;
		// JOptionPane.showMessageDialog(null,
		// first+" "+EquationGrapher.function(a,b,c,d,first));
		// JOptionPane.showMessageDialog(null,
		// last+" "+EquationGrapher.function(a,b,c,d,last));

		// JOptionPane.showMessageDialog(null,
		// ""+EquationGrapher.function(a,b,c,d,middle));
		while (!equals(EquationGrapher.function(a, b, c, d, middle), 0.0)) {
			if (EquationGrapher.function(a, b, c, d, first) < 0.0
					&& EquationGrapher.function(a, b, c, d, last) > 0.0) {
				if (EquationGrapher.function(a, b, c, d, middle) > 0.0) {
					last = middle;
				} else {
					first = middle;
				}
			} else {
				if (EquationGrapher.function(a, b, c, d, middle) > 0.0) {
					first = middle;
				} else {
					last = middle;
				}
			}
			middle = (first + last) / 2.0;
			// JOptionPane.showMessageDialog(null,
			// middle+" "+EquationGrapher.function(a,b,c,d,middle));
		}

		return middle;
	}

}
