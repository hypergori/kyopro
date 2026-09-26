import java.util.*;
import java.lang.*;
class Main{
    public static void main(String[] args){
        //1. build segmenttree
        //2. query segree
        //3. updatge segtree
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        SegTree segtree = new SegTree(n);

        for(int i=0; i<q; i++){
            int com = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            if(com == 0){
                segtree.update(x,y);
                //segtree.print();
            }else{
                int min = segtree.find(x,y+1);
                System.out.println(min);
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
            size=n;
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
