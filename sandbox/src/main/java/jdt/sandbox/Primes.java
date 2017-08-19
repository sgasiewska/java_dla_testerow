package jdt.sandbox;

public class Primes {
  public static boolean isPreame (int n){
    for (int i=2; i<n; i ++){
      if (n%i ==0){
        return false;

      }
    }
    return true;
  }
  public static boolean isPreameWhile (int n){
    int i=2;
    while (i<n && n %i !=0){

      i++;
    }
    return i==n;
  }
  public static boolean isPreame (long n){
    for (long i=2; i<n; i ++){
      if (n%i ==0){
        return false;

      }
    }
    return true;
  }
  public static boolean isPreameFast (int n){
    int m = (int) Math.sqrt(n);
    for (int i=2; i<m; i ++){
      if (n%i ==0){
        return false;

      }
    }
    return true;
  }
}
