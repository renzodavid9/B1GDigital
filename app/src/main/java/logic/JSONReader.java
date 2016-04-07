package logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import test.b1gdigital.b1gdigita_test.MainActivity;

/**
 * Created by Renzo on 6/04/2016.
 */
public class JSONReader {
    String responseS;
    MainActivity reference;
    List<JSONReader> imgLoaders;
    List<Item> itemsList;
    Item item;

    public JSONReader(MainActivity ref){
        reference = ref;
        imgLoaders = new ArrayList<>();
        itemsList = new ArrayList<>();
    }

    public void readJson(String url){
        responseS = "";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    responseS = response.toString();
                    getAttributes(responseS);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    responseS = error.getMessage();
                    reference.processAnsw(false,responseS);
                }
            });
        AppController.getInstance().addToRequestQueue(jsonObjReq,"renzo");
    }

    public String getResponseS(){
        return responseS;
    }

    private void getAttributes (String response){
        JSONObject jsonObject;
        JSONArray items;

        try {
            jsonObject = new JSONObject(response);
            jsonObject = jsonObject.getJSONObject("feed");
            items = jsonObject.getJSONArray("entry");
            for (int i=0; i<items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                JSONReader jr = new JSONReader(reference);
                Item it = new Item(item.getJSONObject("im:name").getString("label"));

                JSONArray urls = item.getJSONArray("im:image");
                jr.setItem(it);
                jr.getUrl(urls, it);

                itemsList.add(it);
                imgLoaders.add(jr);

            }
        } catch (JSONException e) {
            reference.processAnsw(false,"ERROR DE FORMATO");
            return;
        }
        reference.processAnswOk(true, itemsList);
    }

    public void getUrl (JSONArray urls, Item it) throws JSONException {
        String url = "";
        if (urls.length()>0) {
            url = urls.getJSONObject(0).getString("label");
            it.urlImg = url;
        }
    }
    public void setItem (Item item){
        this.item = item;
    }

}
