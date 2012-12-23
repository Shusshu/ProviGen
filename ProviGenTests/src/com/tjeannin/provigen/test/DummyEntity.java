package com.tjeannin.provigen.test;

import android.database.Cursor;
import android.net.Uri;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.annotation.Entity;
import com.tjeannin.provigen.annotation.Persist;
import com.tjeannin.provigen.test.DummyEntityContentProvider.DummyContract;

@Entity(contentUri = DummyContract.CONTENT_URI_STRING)
public class DummyEntity extends ProviGenEntity {

	private int myInt;

	private String myString;

	private double myDouble;

	private boolean myBoolean;

	private Uri myUri;

	public DummyEntity() {
		super();
	}

	public DummyEntity(Cursor cursor) {
		super(cursor);
	}

	@Persist(columnName = DummyContract.MY_INT)
	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	@Persist(columnName = DummyContract.MY_STRING)
	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	@Persist(columnName = DummyContract.MY_DOUBLE)
	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	@Persist(columnName = DummyContract.MY_BOOLEAN)
	public boolean getMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	@Persist(columnName = DummyContract.MY_URI)
	public Uri getMyUri() {
		return myUri;
	}

	public void setMyUri(Uri myUri) {
		this.myUri = myUri;
	}
}
