package jdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPreameFast(Integer.MAX_VALUE));
  }
  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPreame(Integer.MAX_VALUE-2));
  }
  @Test (enabled = false)
  public void testPrimeLong(){
    long n= Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPreame(2));
  }
}
