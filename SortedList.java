package adts;

import nodes.DLLNode;
import interfaces.ListInterface;

public class SortedList<E> implements ListInterface<E> {

	protected DLLNode<E> head;  
	protected DLLNode<E> tail;
	protected DLLNode<E> ipos; 	// Iterator position
	protected DLLNode<E> bpos; 	// Back iterator position
	protected int size;		// Number of elements
	
	public SortedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void add(E element) {
		
		DLLNode<E> newNode = new DLLNode<E>(element);
					// Protocol for adding more than one element is
		switch(size()) {	// different for a list of size 0, 1, and many. 
			case 0:		// No boolean logic because list is empty.
				head = newNode;		
				break;
			case 1:		// There is 1 element in the list. The new element
					// will be placed either before or after.
				if(((Comparable<E>)element).compareTo(head.getInfo()) > 0) {	
					head.setNext(newNode);
					newNode.setPrev(head);
					tail = newNode;
				} else {
					newNode.setNext(head);
					head.setPrev(newNode);
					tail = head;
					head = newNode;
				}
				break;	
			default:	// There are many elements in the list.
					// The new element could belong anywhere.
				DLLNode<E> ptr = head;
				while(ptr.getNext() != null) {	// Stop at the tail.
					if (((Comparable<E>)element).compareTo(ptr.getInfo()) > 0) {	
						ptr = ptr.getNext();
						continue;					
					}
					break;	
				}
				if(ptr.getPrev() == null) {	
					// Pointer is at the head.
					newNode.setNext(ptr);
					ptr.setPrev(newNode);
					head = newNode;
				} else if (ptr.getNext() == null){ 
					// Pointer is at the tail.
					newNode.setPrev(ptr);
					ptr.setNext(newNode);
					tail = newNode;
				} else {
					// Pointer is in between.
					ptr.getPrev().setNext(newNode);
					newNode.setPrev(ptr.getPrev());
					newNode.setNext(ptr);
					ptr.setPrev(newNode);
				}
			}
		size++;			// Increment number of elements.
		resetIterator();	// Reset iterator and back iterator.
		resetBackIterator();
	}

	@Override
	public boolean remove(E element) {
		// TODO Auto-generated method stub
		size--;
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetIterator() {
		ipos = head;
	}
	
	public void resetBackIterator() {
		bpos = tail;
	}

	@Override
	public E getNextItem() {
		E element = ipos.getInfo();
		ipos = ipos.getNext();
		
		if(ipos == null)
			resetIterator();
		
		return element;
	}
	
	public E getPrevItem() {
		E element = bpos.getInfo();
		bpos = bpos.getPrev();
		
		if(bpos == null)
			resetBackIterator();
		
		return element;
	}
	
	@Override
	public String toString() {
		String str = "-HEAD-";
		DLLNode<E> ptr = head;
		while(ptr != null) {
			str += "[" + ptr.getInfo() + "]";
			ptr = ptr.getNext();
		}
		str += "-TAIL-";
		return str;
	}
}
