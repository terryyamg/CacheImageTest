package tw.android;

import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class JsonArrayPostRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> mListener;
    public JsonArrayPostRequest(String url, Response.Listener<JSONArray> listener,
                                Response.ErrorListener errorListener){
        super(Method.GET, url, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {

        	/*utf-8 編碼 否則中文會出現亂碼*/
            String jsonString = new String(response.data, "UTF-8");
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        } catch (JSONException je){
            return Response.error(new ParseError(je));
        }
    }
    @Override
    protected void deliverResponse(JSONArray response) {
        mListener.onResponse(response);
    }
}