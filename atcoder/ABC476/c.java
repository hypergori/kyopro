import java.util.*;
class Main{
   public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int  N = scan.nextInt();
	List<Integer> list = new ArrayList<>();
	for(int i=0;i<N;i++){
	    int n =  scan.nextInt();
	    list.add(n);
	    if(i<2){
		continue;
	    }
	    Collections.sort(list,Collections.reverseOrder());
            System.out.println(list.get(2));
	    if(list.size()>3){
	       list.remove(3);
	    }
        }
   }
}
