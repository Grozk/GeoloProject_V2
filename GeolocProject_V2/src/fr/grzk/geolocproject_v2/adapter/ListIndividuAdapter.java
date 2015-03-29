package fr.grzk.geolocproject_v2.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.grzk.geolocproject_v2.R;
import fr.grzk.geolocproject_v2.object.Individu;

public class ListIndividuAdapter extends ArrayAdapter<Individu> {

	private Context ctx;
	private List<Individu> listIndividus;

	public ListIndividuAdapter(Context context, List<Individu> objects) {
		super(context, R.layout.custom_layout_list_individu, objects);
		this.ctx = context;
		this.listIndividus = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_layout_list_individu,
				parent, false);

		TextView textView = (TextView) rowView
				.findViewById(R.id.listview_individu_textview_name);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.listview_individu_image_direction);

		textView.setText(listIndividus.get(position).getIndividu()
				.getId());

		imageView.setImageResource(R.drawable.ic_launcher);


		return rowView;
	}

}
