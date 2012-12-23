package com.tjeannin.provigen.test;

import android.database.Cursor;
import android.net.Uri;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.annotation.Entity;
import com.tjeannin.provigen.annotation.Persist;
import com.tjeannin.provigen.test.DummyEntityContentProvider.DummyContract;

@Entity(contentUri = DummyContract.CONTENT_URI_STRING)
public class DummyEntity extends ProviGenEntity {

	@Persist(columnName = DummyContract.MY_INT)
	private int myInt;

	@Persist(columnName = DummyContract.MY_STRING)
	private String myString;

	@Persist(columnName = DummyContract.MY_DOUBLE)
	private double myDouble;

	@Persist(columnName = DummyContract.MY_BOOLEAN)
	private boolean myBoolean;

	@Persist(columnName = DummyContract.MY_URI)
	private Uri myUri;

	public DummyEntity() {
		super();
	}

	public DummyEntity(Cursor cursor) {
		super(cursor);
	}

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

	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public boolean getMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	public Uri getMyUri() {
		return myUri;
	}

	public void setMyUri(Uri myUri) {
		this.myUri = myUri;
	}
}
