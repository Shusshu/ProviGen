package com.tjeannin.provigen;

import java.lang.reflect.Method;

import android.content.ContentValues;
import android.net.Uri;

import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.exception.InvalidEntityException;

public class ProviGenEntity {

	public ContentValues getContentValues() {

		ContentValues contentValues = new ContentValues();

		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			Column column = method.getAnnotation(Column.class);
			if (column != null) {

				Object result = null;
				try {
					result = method.invoke(this, (Object[]) null);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				if (result != null) {
					if (result instanceof Boolean) {
						contentValues.put(column.name(), (Boolean) result);
					} else if (result instanceof Byte) {
						contentValues.put(column.name(), (Byte) result);
					} else if (result instanceof byte[]) {
						contentValues.put(column.name(), (byte[]) result);
					} else if (result instanceof Double) {
						contentValues.put(column.name(), (Double) result);
					} else if (result instanceof Float) {
						contentValues.put(column.name(), (Float) result);
					} else if (result instanceof Integer) {
						contentValues.put(column.name(), (Integer) result);
					} else if (result instanceof Long) {
						contentValues.put(column.name(), (Long) result);
					} else if (result instanceof Short) {
						contentValues.put(column.name(), (Short) result);
					} else if (result instanceof String) {
						contentValues.put(column.name(), (String) result);
					} else if (result instanceof Uri) {
						contentValues.put(column.name(), ((Uri) result).toString());
					} else {
						new InvalidEntityException("The " + method.getName() + " method return type is not supported.").printStackTrace();
					}
				}
			}
		}
		return contentValues;
	}
}
