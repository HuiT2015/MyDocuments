package com.ex.tree;

public class BinaryNode<AnyType> {

	AnyType element;
	BinaryNode<AnyType> left;
	BinaryNode<AnyType> right;
	
	public BinaryNode(AnyType element) {
		this(element, null, null);
	}

	public BinaryNode(AnyType element, BinaryNode<AnyType> left,
			BinaryNode<AnyType> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
}
