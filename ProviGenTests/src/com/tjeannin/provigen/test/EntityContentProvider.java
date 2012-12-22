package com.tjeannin.provigen.test;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.Type;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Contract;
import com.tjeannin.provigen.exception.InvalidContractException;

public class EntityContentProvider extends ProviGenProvider {

	public EntityContentProvider() throws InvalidContractException {
		super(EntityContract.class);
	}

	@Contract(version = 1)
	public static interface EntityContract extends ProviGenBaseContract {

		@Column(type = Type.INTEGER)
		public static final String MY_INT = "int";

		@Column(type = Type.TEXT)
		public static final String MY_STRING = "string";

		@Column(type = Type.REAL)
		public static final String MY_DOUBLE = "double";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse("content://com.test.entity/table_entity");
	}
}
