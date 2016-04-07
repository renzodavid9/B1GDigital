package test.b1gdigital.b1gdigita_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.net.URI;
import java.net.URL;
import java.util.List;

import logic.AppController;
import model.Item;

/**
 * Created by Renzo on 6/04/2016.
 */
public class ItemsAdapter extends BaseAdapter {

    List<Item> items;
    Activity a;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private static LayoutInflater inflater=null;

    public ItemsAdapter(MainActivity main, List<Item> items){
        inflater = ( LayoutInflater )main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.items, null);
        TextView tv =(TextView) rowView.findViewById(R.id.name);
        tv.setText(items.get(position).name);

        NetworkImageView thumbNail = (NetworkImageView) rowView.findViewById(R.id.thumbnail);
        thumbNail.setImageUrl(items.get(position).urlImg, imageLoader);

        return rowView;
    }
}
