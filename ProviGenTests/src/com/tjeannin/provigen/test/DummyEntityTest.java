package com.tjeannin.provigen.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockCursor;

import com.tjeannin.provigen.test.DummyEntityContentProvider.DummyContract;

public class DummyEntityTest extends AndroidTestCase {

	private ContentResolver contentResolver;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getContext().deleteDatabase("ProviGenDatabase");
		contentResolver = getContext().getContentResolver();
	}

	@Override
	protected void tearDown() throws Exception {
		getContext().deleteDatabase("ProviGenDatabase");
		super.tearDown();
	}

	public void testEntityContentProviderWorks() {

		assertEquals(0, getCount(DummyContract.CONTENT_URI));
		Uri uri = contentResolver.insert(DummyContract.CONTENT_URI, getContentValues());
		assertEquals(1, getCount(DummyContract.CONTENT_URI));
		contentResolver.delete(uri, null, null);
		assertEquals(0, getCount(DummyContract.CONTENT_URI));

	}

	public void testGetEntityContentValues() {

		DummyEntity entity = new DummyEntity();

		entity.setMyInt(123);
		entity.setMyBoolean(true);
		entity.setMyUri(Uri.parse("http://www.google.com"));
		entity.setMyString("dze");
		entity.setMyDouble(78.89);

		ContentValues contentValues = entity.getContentValues();

		assertEquals(Integer.valueOf(123), contentValues.getAsInteger(DummyContract.MY_INT));
		assertEquals(Boolean.TRUE, contentValues.getAsBoolean(DummyContract.MY_BOOLEAN));
		assertEquals("dze", contentValues.getAsString(DummyContract.MY_STRING));
		assertEquals("http://www.google.com", contentValues.getAsString(DummyContract.MY_URI));
		assertEquals(Double.valueOf(78.89), contentValues.getAsDouble(DummyContract.MY_DOUBLE));
	}

	private class DummyCursor extends MockCursor {

		@Override
		public double getDouble(int columnIndex) {
			return 8465.16;
		}

		@Override
		public int getInt(int columnIndex) {
			if (columnIndex == 3) {
				return 1;
			}
			return 888;
		}

		@Override
		public String getString(int columnIndex) {
			switch (columnIndex) {
			case 1:
				return "testString";
			case 2:
				return "www.google.fr";
			}
			return null;
		}

		@Override
		public int getColumnIndex(String columnName) {
			if (columnName.equals(DummyContract.MY_STRING)) {
				return 1;
			} else if (columnName.equals(DummyContract.MY_URI)) {
				return 2;
			} else if (columnName.equals(DummyContract.MY_BOOLEAN)) {
				return 3;
			} else {
				return 0;
			}
		}
	}

	public void testEntityConstructor() {

		DummyEntity entity = new DummyEntity(new DummyCursor());

		assertEquals(888, entity.getMyInt());
		assertEquals(true, entity.getMyBoolean());
		assertEquals("testString", entity.getMyString());
		assertEquals("www.google.fr", entity.getMyUri());
		assertEquals(8465.16, entity.getMyDouble());

		ContentValues contentValues = entity.getContentValues();

		assertEquals(Integer.valueOf(888), contentValues.getAsInteger(DummyContract.MY_INT));
		assertEquals(Boolean.TRUE, contentValues.getAsBoolean(DummyContract.MY_BOOLEAN));
		assertEquals("testString", contentValues.getAsString(DummyContract.MY_STRING));
		assertEquals("www.google.fr", contentValues.getAsString(DummyContract.MY_URI));
		assertEquals(8465.16, contentValues.getAsDouble(DummyContract.MY_DOUBLE));

	}

	private ContentValues getContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DummyContract.MY_INT, 1456);
		contentValues.put(DummyContract.MY_STRING, "hfdzue");
		contentValues.put(DummyContract.MY_DOUBLE, 1456.45);
		contentValues.put(DummyContract.MY_BOOLEAN, false);
		contentValues.put(DummyContract.MY_URI, "www.google.com");
		return contentValues;
	}

	private int getCount(Uri uri) {
		Cursor cursor = contentResolver.query(uri, null, "", null, "");
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

}
