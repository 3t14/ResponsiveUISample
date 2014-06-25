package com.dev_training.responsiveuisample.app;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dev_training.responsiveuisample.app.dummy.DummyContent;


public class MainActivity extends FragmentActivity
        implements BookmarkFragment.OnFragmentInteractionListener,
                    DetailFragment.OnFragmentInteractionListener,
                    MyWebViewFragment.OnFragmentInteractionListener
{


    private static final int LAYOUT_ONE_COLUMN = 0;
    private static final int LAYOUT_TWO_COLUMN = 1;
    private static final int LAYOUT_THREE_COLUMN = 2;

    private int currentLayout;
    private String url ="";
    private String title ="";
    private String description ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View container = findViewById(R.id.container);
        View detail_container = findViewById(R.id.detail_container);
        View web_view_container = findViewById(R.id.web_view_container);

        // のちの振り分け処理に利用
        if (detail_container == null){
            // One Pane
            currentLayout = LAYOUT_ONE_COLUMN;
        } else  if (web_view_container == null){
            // Two Pane
            currentLayout = LAYOUT_TWO_COLUMN;
        } else {
            // Three Pane
            currentLayout = LAYOUT_THREE_COLUMN;
        }
        Toast.makeText(this,"layout="+currentLayout, Toast.LENGTH_LONG).show();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new BookmarkFragment())
                    .commit();
            // 2列
            if (currentLayout == LAYOUT_TWO_COLUMN){
                getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container,
                            DetailFragment.newInstance(url, title, description));
            }
            if (currentLayout == LAYOUT_THREE_COLUMN){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.web_view_container,
                               MyWebViewFragment.newInstance(url));
            }
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
        if (currentLayout == LAYOUT_TWO_COLUMN) {
            // 次の画面へ行きWebページを表示する
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }else {
            // フラグメントの置き換え
            getSupportFragmentManager().
                    beginTransaction()
                    .replace(R.id.web_view_container,
                            MyWebViewFragment.newInstance(url))
                    .commit();
        }

        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(String url, String title, String description) {
        // DetailFragment.onFragmentInteractionとの差別化を図るため、
        // 引数を３つに修正

        if (currentLayout == LAYOUT_ONE_COLUMN) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("url", url);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            startActivity(intent);
        } else {
            // フラグメントの置き換え
            getSupportFragmentManager().
                    beginTransaction()
                        .replace(R.id.detail_container,
                            DetailFragment.newInstance(url,title,description))
                       .commit();
        }

        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction() {
        //MyWebViewFragmentからのアクションの対応
        // 他の同一名との差別化を図るために引数なしに変更
    }
}
