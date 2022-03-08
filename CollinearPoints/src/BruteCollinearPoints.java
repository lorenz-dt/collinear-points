import java.util.Comparator;

public class BruteCollinearPoints {

    private int numberOfSegments;   // number of segments which contain 4 points
    private final LineSegment[] segments;   // array of all segments which contain
    // 4 points from the array of points

    /**
     * Constructor. Method which does the actual work checking the array.
     *
     * @param points input array of points to search for collinear points
     * @throws IllegalArgumentException if any members of points are null
     *                                  or duplicates
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("no empty array");
        for (Point p : points)
            if (p == null)
                throw new IllegalArgumentException("no empty array entries");
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("no duplicate points");

        numberOfSegments = 0;
        segments = new LineSegment[points.length];

        if (points.length > 3) {
            Comparator<Point> comp;
            for (int i = 0; i < points.length - 3; i++) {
                comp = points[i].slopeOrder();
                for (int j = i + 1; j < points.length - 2; j++) {
                    if (points[i].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException("");
                    for (int k = j + 1; k < points.length - 1; k++) {
                        if (points[j].compareTo(points[k]) == 0)
                            throw new IllegalArgumentException("");
                        if (comp.compare(points[j], points[k]) == 0) {
                            for (int l = k + 1; l < points.length; l++) {
                                if (points[k].compareTo(points[l]) == 0)
                                    throw new IllegalArgumentException("");
                                if (comp.compare(points[j], points[l]) == 0) {
                                    segments[numberOfSegments] =
                                            new LineSegment(points[i], points[l]);
                                    numberOfSegments++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Getter for numberOfSegments.
     *
     * @return number of segments which contain 4 points from the input array
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }

    /**
     * Getter for segments
     *
     * @return an array which contains all of the line segments which contain
     * 4 collinear points
     */
    public LineSegment[] segments() {
        return segments;
    }

    /**
     * Unit Testing
     */
    public static void main(String[] args) {
    }
}
