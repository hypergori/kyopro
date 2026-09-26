import java.util.*;
import java.lang.*;
class Main{

   public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int  N = scan.nextInt();
        int  M = scan.nextInt();
        SegTree minSegTree = new SegTree(N);
        SegTree maxSegTree = new SegTree(N,false);
        int[] idxList = new int[N+1];
        for(int i = 0; i< N; i++){
          int p  = scan.nextInt();
          minSegTree.update(i,p);
          maxSegTree.update(i,p);
          idxList[p]=i;// val -> index
        }
 //       minSegTree.print();
        //maxSegTree.print();
        //if(0!=1) return;
        
        // M operation
	  for(int i=0;i<M;i++){
	    int L = scan.nextInt()-1;
	    int R = scan.nextInt();
        
        int mn=minSegTree.find(L,R);
        int mnIdx = idxList[mn];
        int mx=maxSegTree.find(L,R);
        int mxIdx = idxList[mx];
        
  //      System.out.println("L-R "+L+"-"+R+" MIN "+mn +" at "+ mnIdx +", MAX "+ mx + " at "+mxIdx );
        
        //swap  min and max
        minSegTree.update(mnIdx,mx);
        minSegTree.update(mxIdx,mn);
        idxList[mx]=mnIdx;
        maxSegTree.update(mxIdx,mn);
        maxSegTree.update(mnIdx,mx);
        idxList[mn]=mxIdx;
        
//        minSegTree.print();
	  }
      int[] ans = new int[N];
      for(int i=1;i<=N;i++){
        ans[idxList[i]]=i;
      }
      //minSegTree.print();
      //System.out.println("--------");
      //maxSegTree.print();

      for(int i=0;i<N;i++){
         System.out.print(ans[i]);
         if(i<N-1){
          System.out.print(" ");
         }
      }
    }
       static class SegTree{
        int[] tree;
        int size;
        boolean isMinSeg =true;
        SegTree(int n){
          this(n,true);
        }
        SegTree(int n,boolean isMinSeq){
            tree = new int[n*4];
            Arrays.fill(tree,isMinSeq?Integer.MAX_VALUE:Integer.MIN_VALUE);
            this.isMinSeg=isMinSeq;
            size=n;
        }

        void print(){
          for(int i=0; i<tree.length;i++){
            System.out.println(i+":"+tree[i]);
          }
        }

        //root with default
        void update(int idx,int val){
          update(idx,val,1,0,size);
        }
        void update(int idx,int val,int n, int l,int r){
          //base only 1 in range, update and return
          if(r-l ==1){
            tree[n]=val;
            return ;
          }
          // depending on the index , check left or rigth node
          if(idx < (l+r)/2){
             update(idx,val,n*2,l,(l+r)/2);
          }else{
             update(idx,val,n*2+1,(l+r)/2,r);
          }
          // one child nodes updated, fnd min and update the val
          if(isMinSeg){
            tree[n]=Math.min(tree[n*2],tree[n*2+1]);
          }else{
            tree[n]=Math.max(tree[n*2],tree[n*2+1]);
          }
        }
        int find(int ql,int qr){
            return find(ql,qr,1,0,size);
        }
        int find(int ql,int qr,int node, int l,int r){
            // query range is out of my management , return no answer/inf
            if(qr <= l || ql >= r){
              return isMinSeg?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }else if(ql<=l && qr>=r){// my mananging are is subset of query range
              return tree[node];
            }
            // I need check with my subordinatea. because my min comes from out of rage
            int lmin = find(ql,qr,node*2,l,(l+r)/2);
            int rmin = find(ql,qr,node*2+1,(l+r)/2,r);
            return isMinSeg?Math.min(lmin,rmin):Math.max(lmin,rmin);
        }
     }
}
