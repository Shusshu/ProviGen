package com.tjeannin.provigen.test;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.test.EntityContentProvider.EntityContract;

public class Entity extends ProviGenEntity {

	@Column(name = EntityContract.MY_INT)
	private int myInt;

	@Column(name = EntityContract.MY_STRING)
	private String myString;

	@Column(name = EntityContract.MY_REAL)
	private float myReal;

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public float getMyReal() {
		return myReal;
	}

	public void setMyReal(float myReal) {
		this.myReal = myReal;
	}

}
