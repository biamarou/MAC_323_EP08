import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KsTreeST<Value> {
    Node TreePoints;
    int size;
    
    private class Node {
	Point2D point;
	Value value;
	Node left, right;

	public Node (double x, double y, Value val) {
	    point = new Point2D(x,y);
	    value = val;
	    left = right = null;
	}
    }
    
    public KdTreeST() {
	TreePoints = null;
	size = 0;
    } // construct an empty symbol table of points 

    public boolean isEmpty() {
        return TreePoints == null;
    } // is the symbol table empty?
    
    public int size() {
        return size;
    } // number of points 

    public void put(Point2D p, Value val) {
	Node insert = new Node(p.x(), p.y(), val);
	Node tmp, ntmp;
	boolean x;
	
	if (TreePoints == null) TreePoints = insert;
	else {

	    x = true;
		
	    for (tmp = TreePoints; tmp != null && !tmp.point.equals(p)) {
		if (x) {
		    ntmp = tmp;
		    if (tmp.point.x() < p.x())
			tmp = tmp.right;
		    
		    else
			tmp = tmp.left;
		    x = false;
		}

		else {
		    ntmp = tmp;
		    if (tmp.point.y() < p.y())
			tmp = tmp.right;
		    else
			tmp = tmp.left;
		    x = true;
		    
		}
	    }

	    if (tmp == null)
		
	}
    } // associate the value val with point p

    public Value get(Point2D p) {
        
    } // value associated with point p 

    public boolean contains(Point2D p) {
        
    } // does the symbol table contain point p? 

    public Iterable<Point2D> points() {
        
    } // all points in the symbol table 

    public Iterable<Point2D> range(RectHV rect) {

    } // all points that are inside the rectangle 

    public Point2D nearest(Point2D p) {

    } // a nearest neighbor to point p; null if the symbol table is empty 

    public static void main(String[] args) {

    } // unit testing (required) 
}