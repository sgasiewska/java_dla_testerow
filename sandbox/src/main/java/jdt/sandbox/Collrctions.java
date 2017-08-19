package jdt.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collrctions {
  public static void main (String [] args){
    String[] langs= {"java","C#","python","PHP"};

    List<String>languages = Arrays.asList("java","C#","python","PHP");
   // languages.add("java");


    for  (String l:languages) {
            //(int i =0; i<languages.size();i++){
      //   (int i =0;i<langs.length;i++){
    //  System.out.println("Chcę nauczyć się: " + languages.get(i));
      System.out.println("Chcę nauczyć się: "+ l);
    }

  }
}
