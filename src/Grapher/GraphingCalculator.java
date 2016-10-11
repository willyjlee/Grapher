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
 * Write a description of class Graph here.
 * 
 * @author William Lee
 * @version (1)
 */
public class GraphingCalculator {
	// instance variables - replace the example below with your own
	private double inc, maxY, minY, maxX, minX;
	/** a,b,c,d are initialized when graphed is called */
	private double vertscale;
	private double horizscale;
	private ArrayList<EquationGrapher> graphs;
	private ArrayList<ArrayList<Double>> rootArray;

	public GraphingCalculator(double increment, double maxy, double miny,
			double maxx, double minx, double vert, double horiz) {
		inc = increment;
		maxY = maxy;
		minY = miny;
		maxX = maxx;
		minX = minx;
		vertscale = vert;
		horizscale = horiz;
		graphs = new ArrayList<EquationGrapher>();
		rootArray = new ArrayList<ArrayList<Double>>();
		addNewGraph();
	}

	public void drawGraph(Graphics2D gr) {
		setUpAxes(gr);
		for (EquationGrapher r : graphs) {
			r.drawGraph(gr);
		}
	}

	public ArrayList<ArrayList<Double>> setRoots() {
		rootArray = new ArrayList<ArrayList<Double>>(); // clears reset the
														// array
		for (EquationGrapher r : graphs) {
			rootArray.add(r.setRoots());
		}
		return rootArray;
	}

	public void addNewGraph() {
		graphs.add(new EquationGrapher(this));
	}

	public void setUpAxes(Graphics2D gr) {
		gr.setColor(Color.white);
		Point2D.Double horiz1 = new Point2D.Double(0, (maxY) * vertscale * 1.0);
		Point2D.Double horiz2 = new Point2D.Double((maxX - minX) * horizscale
				* 1.0, (maxY) * vertscale * 1.0);
		Line2D.Double horizline = new Line2D.Double(horiz1, horiz2);
		Point2D.Double vert1 = new Point2D.Double((-minX) * horizscale * 1.0, 0);
		Point2D.Double vert2 = new Point2D.Double((-minX) * horizscale * 1.0,
				(maxY - minY) * vertscale * 1.0);
		Line2D.Double vertline = new Line2D.Double(vert1, vert2);
		gr.draw(horizline);
		gr.draw(vertline);
	}

	public void adjustminX(double x) {
		minX = x + 0.0;

	}

	public void adjustmaxX(double x) {
		maxX = x + 0.0;
	}

	public void adjustminY(double y) {
		minY = y + 0.0;
	}

	public void adjustmaxY(double y) {
		maxY = y + 0.0;
	}

	public double getminX() {
		return minX;
	}

	public double getmaxX() {
		return maxX;
	}

	public double getminY() {
		return minY;
	}

	public double getmaxY() {
		return maxY;
	}

	public double getInc() {
		return inc;
	}

	public void setInc(double i) {
		inc = i + 0.0;
	}

	public void setvertScale(double sc) {
		vertscale = sc;
	}

	public void sethorizScale(double sc) {
		horizscale = sc;
	}

	public double getvertScale() {
		return vertscale;
	}

	public double gethorizScale() {
		return horizscale;
	}

	public ArrayList<EquationGrapher> getGraphs() {
		return graphs;
	}
}
