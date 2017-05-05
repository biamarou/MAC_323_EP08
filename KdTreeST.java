import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

//o retangulo maior seria {[0,0]x[1,1]}

public class KdTreeST<Value> {
    Node TreePoints;
    int size;
    
    private class Node {
	Point2D point;
	RectHV rect;
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
	ntmp = TreePoints;
	
	if (TreePoints == null) {
	    insert.rect = new RectHV(0, 0, 1, 1);
	    TreePoints = insert;
	    size++;
	}

	else {

	    x = true;
		
	    for (tmp = TreePoints; tmp != null && !tmp.point.equals(p);) {
		if (x) {
		    ntmp = tmp;
		    if (tmp.point.x() <= p.x())
			tmp = tmp.right;
		    
		    else
			tmp = tmp.left;
		    x = false;
		}

		else {
		    ntmp = tmp;
		    if (tmp.point.y() <= p.y())
			tmp = tmp.right;
		    else
			tmp = tmp.left;
		    x = true;
		    
		}
	    }

	    if (tmp == null) {
	        if (x) {
		    if (ntmp.point.y() <= p.y()) {
			insert.rect = new RectHV(ntmp.rect.xmin(), ntmp.rect.ymin(), ntmp.rect.xmax(), ntmp.point.y());
			ntmp.right = insert;
		    }
		    else {
			insert.rect = new RectHV(ntmp.rect.xmin(), ntmp.point.y(), ntmp.rect.xmax(), ntmp.rect.ymax());
			ntmp.left = insert;
		    }
		}

		else {
		    if (ntmp.point.x() <= p.x()) {
			insert.rect = new RectHV(ntmp.point.x(), ntmp.rect.ymin(), ntmp.rect.xmax(), ntmp.rect.ymax());
			ntmp.right = insert;
		    }
		    else {
			insert.rect = new RectHV(ntmp.rect.xmin(), ntmp.rect.ymin(), ntmp.point.x(), ntmp.rect.ymax());
			ntmp.left = insert;
		    }
		}
		size++;
	    }

	    else {
		ntmp.value = val;
	    }
	}
    } // associate the value val with point p

    public Value get(Point2D p) {
	Node tmp;
	boolean x = true;
	
	for (tmp = TreePoints; tmp != null && !tmp.point.equals(p);) {
		if (x) {
		    if (tmp.point.x() < p.x())
			tmp = tmp.right;
		    
		    else
			tmp = tmp.left;
		    x = false;
		}

		else {
		    if (tmp.point.y() < p.y())
			tmp = tmp.right;
		    else
			tmp = tmp.left;
		    x = true;
		    
		}
	}

	if (tmp == null) return null;
	else return tmp.value;
        
    } // value associated with point p 

    public boolean contains(Point2D p) {
        Node tmp;
	boolean x = true;
	
	for (tmp = TreePoints; tmp != null && !tmp.point.equals(p);) {
		if (x) {
		    if (tmp.point.x() < p.x())
			tmp = tmp.right;
		    
		    else
			tmp = tmp.left;
		    x = false;
		}

		else {
		    if (tmp.point.y() < p.y())
			tmp = tmp.right;
		    else
			tmp = tmp.left;
		    x = true;
		    
		}
	}

	if (tmp == null) return false;
	else return true;
	
    } // does the symbol table contain point p? 


    private void points(Bag<Point2D> bag, Node tmp) {
	if (tmp == null) return;
	points(bag, tmp.left);
	bag.add(tmp.point);
	points(bag, tmp.right);
    }

    public Iterable<Point2D> points() {
 
	Bag<Point2D> AllPoints = new Bag<Point2D>();
	points(AllPoints, TreePoints);
	return AllPoints;
	
    } // all points in the symbol table 

    private void range(Bag<Point2D> bag, Node tmp, RectHV rect) {
	if (tmp == null) return;
	else {
	    if (rect.contains(tmp.point))
		bag.add(tmp.point);

	    if (rect.intersects(tmp.left.rect))
		range(bag, tmp.left, rect);

	    if (tmp.rect.intersects(tmp.right.rect))
		range(bag, tmp.right, rect);
	}
    }
    
    public Iterable<Point2D> range(RectHV rect) {
	Bag<Point2D> AllContained = new Bag<Point2D>();
	range(AllContained, TreePoints, rect);
	return AllContained;

    } // all points that are inside the rectangle 


    private Point2D nearest (Point2D p, Point2D closest, Node tmp) {

	if (closest == null || (closest.distanceSquaredTo(p) > tmp.point.distanceSquaredTo(p)))
	    closest = tmp.point;
	if (tmp.left != null && closest.distanceSquaredTo(p) > tmp.left.rect.distanceSquaredTo(p))
	    closest = nearest(p, closest, tmp.left);
	if (tmp.right != null && closest.distanceSquaredTo(p) > tmp.right.rect.distanceSquaredTo(p))
	    closest = nearest(p, closest, tmp.right);
	return closest;
    }

    
    public Point2D nearest(Point2D p) {
	if (isEmpty()) return null;
	Point2D closest = null;
	closest = nearest(p, closest, TreePoints);
	System.out.println(closest);
	return closest;
	
    } // a nearest neighbor to point p; null if the symbol table is empty
    /*
    public Iterable<Point2D> nearest(Point2D p, int k) {
	if (size <= k) return points();
	MaxPQ<Point2D> nearestPoints = new MaxPQ<Point2D>();
	while (nearestPoints.size < k) {
	    
	}
	    
    }
    */
    public static void main(String[] args) {

    } // unit testing (required) 
}
