package ADT;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T>{
	
    private Node<T> head;
    @SuppressWarnings("unused")
	private Node<T> tail;

    //keeps track of the size (of the list)
    private int size;
    
  	private static class Node<T>{
  		private int value;
  		private Node<T> next;
  		private Node<T> prev;
  		
  		public Node(int value) {
  			this.value = value;
  			
  		}
  	}
    
    /* 
     * Constructing an empty list
     */
    public DoublyLinkedList(){ 
    	head = null;
    	tail = null;
    	size = 0;
    }
    
   
    //SET-SIZE(S): return the number of elements of set S
    public int setSize() {
    	return this.size;
    }
    
    // SET-EMPTY(S): check whether set S has no elements
    public boolean isEmpty() {
    	//or if (head == null)
    	 return size == 0;
    		
    	}
    
    
	// ADD(S,x): add element x to S, if it is not present already
    public void add(int x) {
    	Node<T> nodeX = new Node<T>(x);
    	nodeX.next = this.head;
    	this.head = nodeX;
    	
    	size++;
    }
    
    
    // REMOVE(S,x): remove element x from S, if it is present
    public void remove(int x) {
    	
    	// throws exception if list is empty
    	if (size == 0) {
    		throw new NoSuchElementException();
    	}
    	
    	
    	if (isElement(x)) {
        	Node<T> nodeTemp = this.head;
        	for (int i = 0; i <= this.size; i++) {
        		if (nodeTemp == null || nodeTemp.value == x) {
        			if (nodeTemp.prev != null) {
        				nodeTemp.prev.next = nodeTemp .next;
        			}
        			else {
        				this.head = nodeTemp.next;
        			}
        			if (nodeTemp.next != null) {
        				nodeTemp.next.prev = nodeTemp.prev;
        			}
        		}
        		nodeTemp = nodeTemp.next;
        	}
    	}
    		
    	
    }
    
    //IS-ELEMENT(S,x): check whether element x is in set S
    public boolean isElement(int x) {
    	Node<T> start = this.head;
    	
    	for(int i = 0; i <= size; i++) {
    		if(start.value == x) {
    			return true;
    		}
    	}
    	return false;
    }
    
    
    // UNION(S,T): return the union of sets S and T
    public DoublyLinkedList<T> union(DoublyLinkedList<T> S, DoublyLinkedList<T> T ) {
		if (S == null) {
			return T;
		}
		else if (T == null) {
			return S;
		}
		
		
		DoublyLinkedList<T> union = new DoublyLinkedList<T>();
		Node<T> temp = S.head;
		
		for (int i = 0; i <= S.size; i++) {
			if (!union.isElement(temp.value)) {
				union.add(temp.value);
			}
			temp = temp.next;
		}
		
		Node<T> temp2 = T.head;
		for (int i = 0; i <= T.size; i++) {
			if (!union.isElement(temp2.value)) {
				union.add(temp2.value);
			}
			temp2 = temp2.next;
		}
		return union;
    }
    
    //  INTERSECTION (S,T): return the intersection of sets S and T
    public DoublyLinkedList<T> intersection(DoublyLinkedList<T> S, DoublyLinkedList<T> T ){
		DoublyLinkedList<T> intersection = new DoublyLinkedList<T>();
		Node<T> temp = S.head;
		
		for (int i = 0; i <= S.size; i++) {
			if(T.isElement(temp.value)) {
				intersection.add(temp.value);
			}
			temp = temp.next;
		}
		return intersection;
    }
    
    // DIFFERENCE(S,T): returns the difference of sets S and T
    public DoublyLinkedList<T> differences(DoublyLinkedList<T> S, DoublyLinkedList<T> T ){
		DoublyLinkedList<T> differences = new DoublyLinkedList<T>();
		Node<T> temp = S.head;
		
		for (int i = 0; i <= S.size; i++) {
			if(!T.isElement(temp.value)) {
				differences.add(temp.value);
			}
			temp = temp.next;
			
		}
		
		Node<T> temp2 = T.head;
		
		for (int i = 0; i <= S.size; i++) {
			if(!S.isElement(temp2.value)) {
				differences.add(temp2.value);
			}
			temp2 = temp2.next;
		}
	
		return differences;
    }
    
    //  SUBSET(S,T): check whether set S is a subset of set T
    public boolean subset(DoublyLinkedList<T> S, DoublyLinkedList<T> T ) {
    	Node<T> temp = S.head;
    	
    	for (int i = 0; i <= S.size; i++) {
    		if(!T.isElement(temp.value)) {
    			return false;
    		}
    		temp = temp.next;
    	}
    	return true;
    	
    }
   
    
    public void printNodes() {  
        //Node current will point to head  
        Node<T> current = head;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");  
            return;  
        }  
        System.out.println("Nodes of doubly linked list: ");  
        while(current != null) {  
            //Print each node and then go to next.  
            System.out.print(current.value + " ");  
            current = current.next;  
        }  
    }
    
}

	
	
    
    
    
    
    

