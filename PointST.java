import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.In;
import java.lang.Iterable;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class PointST<Value> {
    RedBlackBST<Point2D, Value> points;
    
    public PointST() {
	points = new RedBlackBST<>();
    } // construct an empty symbol table of points 

    public boolean isEmpty() {
	return points.isEmpty();
    } // is the symbol table empty?
    
    public int size() {
	return points.size();
    } // number of points 

    public void put(Point2D p, Value val) {
	if (p == null || val == null)
	    throw new java.lang.NullPointerException();
	points.put(p, val);
    } // associate the value val with point p

    public Value get(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException();
	return points.get(p);
    } // value associated with point p 

    public boolean contains(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException();
	return points.contains(p);
    } // does the symbol table contain point p? 

    public Iterable<Point2D> points() {
	return points.keys();
    } // all points in the symbol table 

    public Iterable<Point2D> range(RectHV rect) {
	if (rect == null)
	    throw new java.lang.NullPointerException();
	
	Iterable<Point2D> STpoints = points();
	Bag<Point2D> inside = new Bag<Point2D>();
	for (Point2D p : STpoints)
	    if (rect.contains(p))
		inside.add(p);
	return inside;
    } // all points that are inside the rectangle 

    public Point2D nearest(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException();

	if (points.isEmpty()) {
	    return null;
	}
	Point2D near = null; 
	Iterable<Point2D> allPoints = points();
	for (Point2D pi : allPoints)
	    if (near == null || near.distanceSquaredTo(p) > pi.distanceSquaredTo(p))
	        near = pi;
	return near;    

    } // a nearest neighbor to point p; null if the symbol table is empty

    public Iterable<Point2D> nearest(Point2D p, int k) {
	if (p == null)
	    throw new java.lang.NullPointerException();
	
	if (points.isEmpty()) {
	    return null;
	}
	Point2D near; 
	Iterable<Point2D> allPoints = points();
	LinkedList<Point2D> Knearest = new LinkedList<>();

	if (points.size() <= k)
	    return allPoints;
	
	while (k > 0) {
	    near = null;
	    for (Point2D pi : allPoints)
		if ((near == null || near.distanceSquaredTo(p) > pi.distanceSquaredTo(p)))
		    if (!Knearest.contains(pi))
			near = pi;
	    Knearest.add(near);
	    k--;
	}

	return Knearest;    

    } // a nearest neighbor to point p; null if the symbol table is empty 

    public static void main(String[] args) {

	In file = new In(args[0]);
	PointST<Integer> arvore = new PointST<Integer>();
	Point2D ponto;
	Integer i = 0;
	
	while(!file.isEmpty()) {
	    ponto = new Point2D(file.readDouble(), file.readDouble());
	    arvore.put(ponto, i);
	    i++;
	    Iterable<Point2D> iteravel = arvore.nearest(ponto, 2);
	    System.out.println("Near" + arvore.nearest(ponto));
	    System.out.println("Ponto " + ponto);
	    for (Point2D p : iteravel) {
		System.out.println(p);
	    }
	}

    } // unit testing (required) 
}
