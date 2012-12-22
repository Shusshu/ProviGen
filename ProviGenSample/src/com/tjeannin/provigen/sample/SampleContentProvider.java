package com.tjeannin.provigen.sample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.Type;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Contract;
import com.tjeannin.provigen.exception.InvalidContractException;

public class SampleContentProvider extends ProviGenProvider {

	public SampleContentProvider() throws InvalidContractException {
		super(SampleContract.class);
	}

	@Override
	public void onCreateDatabase(SQLiteDatabase database) {
		super.onCreateDatabase(database);

		ContentValues values = new ContentValues(2);
		values.put(SampleContract.MY_INT, 8);
		values.put(SampleContract.MY_STRING, "a super dingue string");
		database.insert("table_name", null, values);
	}

	@Contract(version = 1)
	public static interface SampleContract extends ProviGenBaseContract {

		@Column(type = Type.INTEGER)
		public static final String MY_INT = "int";

		@Column(type = Type.TEXT)
		public static final String MY_STRING = "string";

		@Column(type = Type.REAL)
		public static final String MY_REAL = "hour";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse("content://com.tjeannin.provigen.sample/table_name");

	}

}
