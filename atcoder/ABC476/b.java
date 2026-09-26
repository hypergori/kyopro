import java.util.*;
class Main{
   public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int  N = scan.nextInt();
        char[] S = scan.next().toCharArray();
        char[] T = scan.next().toCharArray();
	for(int i=0;i<N;i++){
            if(S[i] != T[i]){
	        if(T[i] != '*'){
                   System.out.println("No");
		   return;
	        }
            }
        }
        System.out.println("Yes");
   }
}
