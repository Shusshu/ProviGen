package com.tjeannin.provigen;

import java.lang.reflect.Field;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Entity;
import com.tjeannin.provigen.annotation.Persist;
import com.tjeannin.provigen.exception.InvalidEntityException;

/**
 * Base class for a {@link ProviGenProvider} {@link Entity}.<br/>
 * You should <b>annotate implementations of this class with the {@link Entity} annotation</b> to specify the matching {@link ContentUri}.
 */
public class ProviGenEntity {

	public ProviGenEntity() {
	}

	public ProviGenEntity(Cursor cursor) {

	}

	public ContentValues getContentValues() {

		ContentValues contentValues = new ContentValues();

		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Persist persist = field.getAnnotation(Persist.class);
			if (persist != null) {

				Object value = new Object();
				try {
					field.setAccessible(true);
					value = field.get(this);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				if (value != null) {
					if (value instanceof Boolean) {
						contentValues.put(persist.columnName(), (Boolean) value);
					} else if (value instanceof Byte) {
						contentValues.put(persist.columnName(), (Byte) value);
					} else if (value instanceof byte[]) {
						contentValues.put(persist.columnName(), (byte[]) value);
					} else if (value instanceof Double) {
						contentValues.put(persist.columnName(), (Double) value);
					} else if (value instanceof Float) {
						contentValues.put(persist.columnName(), (Float) value);
					} else if (value instanceof Integer) {
						contentValues.put(persist.columnName(), (Integer) value);
					} else if (value instanceof Long) {
						contentValues.put(persist.columnName(), (Long) value);
					} else if (value instanceof Short) {
						contentValues.put(persist.columnName(), (Short) value);
					} else if (value instanceof String) {
						contentValues.put(persist.columnName(), (String) value);
					} else if (value instanceof Uri) {
						contentValues.put(persist.columnName(), ((Uri) value).toString());
					} else {
						new InvalidEntityException("The " + field.getName() + " method return type is not supported.").printStackTrace();
					}
				}
			}
		}
		return contentValues;
	}
}
