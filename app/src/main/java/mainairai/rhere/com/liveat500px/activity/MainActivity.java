package mainairai.rhere.com.liveat500px.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mainairai.rhere.com.liveat500px.R;
import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import mainairai.rhere.com.liveat500px.fragment.AllSSFragment;
import mainairai.rhere.com.liveat500px.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements AllSSFragment.FragmentListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();



        }

    }

    private void initInstance() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      //  if(actionBarDrawerToggle.onOptionsItemSelected(item))
            switch (item.getItemId()){
                case R.id.action_navigator:
                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return super.onOptionsItemSelected(item);

    }


    @Override
    public void onPhotoItemClicked(TreeItemDao dao) {
        //TODO handle click event
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("dao",dao);
        startActivity(intent);

    }

}
