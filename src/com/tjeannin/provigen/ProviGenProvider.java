package com.tjeannin.provigen;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class ProviGenProvider extends ContentProvider {

	private SQLiteOpenHelper sqLiteOpenHelper;

	private UriMatcher uriMatcher;
	private static final int ALARM = 1;
	private static final int ALARM_ID = 2;

	public static final String DATABASE_NAME = "alarm_app";

	@Override
	public boolean onCreate() {

		sqLiteOpenHelper = new SQLiteOpenHelper(getContext(), DATABASE_NAME, null, 1) {

			private static final String DATABASE_CREATE = "CREATE TABLE " + Alarm.TABLE_NAME + " ( " +
					Alarm.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					Alarm.COLUMN_HOUR + " INTEGER, " +
					Alarm.COLUMN_MINUTE + " INTEGER, " +
					Alarm.COLUMN_SNOOZETIME + " NUMERIC, " +
					Alarm.COLUMN_RINGTIME + " NUMERIC, " +
					Alarm.COLUMN_ACTIVE + " INTEGER, " +
					Alarm.COLUMN_SNOOZED + " INTEGER, " +
					Alarm.COLUMN_NAME + " TEXT, " +
					Alarm.COLUMN_ACTIVE_DAYS + " TEXT " + " ) ";

			@Override
			public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

			}

			@Override
			public void onCreate(SQLiteDatabase database) {

				// Create alarm table.
				database.execSQL(DATABASE_CREATE);
			}
		};

		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.tjeannin.alarm", "alarm", ALARM);
		uriMatcher.addURI("com.tjeannin.alarm", "alarm/#", ALARM_ID);

		return true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

		int numberOfRowsAffected = 0;

		switch (uriMatcher.match(uri)) {
		case ALARM:
			numberOfRowsAffected = database.delete(Alarm.TABLE_NAME, selection, selectionArgs);
			break;
		case ALARM_ID:
			String alarmId = String.valueOf(ContentUris.parseId(uri));

			if (TextUtils.isEmpty(selection)) {
				numberOfRowsAffected = database.delete(Alarm.TABLE_NAME, Alarm.COLUMN_ID + " = ? ", new String[] { alarmId });
			} else {
				numberOfRowsAffected = database.delete(Alarm.TABLE_NAME, selection + " AND " + Alarm.COLUMN_ID + " = ? ",
						appendToStringArray(selectionArgs, alarmId));
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return numberOfRowsAffected;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case ALARM:
			return "vnd.android.cursor.dir/vnd." + getContext().getPackageName() + ".alarm";
		case ALARM_ID:
			return "vnd.android.cursor.item/vnd." + getContext().getPackageName() + ".alarm";
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

		switch (uriMatcher.match(uri)) {
		case ALARM:
			long alarmId = database.insert(Alarm.TABLE_NAME, null, values);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.withAppendedPath(uri, String.valueOf(alarmId));
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase database = sqLiteOpenHelper.getReadableDatabase();

		Cursor cursor = null;

		switch (uriMatcher.match(uri)) {
		case ALARM:
			cursor = database.query(Alarm.TABLE_NAME, projection, selection, selectionArgs, "", "", sortOrder);
			break;
		case ALARM_ID:
			String alarmId = String.valueOf(ContentUris.parseId(uri));
			if (TextUtils.isEmpty(selection)) {
				cursor = database.query(Alarm.TABLE_NAME, projection, Alarm.COLUMN_ID + " = ? ", new String[] { alarmId }, "", "", sortOrder);
			} else {
				cursor = database.query(Alarm.TABLE_NAME, projection, selection + " AND " + Alarm.COLUMN_ID + " = ? ",
						appendToStringArray(selectionArgs, alarmId), "", "", sortOrder);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}

		// Make sure that potential listeners are getting notified.
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase database = sqLiteOpenHelper.getWritableDatabase();

		int numberOfRowsAffected = 0;

		switch (uriMatcher.match(uri)) {
		case ALARM:
			numberOfRowsAffected = database.update(Alarm.TABLE_NAME, values, selection, selectionArgs);
			break;
		case ALARM_ID:
			String alarmId = String.valueOf(ContentUris.parseId(uri));

			if (TextUtils.isEmpty(selection)) {
				numberOfRowsAffected = database.update(Alarm.TABLE_NAME, values, Alarm.COLUMN_ID + " = ? ", new String[] { alarmId });
			} else {
				numberOfRowsAffected = database.update(Alarm.TABLE_NAME, values, selection + " AND " + Alarm.COLUMN_ID + " = ? ",
						appendToStringArray(selectionArgs, alarmId));
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown uri " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return numberOfRowsAffected;
	}

	/**
	 * Appends the given element to a copy of the given array.
	 * @param array The array to copy.
	 * @param element The element to append.
	 * @return An Array with the element appended.
	 */
	private static String[] appendToStringArray(String[] array, String element) {
		if (array != null) {
			String[] newArray = new String[array.length + 1];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			newArray[array.length] = element;
			return newArray;
		} else {
			return new String[] { element };
		}
	}

}
