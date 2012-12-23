package com.tjeannin.provigen.test;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.annotation.Persist;
import com.tjeannin.provigen.test.DummyEntityContentProvider.EntityContract;

public class DummyEntity extends ProviGenEntity {

	private int myInt;

	private String myString;

	private double myDouble;

	private boolean myBoolean;

	private Uri myUri;

	@Persist(columnName = EntityContract.MY_INT)
	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	@Persist(columnName = EntityContract.MY_STRING)
	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	@Persist(columnName = EntityContract.MY_DOUBLE)
	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	@Persist(columnName = EntityContract.MY_BOOLEAN)
	public boolean getMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	@Persist(columnName = EntityContract.MY_URI)
	public Uri getMyUri() {
		return myUri;
	}

	public void setMyUri(Uri myUri) {
		this.myUri = myUri;
	}
}
