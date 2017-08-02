package jdt.sandbox;

public class Zadanie2 {

  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    double d = Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x))) + ((p2.y - p1.y) * (p2.y - p1.y));
    System.out.println("Odległośćzy punktami="+d);
    System.out.println("Odległośćzy punktami="+Point.distance(p1, p2));

  }

}


