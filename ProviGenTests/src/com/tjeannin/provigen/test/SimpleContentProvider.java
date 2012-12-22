package com.tjeannin.provigen.test;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.Type;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;
import com.tjeannin.provigen.annotation.Contract;
import com.tjeannin.provigen.exception.InvalidContractException;

public class SimpleContentProvider extends ProviGenProvider {

	public SimpleContentProvider() throws InvalidContractException {
		super(new Class[] { ContractOne.class, ContractThree.class });
	}

	@Contract(version = 1)
	public static interface ContractOne extends ProviGenBaseContract {

		@Column(type = Type.INTEGER)
		public static final String MY_INT = "int";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse("content://com.test.simple/table_name_simple");

	}

	@Contract(version = 2)
	public static interface ContractTwo extends ProviGenBaseContract {

		@Column(type = Type.INTEGER)
		public static final String MY_INT = "int";

		@Column(type = Type.TEXT)
		public static final String MY_STRING = "string";

		@Column(type = Type.REAL)
		public static final String MY_REAL = "real";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse("content://com.test.simple/table_name_simple");

	}

	@Contract(version = 1)
	public static interface ContractThree extends ProviGenBaseContract {

		@Column(type = Type.INTEGER)
		public static final String ANOTHER_INT = "another_int";

		@ContentUri
		public static final Uri CONTENT_URI = Uri.parse("content://com.test.simple/another_table_name");

	}

}
