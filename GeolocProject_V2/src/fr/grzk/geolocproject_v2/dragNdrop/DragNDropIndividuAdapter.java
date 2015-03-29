/*
 * Copyright (C) 2010 Eric Harlow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.grzk.geolocproject_v2.dragNdrop;

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

public final class DragNDropIndividuAdapter extends ArrayAdapter<Individu> implements RemoveListener, DropListener{

    private LayoutInflater mInflater;
    private List<Individu> mContent;

    
    public DragNDropIndividuAdapter(Context context, List<Individu> objects) {
		super(context, R.layout.custom_layout_list_individu, objects);
    	mInflater = LayoutInflater.from(context);
		this.mContent = objects;
	}

    /**
     * The number of items in the list
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
        return mContent.size();
    }

    /**
     * Since the data comes from an array, just returning the index is
     * sufficient to get at the data. If we were using a more complex data
     * structure, we would return whatever object represents one row in the
     * list.
     *
     * @see android.widget.ListAdapter#getItem(int)
     */
    public Individu getItem(int position) {
        return mContent.get(position);
    }

    /**
     * Use the array index as a unique id.
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = mInflater.inflate(R.layout.custom_layout_list_individu,
				parent, false);

		TextView textView = (TextView) rowView
				.findViewById(R.id.listview_individu_textview_name);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.listview_individu_image_direction);

		textView.setText(mContent.get(position).getIndividu()
				.getId());

		imageView.setImageResource(R.drawable.ic_launcher);


		return rowView;
    }

    static class ViewHolder {
        TextView text;
    }

	public void onRemove(int which) {
		if (which < 0 || which > mContent.size()) return;		
		mContent.remove(which);
	}

	public void onDrop(int from, int to) {
		Individu temp = mContent.get(from);
		mContent.remove(from);
		mContent.add(to,temp);
	}
}