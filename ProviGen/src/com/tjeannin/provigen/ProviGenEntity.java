package com.tjeannin.provigen;

import java.lang.reflect.Method;

import android.content.ContentValues;
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

	public ContentValues getContentValues() {

		ContentValues contentValues = new ContentValues();

		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			Persist persist = method.getAnnotation(Persist.class);
			if (persist != null) {

				Object result = null;
				try {
					result = method.invoke(this, (Object[]) null);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				if (result != null) {
					if (result instanceof Boolean) {
						contentValues.put(persist.columnName(), (Boolean) result);
					} else if (result instanceof Byte) {
						contentValues.put(persist.columnName(), (Byte) result);
					} else if (result instanceof byte[]) {
						contentValues.put(persist.columnName(), (byte[]) result);
					} else if (result instanceof Double) {
						contentValues.put(persist.columnName(), (Double) result);
					} else if (result instanceof Float) {
						contentValues.put(persist.columnName(), (Float) result);
					} else if (result instanceof Integer) {
						contentValues.put(persist.columnName(), (Integer) result);
					} else if (result instanceof Long) {
						contentValues.put(persist.columnName(), (Long) result);
					} else if (result instanceof Short) {
						contentValues.put(persist.columnName(), (Short) result);
					} else if (result instanceof String) {
						contentValues.put(persist.columnName(), (String) result);
					} else if (result instanceof Uri) {
						contentValues.put(persist.columnName(), ((Uri) result).toString());
					} else {
						new InvalidEntityException("The " + method.getName() + " method return type is not supported.").printStackTrace();
					}
				}
			}
		}
		return contentValues;
	}
}
