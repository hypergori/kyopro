import java.util.*;
import java.lang.*;
class Main{

   public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int  N = scan.nextInt();
        int  M = scan.nextInt();
        int  K = scan.nextInt();
        long  X = scan.nextLong();
        long  Y = scan.nextLong();
	List<int[]> list = new ArrayList<>();
	int ans =0;
        //can use both $1 bill and $K bill
	for(int i=0;i<N;i++){
	    int A = scan.nextInt();
	    int[] priceToType=new int[]{A,0};
	    list.add(priceToType);
	}
        //can use only  $K bill
	for(int i=0;i<M;i++){
	    int B = scan.nextInt();
	    int[] priceToType=new int[]{B,1};
	    list.add(priceToType);
	}
	list.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a ->a[1]));
        // greedy buy
        for(int i = 0; i< M+N ; i++){
	    int price = list.get(i)[0];
	    int type  = list.get(i)[1];
	    if(type == 0){
		while(X < price && Y>0){ //$1 bill not sufficient
			Y--;
			X+=K;
		}
		if(price>X){ // no more buying
		   break; 
		}
		X = X - price;
		ans++;		
	    }else{
		if(price>Y*K){ // no more buying, skip this drink
		   continue; 
		}
		int reqK = Math.ceilDiv(price,K);
		Y = Y - reqK;
		X +=  reqK*K - price;
		ans++;		
	    }	   
	}

        System.out.println(ans);
        }
}
