package com.marakana.android.rss;

import android.net.Uri;
import android.provider.BaseColumns;

/** Constants related to our RssProvider. */
public final class RssContract {
	/** The authority for the contacts provider */
	public static final String AUTHORITY = "com.marakana.android.rss.provider";
	public static final String PATH = "post";

	/** A content:// style uri to the authority for this table. */
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + PATH);

	/**
	 * The MIME type of {@link #CONTENT_URI} providing a directory or a single
	 * post.
	 */
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.marakana.post";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.marakana.post";

	/** Contains the column names for a Post. */
	public final class Columns implements BaseColumns {
		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String DESCRIPTION = "desc";
		public static final String DATE = "date";

		private Columns() {
		};
	}

	// Private constructor to ensure nobody instantiates this
	private RssContract() {
	}
}
