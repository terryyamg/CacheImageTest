package tw.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<ListItem> items;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<ListItem> items) {
		this.activity = activity;
		this.items = items;
	}

	public int getCount() {
		return items.size();
	}

	public Object getItem(int location) {
		return items.get(location);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView attack = (TextView) convertView.findViewById(R.id.attack);
		TextView attributes = (TextView) convertView
				.findViewById(R.id.attributes);
		TextView material = (TextView) convertView.findViewById(R.id.material);

		// getting movie data for the row
		ListItem m = items.get(position);

		// 縮圖
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// 名稱
		title.setText(m.getTitle());

		// 攻擊力
		attack.setText("攻擊力: " + String.valueOf(m.getAttack()));

		// 屬性
		attributes.setText("屬性: " + m.getAttributes());

		// 材料
		String materialStr = "";
		for (String str : m.getMaterial()) {
			materialStr += str + ", ";
		}
		materialStr = materialStr.length() > 0 ? materialStr.substring(0,
				materialStr.length() - 2) : materialStr;
		material.setText(materialStr);

		return convertView;
	}

}
