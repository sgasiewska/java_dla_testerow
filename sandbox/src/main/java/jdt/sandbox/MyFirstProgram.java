package jdt.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Sandra");

    double l= 5;
    System.out.println("Pole kwadratu o boku "+l+ "="+area(l));

    double a=3;
    double b=4;
    System.out.println("Pole prostokąta o bokach "+a+ " i "+b+"="+area(a,b));

  }
  public static void hello(String somebody){
    System.out.println("Hello,"+somebody+ "!");

  }
  public static double area (double l){
    return l*l;
  }
  public static double area (double a, double b){
    return a*b;
  }
}
