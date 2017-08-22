package mainairai.rhere.com.liveat500px.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mainairai.rhere.com.liveat500px.R;
import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import mainairai.rhere.com.liveat500px.fragment.TreeDetailFragment;

public class DetailActivity extends AppCompatActivity implements TreeDetailFragment.LocationListener,TreeDetailFragment.FullScreenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initInstances();

        TreeItemDao dao = getIntent().getParcelableExtra("dao");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, TreeDetailFragment.newInstance(dao))
                    .commit();
        }
    }

    private void initInstances() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if( item.getItemId()== android.R.id.home){
           finish();
           return true;
       }
        return super.onOptionsItemSelected(item);
    }



    /////////////////////Listener///////////////////////////////////////
    @Override
    public void onLocationBtnClicked(TreeItemDao dao) {
        Intent intent = new Intent(DetailActivity.this,MapsActivity.class);
        intent.putExtra("dao",dao);
        startActivity(intent);
    }

    @Override
    public void onFullscreenImage(boolean isShow) {
        ActionBar actionBar = getSupportActionBar();
        if(isShow==true){
            actionBar.show();
        }else{
            actionBar.hide();
        }

    }
}
