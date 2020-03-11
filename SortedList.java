package adts;

import nodes.DLLNode;
import interfaces.ListInterface;

public class SortedList<E> implements ListInterface<E> {

	protected DLLNode<E> head;
	protected DLLNode<E> tail;
	protected DLLNode<E> ipos;
	protected DLLNode<E> bpos;
	
	protected int size;
	
	public SortedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void add(E element) {
		
		DLLNode<E> newNode = new DLLNode<E>(element);
	
		switch(size()) {	
			case 0:
				head = newNode;
				break;
			case 1:
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
			default:
				DLLNode<E> ptr = head;
				while(ptr.getNext() != null) {
					if (((Comparable<E>)element).compareTo(ptr.getInfo()) > 0) {
						ptr = ptr.getNext();
						continue;
					}
					break;	
				}
				if(ptr.getPrev() == null) {
					newNode.setNext(ptr);
					ptr.setPrev(newNode);
					head = newNode;
				} else if (ptr.getNext() == null){
					newNode.setPrev(ptr);
					ptr.setNext(newNode);
					tail = newNode;
				} else {
					ptr.getPrev().setNext(newNode);
					newNode.setPrev(ptr.getPrev());
					newNode.setNext(ptr);
					ptr.setPrev(newNode);
				}
			}
		
		size++;
		resetIterator();
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
