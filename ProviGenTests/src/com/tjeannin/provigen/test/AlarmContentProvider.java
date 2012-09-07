package com.tjeannin.provigen.test;

import android.net.Uri;

import com.tjeannin.provigen.InvalidContractException;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.Type;
import com.tjeannin.provigen.annotations.Column;
import com.tjeannin.provigen.annotations.Contract;
import com.tjeannin.provigen.annotations.Id;
import com.tjeannin.provigen.annotations.Table;

public class AlarmContentProvider extends ProviGenProvider {

	public AlarmContentProvider() throws InvalidContractException {
		super(AlarmContract.class);
	}

	@Contract(authority = "com.tjeannin.provigen", databaseName = "alarm_app")
	public static class AlarmContract {

		@Table
		public static final String TABLE_NAME = "alarms";

		@Id
		@Column(type = Type.INTEGER)
		public static final String COLUMN_ID = "_id";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_HOUR = "hour";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_MINUTE = "minute";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_RINGTIME = "ring_time";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_SNOOZETIME = "snooze_time";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_ACTIVE = "active";

		@Column(type = Type.INTEGER)
		public static final String COLUMN_SNOOZED = "snoozed";

		@Column(type = Type.TEXT)
		public static final String COLUMN_NAME = "name";

		@Column(type = Type.TEXT)
		public static final String COLUMN_ACTIVE_DAYS = "active_days";

		public static final Uri CONTENT_URI = Uri.parse("content://com.tjeannin.provigen/" + TABLE_NAME);

	}

}