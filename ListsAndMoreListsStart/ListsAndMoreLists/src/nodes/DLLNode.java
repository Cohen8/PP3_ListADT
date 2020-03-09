package nodes;

public class DLLNode<E> {
	//THIS IS THE RED ONE FROM THE SCREENCAP, this was the one you sent over canvas
	private E info;
	private DLLNode<E> next;
	private DLLNode<E> prev;
	
	public DLLNode(E info) {
		this.info = info;
		next = null;
		prev = null;
	}
	
	public void setinfo(E info) {
		this.info = info;
		
	}
	
	public E getInfo() {
		return info;
	}
	
	public void setNext(DLLNode<E> reference) {
		this.next = reference;
	}
	
	public DLLNode<E> getNext() {
		return next;
	}
	
	public void setPrev(DLLNode<E> reference) {
		this.next = reference;
	}
	
	public DLLNode<E> getPrev() {
		return prev;
	}

}
