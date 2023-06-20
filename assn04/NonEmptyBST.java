package assn04;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if(_element.compareTo(element) < 0){
			if(_right.isEmpty()){
				BST<T> temp = new NonEmptyBST<T>(element);
				_right = temp;
				return this;
			}
			_right.insert(element);
		}
		if(_element.compareTo(element)>0){
			if(_left.isEmpty()){
				BST<T> temp = new NonEmptyBST<T>(element);
				_left = temp;
				return this;
			}
			_left.insert(element);
		}
		if(_element==null){
			BST<T> temp = new NonEmptyBST<T>(element);
			return temp;
		}
		return this;

	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if(_element.compareTo(element)<0){
			this._right = _right.remove(element);
		}
		if(_element.compareTo(element)>0){
			this._left = _left.remove(element);
		}
		if(_element.compareTo(element)==0){
			if(_right.isEmpty() && _left.isEmpty()){
				return new EmptyBST<T>();
			}
			else if(_right.isEmpty()){
				return this._left;
			}
			else if(_left.isEmpty()){
				return this._right;
			}
			else{
				T min = findMin(_right);
				System.out.println(min);
				this._element = min;
				this._right = _right.remove(min);
			}
		}
		return this;
	}

	private T findMin(BST<T> b){
		if(!b.getLeft().isEmpty()){
			return findMin(b.getLeft());
		}
		return b.getElement();
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element+ " ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();

	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> q = new LinkedList<>();
		q.add(this);
		while(!q.isEmpty()){
			BST<T> temp = q.remove();
			if(temp.isEmpty()){
				continue;
			}
			q.add(temp.getLeft());
			q.add(temp.getRight());
			System.out.print(temp.getElement() + " ");
		}
	}

	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
