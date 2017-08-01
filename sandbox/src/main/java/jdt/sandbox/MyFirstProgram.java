package jdt.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Sandra");

    Square s=new Square(5);
    System.out.println("Pole kwadratu o boku "+s.l+ "="+s.area());
    Rectangle r=new Rectangle(3,4);
    System.out.println("Pole prostokąta o bokach "+r.a+ " i "+r.b+"="+r.area());

  }
  public static void hello(String somebody){
    System.out.println("Hello,"+somebody+ "!");

  }

}
