package jdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){

Point p3 = new Point(3, -2);
Point p4 = new Point(6, 2);
Point p5 = new Point (1,-2);

    Assert.assertEquals(p3.distance(p4),5.0);
    Assert.assertEquals(p3.distance(p5),2.0);
    Assert.assertEquals(p4.distance(p5),Math.sqrt(41));

  }
}
