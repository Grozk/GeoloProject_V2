package fr.grzk.geolocproject_v2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import fr.grzk.geolocproject_v2.dragNdrop.DragListener;
import fr.grzk.geolocproject_v2.dragNdrop.DragNDropIndividuAdapter;
import fr.grzk.geolocproject_v2.dragNdrop.DragNDropListView;
import fr.grzk.geolocproject_v2.dragNdrop.DropListener;
import fr.grzk.geolocproject_v2.dragNdrop.RemoveListener;
import fr.grzk.geolocproject_v2.object.Individu;
import fr.grzk.geolocproject_v2.object.Individu_;

public class ListPrincipalActivity extends Activity implements OnItemClickListener{

	List<Individu> listSave =new ArrayList<Individu>();
	List<Individu> listValues =new ArrayList<Individu>();
	ListView list = null;
	
	DragNDropIndividuAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_principal);

		list = (ListView) findViewById(R.id.listViewContact);

		listSave = getListeIndividus();
		listValues.addAll(listSave);
		adapter = new DragNDropIndividuAdapter(this, listValues);
		list.setAdapter(adapter);

		
		list.setOnItemClickListener(this);
        if (list instanceof DragNDropListView) {
        	((DragNDropListView) list).setDropListener(mDropListener);
        	((DragNDropListView) list).setRemoveListener(mRemoveListener);
        	((DragNDropListView) list).setDragListener(mDragListener);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_principal, menu);
		MenuItem searchItem = menu.findItem(R.id.menu_item_search);
	    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
  
	    searchView.setOnQueryTextListener(new OnQueryTextListener() {
	    	 
            @Override
            public boolean onQueryTextChange(String query) {
            	
            	// suppression des valeurs affichées
            	adapter.clear();
            	// affichage des valeurs matchant la query
            	for (Individu it : listSave){
            		String id = it.getIndividu().getId();
            		if(id.toUpperCase().contains(query.toUpperCase())|| query.isEmpty()){
                		adapter.add(it);
            		}

            	} 

            	return true;
 
            }

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
 
        });
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
		Individu item = (Individu) adapter.getAdapter().getItem(position);
		System.out.println(item);
		if (item.getIndividu().getIsRelationActive()){
			Intent intent = new Intent(this, GPSActivity.class);
			intent.putExtra("orientation","45.0");
			startActivity(intent);		
		} else {
			alertDialog(item);
		}
	}
	
	private DropListener mDropListener = 
			new DropListener() {
	        public void onDrop(int from, int to) {
	        	if (adapter instanceof DragNDropIndividuAdapter) {
	        		((DragNDropIndividuAdapter)adapter).onDrop(from, to);
	        		list.invalidateViews();
	        	}
	        }
	    };
	    
	    private RemoveListener mRemoveListener =
	        new RemoveListener() {
	        public void onRemove(int which) {
	        	if (adapter instanceof DragNDropIndividuAdapter) {
	        		((DragNDropIndividuAdapter)adapter).onRemove(which);
	        		list.invalidateViews();
	        	}
	        }
	    };
	    
	    private DragListener mDragListener =
	    	new DragListener() {

	    	int backgroundColor = 0xe0103010;
	    	int defaultBackgroundColor;
	    	
				public void onDrag(int x, int y, ListView listView) {
					// TODO Auto-generated method stub
				}

				public void onStartDrag(View itemView) {
					itemView.setVisibility(View.INVISIBLE);
					defaultBackgroundColor = itemView.getDrawingCacheBackgroundColor();
					itemView.setBackgroundColor(backgroundColor);
					ImageView iv = (ImageView)itemView.findViewById(R.id.imageView1);
					if (iv != null) iv.setVisibility(View.INVISIBLE);
				}

				public void onStopDrag(View itemView) {
					itemView.setVisibility(View.VISIBLE);
					itemView.setBackgroundColor(defaultBackgroundColor);
					ImageView iv = (ImageView)itemView.findViewById(R.id.imageView1);
					if (iv != null) iv.setVisibility(View.VISIBLE);
				}
	    	
	    };
	    
	    public void alertDialog(final Individu indiv) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(true);
			builder.setTitle(indiv.getIndividu().getId()+" voudrais vous ajouter");
			builder.setInverseBackgroundForced(true);

			builder.setPositiveButton("Accepter", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					accepterDemandeContact(indiv);
					dialog.cancel();
				}
			});
			builder.setNegativeButton("Refuser", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					refuserDemandeContact(indiv);
					dialog.cancel();
				}
			});
			builder.show();
		}
	
	protected void refuserDemandeContact(Individu indiv) {
			// TODO requete serveur pour refuser le contact
		indiv.getIndividu().setIsRelationActive(false);
	}

	protected void accepterDemandeContact(Individu indiv) {
			// TODO requete serveur pour accepter le contact
		indiv.getIndividu().setIsRelationActive(true);
		adapter.notifyDataSetChanged();
	}

	private List<Individu> getListeIndividus(){
		List<Individu> listTest = new ArrayList<Individu>();
		
		Individu ind = new Individu();
		ind.setIndividu(new Individu_());
		ind.getIndividu().setId("Marco");
		ind.getIndividu().setTelephone("0265854574");
		ind.getIndividu().setIdIndividuGenere("AAA");
		ind.getIndividu().setIsRelationActive(false);

		Individu ind2 = new Individu();
		ind2.setIndividu(new Individu_());
		ind2.getIndividu().setId("John");
		ind2.getIndividu().setTelephone("0265854574");
		ind2.getIndividu().setIdIndividuGenere("AA2");
		ind2.getIndividu().setIsRelationActive(false);

		Individu ind3 = new Individu();
		ind3.setIndividu(new Individu_());
		ind3.getIndividu().setId("Bart");
		ind3.getIndividu().setTelephone("0265854574");
		ind3.getIndividu().setIdIndividuGenere("AA3");
		ind3.getIndividu().setIsRelationActive(false);

		Individu ind4 = new Individu();
		ind4.setIndividu(new Individu_());
		ind4.getIndividu().setId("Zoe");
		ind4.getIndividu().setTelephone("0265854574");
		ind4.getIndividu().setIdIndividuGenere("AA4");
		ind4.getIndividu().setIsRelationActive(true);

		Individu ind5 = new Individu();
		ind5.setIndividu(new Individu_());
		ind5.getIndividu().setId("Ifikit");
		ind5.getIndividu().setTelephone("0265854574");
		ind5.getIndividu().setIdIndividuGenere("AA5");
		ind5.getIndividu().setIsRelationActive(true);

		listTest.add(ind);
		listTest.add(ind2);
		listTest.add(ind3);
		listTest.add(ind4);
		listTest.add(ind5);

		return listTest;
	}
}
