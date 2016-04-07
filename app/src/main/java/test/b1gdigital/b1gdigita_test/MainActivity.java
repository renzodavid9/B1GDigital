package test.b1gdigital.b1gdigita_test;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.List;

import logic.AppController;
import logic.JSONReader;
import model.Item;

public class MainActivity extends AppCompatActivity {

    String url = "https://itunes.apple.com/us/rss/topfreeapplications/limit=50/json";
    String responseS = "";
    ProgressDialog progressBar;
    ListView listView;
    List<Item>items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        loadContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void loadContent() {
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Cargando...");
        progressBar.show();
        JSONReader jsonReader = new JSONReader(this);
        jsonReader.readJson(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void processAnsw(Boolean status, String response){
        progressBar.hide();
        Toast.makeText(this,response,Toast.LENGTH_SHORT).show();
    }

    public void processAnswOk(Boolean status, List<Item> items){
        progressBar.hide();
        listView.setAdapter(new ItemsAdapter(this, items));
        this.items = items;
    }

    public void update(){
        listView.setAdapter(new ItemsAdapter(this,items));
    }
}
