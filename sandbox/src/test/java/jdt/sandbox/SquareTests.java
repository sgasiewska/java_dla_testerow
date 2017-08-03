package jdt.sandbox;

//import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

@Test
  public void testArea(){
    Square s= new Square(5);
    //assert s.area() == 26;
  Assert.assertEquals(s.area(),25.0);
  }
}
