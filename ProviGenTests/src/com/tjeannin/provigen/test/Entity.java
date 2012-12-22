package com.tjeannin.provigen.test;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.test.EntityContentProvider.EntityContract;

public class Entity extends ProviGenEntity {

	private int myInt;

	private String myString;

	private double myReal;

	@Column(name = EntityContract.MY_INT)
	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	@Column(name = EntityContract.MY_STRING)
	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	@Column(name = EntityContract.MY_REAL)
	public double getMyReal() {
		return myReal;
	}

	public void setMyReal(double myReal) {
		this.myReal = myReal;
	}

}
