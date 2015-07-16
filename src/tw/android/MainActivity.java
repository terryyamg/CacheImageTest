package tw.android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class MainActivity extends Activity {// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// json檔案位置
	private static final String url = "http://terryyamg.3eeweb.com/test/list.json";

	private List<ListItem> listItem = new ArrayList<ListItem>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, listItem);
		listView.setAdapter(adapter);	
		
		// 取得資料並編碼
		JsonArrayPostRequest itemReq = new JsonArrayPostRequest(url,
				new Response.Listener<JSONArray>() {

					public void onResponse(JSONArray response) {

						// 解析json
						for (int i = 0; i < response.length(); i++) {
							try {
								
								//取得回應物件
								JSONObject obj = response.getJSONObject(i);
								//取得對定標題並放入item內
								ListItem item = new ListItem();
								item.setTitle(obj.getString("title"));
								item.setThumbnailUrl(obj.getString("image"));
								item.setAttack(obj.getInt("attack"));
								item.setAttributes(obj.getString("attributes"));
								//多項目處理
								JSONArray materialArry = obj
										.getJSONArray("material");
								ArrayList<String> material = new ArrayList<String>();
								for (int j = 0; j < materialArry.length(); j++) {
									material.add((String) materialArry.get(j));
								}
								item.setMaterial(material);

								// 將所有項目item放入listItem陣列
								listItem.add(item);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// 更新adapter數據
						adapter.notifyDataSetChanged();
					}
					
					
				}, new Response.ErrorListener() {

					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// 使用AppController內的排序
		AppController.getInstance().addToRequestQueue(itemReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}



}

