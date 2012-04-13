package com.marakana.android.rss;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RssDemoActivity extends FragmentActivity implements
		LoaderManager.LoaderCallbacks<Cursor> {
	private static final String[] FROM = { RssContract.Columns.TITLE };
	private static final int[] TO = { android.R.id.text1 };
	private SimpleCursorAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup the adapter
		adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, null, FROM, TO);

		// Setup the UI
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(RssDemoActivity.this, DetailsActivity.class);
				intent.putExtra("post_id", id);
				startActivity( intent );
			}
			
		});
		
		// Initialize the loader
		getSupportLoaderManager().initLoader(47, null, this);
	}

	// --- LoaderManager.LoaderCallbacks<Cursor> callbacks ---

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		setProgressBarIndeterminateVisibility(true);
		return new CursorLoader(this, RssContract.CONTENT_URI, null, null,
				null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		setProgressBarIndeterminateVisibility(false);
		adapter.changeCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		setProgressBarIndeterminateVisibility(false);
		adapter.changeCursor(null);
	}
}
