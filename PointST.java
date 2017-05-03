import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointST<Value> {
    RedBlackBST points;
    
    public PointST() {
	points = new RedBlackBST();
    } // construct an empty symbol table of points 

    public boolean isEmpty() {
	return points.isEmpty();
    } // is the symbol table empty?
    
    public int size() {
	return points.size();
    } // number of points 

    public void put(Point2D p, Value val) {
	points.put(p, val);
    } // associate the value val with point p

    public Value get(Point2D p) {
	return (Value)points.get(p);
    } // value associated with point p 

    public boolean contains(Point2D p) {
	return points.contains(p);
    } // does the symbol table contain point p? 

    public Iterable<Point2D> points() {
	return points.keys();
    } // all points in the symbol table 

    public Iterable<Point2D> range(RectHV rect) {
	Iterable<Point2D> STpoints = points();
	Bag<Point2D> inside = new Bag<Point2D>();
	for (Point2D p : STpoints)
	    if (rect.contains(p))
		inside.add(p);
	return inside;
    } // all points that are inside the rectangle 

    public Point2D nearest(Point2D p) {
	if (points.isEmpty())
	    return null;
	else {
	    return (Point2D)points.floor(p);
	}

    } // a nearest neighbor to point p; null if the symbol table is empty 

    public static void main(String[] args) {

    } // unit testing (required) 
}
