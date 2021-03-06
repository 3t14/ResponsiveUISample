package com.dev_training.responsiveuisample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev_training.responsiveuisample.app.dummy.DummyContent;


public class MainActivity extends FragmentActivity
        implements BookmarkFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new BookmarkFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void onFragmentInteraction(String url) {
        DummyContent.DummyItem item = DummyContent.ITEM_MAP.get(url);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title", item.title);
        intent.putExtra("description", item.description);
        startActivity(intent);

        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
    }
}
