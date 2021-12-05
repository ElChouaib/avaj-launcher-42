package dao.service;
/**
 * @Author Chouaib
 * @Date 27-11-2021
 * @Project : Avaj-launcher-42
 */
public class LivensteinService {
        public static int calculate(String x, String y) {
             if (x.isEmpty()) {
                 return y.length();
             }
     
             if (y.isEmpty()) {
                 return x.length();
             } 
     
             int substitution = calculate(x.substring(1), y.substring(1)) 
              + costOfSubstitution(x.charAt(0), y.charAt(0));
             int insertion = calculate(x, y.substring(1)) + 1;
             int deletion = calculate(x.substring(1), y) + 1;
     
             return min(substitution, insertion, deletion);
         }
     
         private static int costOfSubstitution(char a, char b) {
             return a == b ? 0 : 1;
         }
     
         private static int min(int a, int b, int c) {
             int smallest = a;
             if(smallest > b) smallest = b;
             if(smallest > c) smallest = c;
             return smallest;
         }
}