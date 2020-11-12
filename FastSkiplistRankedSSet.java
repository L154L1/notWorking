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
	int n;

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
	
		return findPred(i).next[0].x;

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

	public boolean remove(T x) {
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
        Node<T> u = sentinel;
        int i = 0;
		int r = h;
		



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
        add(i, w);
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

	public static void main(String[] args) {
		int n = 10;

		Random rand = new java.util.Random();
		RankedSSet<Integer> rss = new FastSkiplistRankedSSet<>();
		for (int i = 0; i < n; i++) {
			rss.add(rand.nextInt(3*n));
			
		}

		rss.add(null);


		System.out.print("Contents: ");
		for (Integer x : rss) {
			if(x == null){
				System.out.println("null got added");
			}
			System.out.print(x + ",");
		}
		System.out.println();

		System.out.println("size()=" + rss.size());

		for (int i = 0; i < rss.size(); i++) {
			Integer x = rss.get(i);
			System.out.println("get(" + i + ")=" + x);
		}
		
		System.out.println("rank(-3)=" + rss.rank(-3));
		System.out.println("rank(null)=" + rss.rank(null));

		for (Integer x = 0; x < 3*n+1; x++) {
			int i = rss.rank(x);

			System.out.println("rank(" + x + ")=" + i);
		}
	}
}
