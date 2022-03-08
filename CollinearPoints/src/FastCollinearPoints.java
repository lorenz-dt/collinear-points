import java.util.Comparator;

public class FastCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] segments;
    private static Comparator<Point> order;

    public FastCollinearPoints(Point[] points) {
        // TODO handle exceptions

        numberOfSegments = 0;
        segments = new LineSegment[points.length];

        Point p;
        for (int i = 0; i < points.length; i++) {
            p = points[i];
            order = p.slopeOrder();
            overallsort(points);
            countCollinearPoints(p, points);
        }
    }

    private static void countCollinearPoints(Point p, Point[] list) {
        
    }

    private static void merge(Point[] orig, Point[] copy, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)      // copy
            copy[k] = orig[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {    // merge
            if (i > mid) orig[k] = copy[j++];
            else if (j > hi) orig[k] = copy[i++];
            else if (order.compare(copy[j], copy[i]) < 0) orig[k] = copy[j++];
            else orig[k] = copy[i++];
        }
    }

    private static void mergesort(Point[] orig, Point[] copy, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergesort(orig, copy, lo, mid);
        mergesort(orig, copy, mid + 1, hi);
        merge(orig, copy, lo, mid, hi);
    }

    private static void overallsort(Point[] p) {
        Point[] copy = new Point[p.length];
        mergesort(p, copy, 0, p.length - 1);
    }

}
