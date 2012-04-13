package com.marakana.android.rss;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.webkit.WebView;

public class DetailsActivity extends FragmentActivity implements
		LoaderManager.LoaderCallbacks<Cursor> {
	private static final String TAG = "DetailsActivity";
	WebView out;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.details);
		out = (WebView) findViewById(R.id.out);

	}

	@Override
	protected void onStart() {
		super.onStart();
		getSupportLoaderManager().initLoader(57, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		long post_id = getIntent().getLongExtra("post_id", -1);
		Log.d(TAG, "post_id: " + post_id);
		Uri uri = Uri.withAppendedPath(RssContract.CONTENT_URI,
				Long.toString(post_id));
		return new CursorLoader(this, uri, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		String data = "<p><strong>(404) Item not found.</strong></p>";
		
		Log.d(TAG, "cursor size: "+cursor.getCount());
		if (cursor.moveToFirst()) {
			data = cursor.getString(cursor
					.getColumnIndex(RssContract.Columns.DESCRIPTION));
		}
		out.loadData(data, "text/html", "utf-8");
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		out.loadData("No data", "text/html", "utf-8");
	}

}
