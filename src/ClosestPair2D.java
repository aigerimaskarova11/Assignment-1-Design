import java.util.*;

public class ClosestPair2D {

    public static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingInt(p -> p.x));
        Point[] aux = new Point[points.length];
        return closest(sortedByX, aux, 0, points.length - 1);
    }

    private static double closest(Point[] pts, Point[] aux, int left, int right) {
        if (right - left <= 3) return bruteForce(pts, left, right);
        int mid = (left + right) / 2;
        double d1 = closest(pts, aux, left, mid);
        double d2 = closest(pts, aux, mid + 1, right);
        double d = Math.min(d1, d2);
        mergeByY(pts, aux, left, mid, right);
        List<Point> strip = new ArrayList<>();
        int midX = pts[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) strip.add(pts[i]);
        }
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) aux[k++] = pts[i++];
            else aux[k++] = pts[j++];
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        for (i = left; i <= right; i++) pts[i] = aux[i];
    }

    private static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        Arrays.sort(pts, left, right + 1, Comparator.comparingInt(p -> p.y));
        return min;
    }

    private static double dist(Point a, Point b) {
        long dx = (long) a.x - b.x;
        long dy = (long) a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Point[] pts = {
                new Point(2, 3), new Point(12, 30), new Point(40, 50),
                new Point(5, 1), new Point(12, 10), new Point(3, 4)
        };
        System.out.println(closestPair(pts));
    }
}
