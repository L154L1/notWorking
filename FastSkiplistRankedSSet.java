//package comp2402a3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * An implementation of skiplists for searching
 *
 * @author morin
 *
 * @param <T>
 */
public class FastSkiplistRankedSSet<T> implements RankedSSet<T> {
	protected Comparator<T> c;

	@SuppressWarnings("unchecked")
	protected static class Node<T> {
		T x;
		Node<T>[] next;

		int[] length;
		public Node(T ix, int h) {
			x = ix;
			next = (Node<T>[])Array.newInstance(Node.class, h+1);
			length = new int[h+1];
		}
		public int height() {
			return next.length - 1;
		}
	}

	/**
	 * This node<T> sits on the left side of the skiplist
	 */
	protected Node<T> sentinel;

	/**
	 * The maximum height of any element
	 */
	int h;

	/**
	 * The number of elements stored in the skiplist
	 */
	int n;		//i + 1

	/**
	 * A source of random numbers
	 */
	Random rand;

	/**
	 * Used by add(x) method
	 */
	protected Node<T>[] stack;

	@SuppressWarnings("unchecked")
	public FastSkiplistRankedSSet(Comparator<T> c) {
		this.c = c;
		n = 0;
		sentinel = new Node<T>(null, 32);
		stack = (Node<T>[])Array.newInstance(Node.class, sentinel.next.length);
		h = 0;
		rand = new Random();
	}

	public FastSkiplistRankedSSet() {
		this(new DefaultComparator<T>());
	}

	/**
	 * Find the node<T> u that precedes the value x in the skiplist.
	 *
	 * @param x - the value to search for
	 * @return a node<T> u that maximizes u.x subject to
	 * the constraint that u.x < x --- or sentinel if u.x >= x for
	 * all node<T>s x
	 */
	protected Node<T> findPredNode(T x) {
		Node<T> u = sentinel;
		int r = h;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,x) < 0)
				u = u.next[r];   // go right in list r
			r--;               // go down into list r-1

		}
		return u;
	}

	Node<T> findPred(int i){
		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while(r>=0){
			while(u.next[r] != null && j + u.length[r]<i){
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return u;
	}

	int findPredLength(T x){
		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while(r>=0){
			while(u.next[r] != null && c.compare(u.next[r].x,x) < 0){
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return j;
	}

	public T find(T x) {
		Node<T> test = sentinel;

		

		// if x is the last thing in the list, return itself's value
		// if x is larger than the last value in the list, return null

		Node<T> u = findPredNode(x);
		return u.next[0] == null ? null : u.next[0].x;
	}

	public T findGE(T x) {
		if (x == null) {   // return first node<T>
			return sentinel.next[0] == null ? null : sentinel.next[0].x;
		}
		return find(x);
	}

	public T findLT(T x) {
		if (x == null) {  // return last node<T>
			Node<T> u = sentinel;
			int r = h;
			while (r >= 0) {
				while (u.next[r] != null)
					u = u.next[r];
				r--;
			}
			return u.x;
		}
		return findPredNode(x).x;
	}

	public T get(int i) {
		// This is too slow and making it faster will take changes to this
		// structure
		// Iterator<T> it = this.iterator();
		// for (int j = 0; j < i; j++) {
		// 	it.next();
		// }
		// return it.next();
	
		if(i < 0 || i > (n-1) || n == 0){			// if out of bounds, or nothing in list yet
			return null;
		}

		Node<T> u = sentinel;
		int r = h;
		int j = -1;		// sentinel's index is -1
		while(r>=0){				// find pred
			while(u.next[r] != null && j + u.length[r]<i){
				j += u.length[r];
				u = u.next[r];
			}
			r--;
		}
		return u.next[0].x;
	}

	public int rank(T x) {
		// This is too slow and making it faster will take changes to this
		// structure
		// Iterator<T> it = this.iterator();
		// int r = 0;
		// while (it.hasNext() && c.compare(it.next(), x) < 0) {
		// 	r++;
		// }
		// return r;
		if(x == null){
			return 0;
		}
		return findPredLength(x)+1;
		
	}





	public boolean remove2(T x) {		// provided with code version
		if(x == null){
			return false;
		}

		boolean removed = false;
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;
		while (r >= 0) {
			while (u.next[r] != null
			       && (comp = c.compare(u.next[r].x, x)) < 0) {
				u = u.next[r];
			}
			if (u.next[r] != null && comp == 0) {
				removed = true;
				u.next[r] = u.next[r].next[r];
				if (u == sentinel && u.next[r] == null)
					h--;  // height has gone down
			}
			r--;
		}
		if (removed) n--;
		return removed;
	}


	public boolean remove(T x){		// my version
		System.out.println("remove x has been called");
		// find index where x is
		if(x == null){
			return false;
		}

		if (sentinel.next[0] == null){			// if list is empty, exit
			System.out.println("Something is wrong this list is empty cannot remove");
			return false;
		}

		Node<T> u = sentinel;	// which node we are at rn
		//int k = // height of node trying to add
		// int j = -1 // sentinel's index is -1
        int i = -1;
		int r = h;
		
		// if x isnt in the list, need to exit

        // while (u.next[0] != null && c.compare(u.next[0].x, x) < 0){ // to find where x is supposed to go - which is i // TOO SLOW
        //     i++;
        //     u = u.next[0];
		// }
		

		while(r>=0){
			while(u.next[r] != null && c.compare(u.next[r].x,x) < 0){
				i += u.length[r];
				u = u.next[r];
			}
			r--;
		}

		int toSee = i+1;
		System.out.println("\ti+1 is " + toSee);
		System.out.println("\tn is " + n);

		if(i == -1 && sentinel.next[0] != null && sentinel.next[0].x != x){
			System.out.println("something is wrong i cant remove this because it is not within the bounds of the list or the list is empty");
			return false;
		} else if (i+1>=n){
			System.out.println("something is wrong i cant remove this because it is past the list");
			return false;
		}
		// call remove(index i)
		remove(i+1);
		return true;
	}


    T remove(int i) {
		System.out.println("remove i has been called for i=" + i);
        T x = null;
        Node<T> u = sentinel;
        int r = h;
        int j = -1; // index of node u
        while (r >= 0) {
            while (u.next[r] != null && j+u.length[r] < i) {
                j += u.length[r];
                u = u.next[r];
            }
            u.length[r]--;  // for the node we are removing
            if (j + u.length[r] + 1 == i && u.next[r] != null) {
                x = u.next[r].x;
                u.length[r] += u.next[r].length[r];
                u.next[r] = u.next[r].next[r];
                if (u == sentinel && u.next[r] == null)
                    h--;
            }
            r--;
        }
        n--;
        return x;
    }





	/**
	 * Simulate repeatedly tossing a coin until it comes up tails.
	 * Note, this code will never generate a height greater than 32
	 * @return the number of coin tosses - 1
	 */
	protected int pickHeight() {
		int z = rand.nextInt();
		int k = 0;
		int m = 1;
		while ((z & m) != 0) {
			k++;
			m <<= 1;
		}
		return k;
	}

	public void clear() {
		n = 0;
		h = 0;
		Arrays.fill(sentinel.next, null);
	}

	public int size() {
		return n;
	}

	public Comparator<T> comparator() {
		return c;
	}

	/**
	 * Create a new iterator in which the next value in the iteration is u.next.x
	 * TODO: Constant time removal requires the use of a skiplist finger (a stack)
	 * @param u
	 * @return
	 */
	protected Iterator<T> iterator(Node<T> u) {
		class SkiplistIterator implements Iterator<T> {
			Node<T> u, prev;
			public SkiplistIterator(Node<T> u) {
				this.u = u;
				prev = null;
			}
			public boolean hasNext() {
				return u.next[0] != null;
			}
			public T next() {
				prev = u;
				u = u.next[0];
				return u.x;
			}
			public void remove() {
				// Not constant time
				FastSkiplistRankedSSet.this.remove(prev.x);
			}
		}
		return new SkiplistIterator(u);
	}

	public Iterator<T> iterator() {
		return iterator(sentinel);
	}

	public Iterator<T> iterator(T x) {
		return iterator(findPredNode(x));
	}


	public boolean add2(T x){     // TOO SLOW // add function to match the parameter list of the add call in the main
        if (x == null){                  // to stop adding nulls
            return false;
        }
        Node<T> u = sentinel;
        int i = 0;
        int r = h;
        while (u.next[0] != null && c.compare(u.next[0].x, x) < 0){ // to find where x is supposed to go
            i++;
            u = u.next[0];
        }
        if(u.next[0] != null && c.compare(u.next[0].x, x) == 0){   // to prevent adding repeats
            return false;
        }

        Node<T> w = new Node(x, pickHeight());    // preparing for next func call
        if (w.height() >h){
            h = w.height();
        }
        add(i, w);
        return true;
    }


	public boolean add(T x){
		
		//from slow version
		if (x == null){                  // to stop adding nulls
            return false;
        }
		Node<T> u = sentinel;	// which node we are at rn
		//int k = // height of node trying to add
		// int j = -1 // sentinel's index is -1
        int i = -1;
		int r = h;
		// print what add.next is

        // while (u.next[0] != null && c.compare(u.next[0].x, x) < 0){ // to find where x is supposed to go - which is i // TOO SLOW
        //     i++;
        //     u = u.next[0];
		// }
		

		while(r>=0){
			while(u.next[r] != null && c.compare(u.next[r].x,x) < 0){
				i += u.length[r];
				u = u.next[r];
			}
			r--;
		}

        if(u.next[0] != null && c.compare(u.next[0].x, x) == 0){   // to prevent adding repeats
            return false;
        }

        Node<T> w = new Node(x, pickHeight());    // preparing for next func call
        if (w.height() >h){
            h = w.height();
        }
        add(i+1, w);
        return true;
	}

	public Node<T> add(int i, Node<T> w) {
		Node<T> u = sentinel;
		int k = w.height();
		int r = h;
		int j = -1;			// j is the index of u
		while (r>=0){
			while (u.next[r] != null && j+u.length[r] < i) {
				j += u.length[r];
				u = u.next[r];
			}
			u.length[r]++;
			if(r<=k){
				w.next[r] = u.next[r];
				u.next[r] = w;
				w.length[r] = u.length[r]-(i-j);
				u.length[r] = i-j;
			}
			r--;
		}
		n++;
		return u;
	}


	public boolean add(int i, T x) {		// not used, skipped over
		Node<T> w = new Node(x, pickHeight());
		if (w.height() >h){
			h = w.height();
		}
		add(i, w);
		return true;
	}


	// public void display(){
	// 	System.out.println("\nPRINTING...");
	// 	Node<T> u = sentinel;

	// 	for(int i = h; i>=0; i--){						// at height h
	// 		System.out.print("*_");		// sentinel
	// 		for(int j = 0; j<=n; j++){					// for all elements of list number of times
	// 			if (u.next.length < i){					// if nothing in the next array at this height for this element, print _
	// 				System.out.print("__");
	// 			} else{	
	// 				if(u.x != null){								// if something, print *
	// 					System.out.print((int)u.x);
	// 					System.out.print("_");		
	// 				// System.out.print("*_");
	// 				}
	// 			}
	// 			u = u.next[0];
	// 		}
	// 		System.out.println("");
	// 		u = sentinel;
	// 	}
	// 	System.out.println("");
	// }

	public static void main(String[] args) {

		RankedSSet<Integer> rss = new FastSkiplistRankedSSet<>();

		System.out.println("STARTING WITH PHASE 1:");
		System.out.println("in the beginning: size()=" + rss.size());
		rss.add(3);
		System.out.println("after adding 3: size()=" + rss.size());
		rss.add(17);
		System.out.println("after adding 17: size()=" + rss.size());
		rss.add(8);
		System.out.println("after adding 8: size()=" + rss.size());
		rss.add(10);
		System.out.println("after adding 10: size()=" + rss.size());
		rss.add(11);
		System.out.println("after adding 11: size()=" + rss.size());
		rss.add(16);
		System.out.println("after adding 16: size()=" + rss.size());
		rss.add(7);
		System.out.println("after adding 7: size()=" + rss.size());
		rss.add(12);
		System.out.println("after adding 12: size()=" + rss.size());

		//rss.display();

		System.out.println("AFTERWARDS:");
		System.out.print("Contents: ");
		for (Integer x : rss) {
			System.out.print(x + ",");
		}
		System.out.println("\nsize()=" + rss.size());

		for (int i = 0; i < rss.size(); i++) {
			Integer x = rss.get(i);
			System.out.println("get(" + i + ")=" + x);
		}

		
		System.out.println("find(1000)=" + rss.find(1000));
		System.out.println("find(18)=" + rss.find(18));

		System.out.println("finding every element in rss: ");
		for (int x = 0; x < 20; x++) {
			System.out.println("find(" + x + ")=" + rss.find(x));
		}

		for (Integer x = 0; x < 20; x++) {
			int i = rss.rank(x);

			System.out.println("rank(" + x + ")=" + i);
		}
		// System.out.println("get(5)=" + rss.get(5));
		// System.out.println("rank(34)=" + rss.rank(34));
		// System.out.println("rank(null)=" + rss.rank(null));
		// System.out.println("get(-3)=" + rss.get(-3));
		// System.out.println("rank(-3)=" + rss.rank(-3));


		System.out.println("\nSTARTING WITH PHASE 2:");
		rss.remove(8);
		System.out.println("after removing 8: size()=" + rss.size());
		rss.remove(3);
		System.out.println("after removing 3: size()=" + rss.size());
		rss.add(14);
		System.out.println("after adding 14: size()=" + rss.size());
		rss.remove(11);
		System.out.println("after removing 11: size()=" + rss.size());
		rss.remove(5);
		System.out.println("after removing 5: size()=" + rss.size());
		rss.remove(17);
		System.out.println("after removing 17: size()=" + rss.size());
		rss.remove(50);
		System.out.println("after removing 50: size()=" + rss.size());
		rss.remove(-11);


		//rss.display();

		System.out.println("AFTERWARDS:");
		System.out.print("Contents: ");
		for (Integer x : rss) {
			System.out.print(x + ",");
		}
		System.out.println("\nsize()=" + rss.size());

		for (int i = 0; i < rss.size(); i++) {
			Integer x = rss.get(i);
			System.out.println("get(" + i + ")=" + x);
		}

		
		System.out.println("find(1000)=" + rss.find(1000));
		System.out.println("find(18)=" + rss.find(18));

		System.out.println("finding every element in rss: ");
		for (int x = 0; x < 20; x++) {
			System.out.println("find(" + x + ")=" + rss.find(x));
		}

		for (Integer x = 0; x < 20; x++) {
			int i = rss.rank(x);

			System.out.println("rank(" + x + ")=" + i);
		}





		

		// for (Integer x = 0; x < 3*n+1; x++) {
		// 	int i = rss.rank(x);

		// 	System.out.println("rank(" + x + ")=" + i);
		// }


	}


}
