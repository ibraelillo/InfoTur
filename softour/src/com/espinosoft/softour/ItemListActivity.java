package com.espinosoft.softour;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.espinosoft.softour.navigation.DummyContent;
import com.espinosoft.softour.news.Noticia;
import com.espinosoft.softour.services.NoticiasLoadingTask;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class ItemListActivity extends FragmentActivity implements Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_twopane);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);

		}
		

		/**
		 * actionbar
		 */
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		NoticiasLoadingTask task = new NoticiasLoadingTask(this, null);
		task.execute(null, null, null);
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */

	@Override
	public void onItemSelected(Object object) {

		if (object instanceof Noticia) {
			Log.i("Noticia seleccionada", ((Noticia) object).getTitulo());
			Fragment f;
			Bundle arguments = new Bundle();
			arguments.putInt(NoticiaDetailFragment.NOTICIA_ID,
					((Noticia) object).getId());
			f = new NoticiaDetailFragment();
			f.setArguments(arguments);

			getSupportFragmentManager().beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.replace(R.id.item_detail_container, f).commit();
		}

	}

	@Override
	public void onItemSelected(String id) {
		
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			
			getSupportFragmentManager().findFragmentByTag(id);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			
			Log.i("Id", id);
			Fragment f = null;
			
			if (getSupportFragmentManager().findFragmentByTag(id) != null) {
				ft.remove(getSupportFragmentManager().findFragmentByTag(id));				
			}
			try{					
				f = DummyContent.ITEM_MAP.get(id).getFragment().newInstance();
				getSupportFragmentManager().beginTransaction()
				//.remove(getSupportFragmentManager().findFragmentByTag(id)) //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.replace(R.id.item_detail_container, f, id)
				//.addToBackStack(null)
				.commit();
			}catch(Exception e){
				e.printStackTrace();
			}
				
			
			//getSupportFragmentManager().executePendingTransactions();;
			
		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		MenuItem menuItem;
		if (item.getItemId() == android.R.id.home) {
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, this.getClass());
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;
		} else if (item.getItemId() == R.id.menu_refresh) {
			menuItem = item;
			menuItem.setActionView(R.layout.progressbar);
			menuItem.expandActionView();
			NoticiasLoadingTask task = new NoticiasLoadingTask(this, null);
			task.execute(null, null, null);
		} else
			super.onOptionsItemSelected(item);

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.portal, menu);

		ShareActionProvider shareProvider = (ShareActionProvider) menu
				.findItem(R.id.menu_share).getActionProvider();

		// If you use more than one ShareActionProvider, each for a different
		// action,
		// use the following line to specify a unique history file for each one.
		// mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");

		// Set the default share intent
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		shareProvider.setShareIntent(intent);

		
		
		// you need to update the share intent somewhere else in the app, call
		// mShareActionProvider.setShareIntent()
		return true;
	}

	public void checkGooglePlayServicesAvailability() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		
		Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,this, 69);
		dialog.setCancelable(false);
			// dialog.setOnDismissListener(getOnDismissListener());
		dialog.show();
		

		Log.d("GooglePlayServicesUtil Check", "Result is: " + resultCode);
	}

}
