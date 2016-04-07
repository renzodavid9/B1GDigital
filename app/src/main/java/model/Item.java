package model;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Renzo on 6/04/2016.
 */
public class Item {

    public String name;
    public String urlImg;
    public Bitmap bitmap;

    public Item(String name){
        this.name = name;
    }

    public void setUrlImg(String urlImg){
        this.urlImg = urlImg;
    }

}
