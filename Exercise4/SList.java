package edalib.list.singlelink;

import edalib.list.interfaces.IList;

public class SList<E> implements IList<E> {

	protected SNode<E> firstNode = null;

	public SList() {
		firstNode = null;
	}

	public SNode<E> getFirstNode() {
		return firstNode;
	}

	public void addFirst(E newElem) {
		SNode<E> newNode = new SNode<E>(newElem);
		newNode.nextNode = firstNode;
		firstNode = newNode;
	}

	public void addLast(E newElem) {
		if (isEmpty())
			addFirst(newElem);
		else {
			SNode<E> node = new SNode<E>(newElem);
			SNode<E> last = firstNode;
			while (last.nextNode != null) {
				last = last.nextNode;
			}
			last.nextNode = node;
		}
	}

	public void removeFirst() {
		if (!isEmpty()) {
			firstNode = firstNode.nextNode;
		}
	}

	public boolean isEmpty() {
		return (firstNode == null);
	}

	/**
	 * Inserts an element into the list at the position specified by the index parameter.
	 * 
	 * @param index Only allows non-zero positive values smaller than or equal to the size of the list.
	 * @param newElem
	 */
	@Override
	public void insertAt(int index, E newElem) {
		if (index == 1){
			this.addFirst(newElem);
		}
		else if (index <= 0){
			System.out.println("List does not contain position " + index);
		}
		else if (index > this.getSize()){
			System.out.println("Can't insert element in position " + index + " , as list only has size " + this.getSize());
		}
		else {
			SNode<E> previousNode = firstNode; //Node previous to indexNode.
			SNode<E> indexNode = firstNode; //Finds the location of the index where we want to insert the insertNode.
			SNode<E> insertNode = new SNode<E>(newElem); //Node containing the element we want to insert.
			for (int ii = 1; ii < index; ii++){ //Points indexNode to the position specified by the index parameter.
				indexNode = indexNode.nextNode;
			}
			while (previousNode.nextNode != indexNode){ //Points previousNode to the position prior to indexNode.
				previousNode = previousNode.nextNode;
			}
			insertNode.nextNode = indexNode; //The element next to insertNode will be the indexNode.
			previousNode.nextNode = insertNode; //The element next to previousNode will be the insertNode.
		}
	}

	/**
	 * Returns true if the list contains the object specified as a parameter.
	 * Returns false if the list is empty or does not contain the object.
	 * @param elem Object that is being looked for.
	 * @return
	 */
	@Override
	public boolean contains(E elem) {
		// TODO conflicts with removeAll.
		if (!this.isEmpty()){ //If the list is not empty, proceed with the following code.
			SNode<E> nodeTraveler = firstNode; //This node will run through the whole list.
			while (nodeTraveler.nextNode != null){ //This while doesn't check the element in the last node.
				if (nodeTraveler.elem == elem){
					return true;
				}
				nodeTraveler = nodeTraveler.nextNode;
			}
			if (nodeTraveler.elem == elem){ //This checks the element in the last node.
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the last element in the list.
	 */
	@Override
	public void removeLast() {
		SNode<E> nodeTraveler = firstNode; //This node will go to the previous to last node in order to change its .nextNode pointer.
		if (this.getSize() == 1){
			this.removeFirst();
		}
		else {
			while (nodeTraveler.nextNode != null){ //Checks if the position next to the node is null, signifying the end of the list.
				if (nodeTraveler.nextNode.nextNode == null){ //Checks if it is the previous to last node.
					nodeTraveler.nextNode = null; //If it is, point it to null in order to remove the last node.
				}
				else {
					nodeTraveler = nodeTraveler.nextNode;
				}
			}
		}
	}

	/**
	 * Removes all occurrences of the object specified as a parameter.
	 * @param elem
	 */
	@Override
	public void removeAll(E elem) {
		if (this.contains(elem)){  //Will only function if the list contains the element.
			int index = 1; //Sets index to starting position.
			SNode<E> nodeTraveler = firstNode; //nodeTraveler will run through the whole list.
			while (nodeTraveler.nextNode != null){  //While nodeTraveler isn't at the final node.
				if (nodeTraveler.getElement() == elem){ //If the element in the node matches the element that needs to be removed, remove it and move nextNode one position further down the list.
					this.removeAt(index);
					nodeTraveler = nodeTraveler.nextNode; //Index doesn't need to be increased here because the list is being rolled back.
				}
				else { //If the element does not match, move it down the list and increase the index.
					nodeTraveler = nodeTraveler.nextNode;
					index++;
				}
			}
		}


	}
	/**
	 * Removes the node at the position given as a parameter.
	 * If index is less than or equal to 0, method will not remove anything.
	 * If index exceeds list size, method will not remove anything.
	 */
	@Override
	public void removeAt(int index) {
		if (index <= 0){
			System.out.println("Nothing to remove at position " + index);
		}
		else if (index > this.getSize()){
			System.out.println("Index " + index + " exceeds list size: " + this.getSize());
		}
		else if (index == 1){
			this.removeFirst();
		}
		else {
			SNode<E> previousNode = firstNode; //Node previous to the one that will be removed.
			SNode<E> removeNode = firstNode; //Node the will be removed.
			for (int ii = 1; ii < index; ii++){ //Locates the node to be removed.
				removeNode = removeNode.nextNode;
			}
			while (previousNode.nextNode != removeNode){ //Locates the node previous to the one being removed.
				previousNode = previousNode.nextNode;
			}
			previousNode.nextNode = removeNode.nextNode; //Sets the previous node's next pointer to the node after the one we want to remove.
		}
	}

	/**
	 * Returns the size of the list.
	 */
	@Override
	// TODO If an element in the list can be null, imagine a list of 10 null elements. How do we know how to count them?
	public int getSize() {
		int size;
		if (firstNode == null){ 
			size = 0;
		}
		else {
			size = 1; //If firstNode is not null, default size will be 1.
			SNode<E> nodeTraveler = firstNode; //nodeTraveler will run through the whole list.
			while (nodeTraveler.nextNode != null){  //As long as the node that comes after nodeTraveler isn't null, we haven't reached the end of the list.
				size++;
				nodeTraveler = nodeTraveler.nextNode;
			}
		}
		return size;
	}

	/**
	 * Returns the position of the object specified as a parameter.
	 * Will return -1 if object is not contained in the list.
	 */
	@Override
	public int getIndexOf(E elem) {
		if (this.contains(elem)){
			if (firstNode.getElement() == elem){
				return 1;
			}
			else {
				int index = 1;
				SNode<E> nodeTraveler = firstNode;
				while (nodeTraveler.nextNode.getElement() != elem){
					index++;
					nodeTraveler = nodeTraveler.nextNode;
				}
				index++;
				// TODO Complete the code of this method
				return index;
			}
		}
		else {
			return -1;
		}
	}

	/**
	 * Returns the first element in the list.
	 * Returns null if list doesn't contain any elements.
	 */
	@Override
	public E getFirst() {
		if (this.getSize() == 0){
			return null;
		}
		else {
			return firstNode.getElement();
		}
	}

	/**
	 * Returns the last element in the list.
	 */
	@Override
	public E getLast() {
		SNode<E> nodeTraveler = firstNode;
		while (nodeTraveler.nextNode != null){
			nodeTraveler = nodeTraveler.nextNode;
		}
		return nodeTraveler.getElement();
	}

	/**
	 * Returns the element at the position specified by the index parameter.
	 * Will return null if index exceeds the size of the list.
	 */
	@Override
	public E getAt(int index) {
		if (index <= this.getSize()){
			SNode<E> nodeTraveler = firstNode;
			for (int ii = 1; ii < index; ii++){
				nodeTraveler = nodeTraveler.nextNode;
			}
			return nodeTraveler.getElement();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the list as a string. 
	 * If list does not contain any elements, returns null.
	 */
	@Override
	public String toString() {
		String str = "";
		SNode<E> nodeTraveler = firstNode;
		if (this.getSize() == 0){
			return null;
		}
		else {
			while (nodeTraveler.nextNode != null){ //This while doesn't check the element in the last node.
				str = str + nodeTraveler.getElement() + " ";
				nodeTraveler = nodeTraveler.nextNode;
			}
			str = str + nodeTraveler.getElement(); //This will add the last element to the string.
			return str;
		}
	}

	public void show() {
		SNode<E> nodeTraveler = firstNode;
		while (nodeTraveler.nextNode != null) {
			System.out.print(nodeTraveler.getElement());
			nodeTraveler = nodeTraveler.nextNode;
		}
		System.out.println(nodeTraveler.getElement());
	}
	
}
