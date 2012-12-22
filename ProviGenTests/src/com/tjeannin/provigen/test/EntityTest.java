package com.tjeannin.provigen.test;

import com.tjeannin.provigen.test.EntityContentProvider.EntityContract;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class EntityTest extends AndroidTestCase {

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

		assertEquals(0, getCount(EntityContract.CONTENT_URI));
		Uri uri = contentResolver.insert(EntityContract.CONTENT_URI, getContentValues());
		assertEquals(1, getCount(EntityContract.CONTENT_URI));
		contentResolver.delete(uri, null, null);
		assertEquals(0, getCount(EntityContract.CONTENT_URI));

	}

	public void testGetEntityContentValues() {

		Entity entity = new Entity();
		entity.setMyInt(123);
		entity.setMyReal(78.89);
		entity.setMyString("dze");

		ContentValues contentValues = entity.getContentValues();

		assertEquals(Integer.valueOf(123), contentValues.getAsInteger(EntityContract.MY_INT));
		assertEquals(Double.valueOf(123), contentValues.getAsInteger(EntityContract.MY_REAL));
		assertEquals("dze", contentValues.getAsInteger(EntityContract.MY_STRING));
	}

	private ContentValues getContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(EntityContract.MY_INT, 1456);
		contentValues.put(EntityContract.MY_STRING, "hfdzue");
		contentValues.put(EntityContract.MY_REAL, 1456.45);
		return contentValues;
	}

	private int getCount(Uri uri) {
		Cursor cursor = contentResolver.query(uri, null, "", null, "");
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

}
