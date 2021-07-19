package ADT;

public class BST<T extends Comparable<T>> {
	public Node<T> root;
	
	public static class Node<T>{
		private int key;
		private Node<T> left, right, parent;
		private int size;
		
		public Node(int key) {
			this.key = key;
			this.size = size;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
	
	public BST() {
		root = null;
	}
	
	// ADD(S,x): add element x to S, if it is not present already
	public Node<T> add(Node<T> root,int x) {
		// if tree is empty
		if (root == null) {
			root = new Node<>(x);
			return root;
		}
		if (x < root.key) {
			root.left = add(root.left, x);
		}
		else if (x > root.key) {
			root.right = add(root.right, x);
		}
		return root;
	}


	
	public void add(int x) {
		root = add(root,x);
	}
		
	
	public Node<T> min(Node<T> x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	public Node<T> max(Node<T> x){
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}
	
	public int findHeight(){
	    if(this.setEmpty()){
	        return 0;
	    }
	    else{
	        Node<T> node = root;
	        return findHeight(node);
	    }
	}
	public int findHeight(Node<T> aNode) {
		int heightLeft = 0;
	    int heightRight = 0;
	    if(aNode.left!=null)
	        heightLeft = findHeight(aNode.left);
	    if(aNode.right!=null)
	        heightRight = findHeight(aNode.right);
	    if(heightLeft > heightRight){
	        return heightLeft+1;
	    }
	    else{
	        return heightRight+1;
	    }
		
	}
	
	public void TRANSPLANT(Node<T> u, Node<T> v) {
		if (u.parent == null) {
			this.root = v;
		}
		else if(u == u.parent.left){
			u.parent.left = v;
		}
		else {
			u.parent.right = v;
		}
		if(v != null) {
			v.parent = u.parent;
		}
	}
	
	// REMOVE(S,x): remove element x from S, if it is present
	public void remove(int x) {
		if(isElement(x)) {
			Node<T> nodeX = new Node<T>(x);
			if (nodeX.left == null){
				this.TRANSPLANT(nodeX, nodeX.right);
			}
			else if (nodeX.right == null) {
				this.TRANSPLANT(nodeX, nodeX.left);
			}
			else {
				Node<T> nodeY = min(nodeX.right);
				if (nodeY.parent != nodeX) {
					this.TRANSPLANT(nodeY, nodeY.right);
					nodeY.right = nodeY.right;
					nodeY.right.parent = nodeY;
				}
				this.TRANSPLANT(nodeX, nodeY);
				nodeY.left = nodeY.left;
				nodeY.left.parent = nodeY;
			}
		}
		
	}
	
	// IS-ELEMENT(S,x): check whether element x is in set S
	public boolean isElement(int x) {
        return isElement(root, x);
    }
	public boolean isElement(Node<T> node, int x) {
		if (node == null) {
			return false;
		}
		if (x == node.key) {
            return true;
        }
        if(x < node.key) {
            return isElement(node.left, x);
        }
        else {
            return isElement(node.right, x);
        }
	}

	
	// prints in order
	public void print(Node<T> root){
		if (root != null) {
			print(root.left);
			System.out.println(root.key);
			print(root.right);
		}
	}
	
	public void print() {
		print(root);
	}

	// SET-EMPTY(S): check whether set S has no elements
	public boolean setEmpty(){
		return root == null;
	}

	// SET-SIZE(S): return the number of elements of set S
	public int setSize(){
		return root.size;
	}
	
	// UNION(S,T): return the union of sets S and T
	public void traverseBinaryTreeUnion(BST<T> union, Node<T> x) {
		if (x == null){
			return;
		}
		traverseBinaryTreeUnion(union, x.left);
		union.add(x.key);
		traverseBinaryTreeUnion(union, x.right);	
	}
	
	public BST<T> union(BST<T> S, BST<T> T){
		BST<T> union = new BST<T>();
		Node<T> temp = S.root;
		traverseBinaryTreeUnion(union, temp);
		
		Node<T> temp2 = T.root;
		traverseBinaryTreeUnion(union, temp2);
		return union;
	}
	
	// INTERSECTION (S,T): return the intersection of sets S and T
	public void traverseBinaryTreeIntersection(BST<T> S, BST<T> T, BST<T> intersection, Node<T> x) {
		if (x == null){
			return;
		}
		traverseBinaryTreeUnion(intersection, x.left);
		if(T.isElement(x.key) && !intersection.isElement(x.key)) {
			intersection.add(x.key);
		}
		traverseBinaryTreeUnion(intersection, x.right);	
	}
	
	public BST<T> INTERSECTION(BST<T> S, BST<T> T){
		BST<T> intersection = new BST<T>();
		Node<T> temp = S.root;
		traverseBinaryTreeIntersection(S,T,intersection, temp);
		
		Node<T> temp2 = T.root;
		traverseBinaryTreeIntersection(T,S,intersection, temp2);
		return intersection;
	}
	
	// DIFFERENCE(S,T): returns the difference of sets S and T
	public void traverseBinaryTreeDifference(BST<T> S, BST<T> T, BST<T> difference, Node<T> x) {
		if (x == null){
			return;
		}
		traverseBinaryTreeUnion(difference, x.left);
		if(!T.isElement(x.key) && !difference.isElement(x.key)) {
			difference.add(x.key);
		}
		traverseBinaryTreeUnion(difference, x.right);	
	}
	
	public BST<T> DIFFERENCE(BST<T> S, BST<T> T){
		BST<T> difference = new BST<T>();
		Node<T> temp = S.root;
		traverseBinaryTreeIntersection(S,T,difference, temp);
		
		Node<T> temp2 = T.root;
		traverseBinaryTreeIntersection(T,S,difference, temp2);
		return difference;
	}
	
	// SUBSET(S,T): check whether set S is a subset of set T
	public void traverseBinaryTreeSubset(BST<T> S, BST<T> T, boolean isSubset, Node<T> x) {
		if (x == null){
			return;
		}
		traverseBinaryTreeSubset(S, T, isSubset, x.left);
		if(!T.isElement(x.key)) {
			isSubset = false;
		}
		traverseBinaryTreeSubset(S, T, isSubset, x.right);
	}
	
	public boolean SUBSET(BST<T> S, BST<T> T){
		Node<T> temp = S.root;
		boolean isSubset = true;
		traverseBinaryTreeSubset(S,T, isSubset, temp);
		return isSubset;
	}
	
}