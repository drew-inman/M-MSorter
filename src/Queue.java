/* NAME: Drew Inman
 * DATE: 06/06/2019
 * PROJECT: Programming Assignment 1
 * 
 * This is the generic queue I set up. It can queue, dequeue, peek (just because)
 * and check to see if it's empty.
 */

public class Queue<T> {

	private QueueNode<T> head;
	private QueueNode<T> tail;
	int size = 0;
	
	//This method enqueues data to the queue
	public void enqueue(T data) {
		QueueNode<T> newNode = new QueueNode<T>(data);
		
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
			head.next = tail;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	//This method dequeues data from the queue
    public T dequeue() {
        T returnData = null;
        QueueNode<T> headNode = head;
        head = headNode.next;
        if (headNode == tail) {
            tail = null;
        }
        returnData = headNode.data;
        size--;
        return returnData;
    }
	
    //This methods peeks at the first element in the queue
    //It doesn't even need to exist, but whatever.
	public T peek() {
		if(head==null)
			return null;
		return head.data;
	}
	
	//This method checks to see whether the queue is empty or not.
	public Boolean isEmpty() {
		return (head == null || head.data==null);
	}
	
	//This is a private class that represents an item in the queue.
	private class QueueNode<T>{
		public T data;
		public QueueNode<T> next;
		
		public QueueNode(T data) {
			this.data = data;
		}
	}
}
