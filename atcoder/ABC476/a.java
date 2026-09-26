import java.util.*;
class Main{
   public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        if(S.charAt(S.length()-1)=='e'){
           System.out.println(S+"r");
        }else{
           System.out.println(S+"er");
        }
   }
}
