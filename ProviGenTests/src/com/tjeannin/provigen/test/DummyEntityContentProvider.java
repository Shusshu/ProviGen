package com.tjeannin.provigen.test;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.Type;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Contract;
import com.tjeannin.provigen.exception.InvalidContractException;

public class DummyEntityContentProvider extends ProviGenProvider {

	public DummyEntityContentProvider() throws InvalidContractException {
		super(DummyContract.class);
	}

	@Contract(version = 1)
	public static interface DummyContract extends ProviGenBaseContract {

		public static final String CONTENT_URI_STRING = "content://com.test.entity/table_entity";

		@Column(type = Type.INTEGER)
		public static final String MY_INT = "int";

		@Column(type = Type.TEXT)
		public static final String MY_STRING = "string";

		@Column(type = Type.REAL)
		public static final String MY_DOUBLE = "double";

		@Column(type = Type.INTEGER)
		public static final String MY_BOOLEAN = "boolean";

		@Column(type = Type.TEXT)
		public static final String MY_URI = "uri";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
	}
}
