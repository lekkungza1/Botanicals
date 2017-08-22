package mainairai.rhere.com.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import java.util.List;

import mainairai.rhere.com.liveat500px.Adapter.PhotoListAdapter;
import mainairai.rhere.com.liveat500px.R;
import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import mainairai.rhere.com.liveat500px.manager.HttpManager;
import mainairai.rhere.com.liveat500px.manager.http.PhotoListManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AllSSFragment extends Fragment {

    public  interface  FragmentListener{
        void onPhotoItemClicked(TreeItemDao dao);
    }

    ListView listView;
    PhotoListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    PhotoListManager photoListManager;

    public AllSSFragment() {
        super();
    }

    public static AllSSFragment newInstance() {
        AllSSFragment fragment = new AllSSFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoListManager = new PhotoListManager();

        if(savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allss, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;

    }
    


    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new PhotoListAdapter();
        listAdapter.setDao(photoListManager.getDao());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);

        swipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem==0);

            }
        });
        if (savedInstanceState == null)
        reloadData();
    }

    private void reloadData() {
        Call<List<TreeItemDao>> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new PhotoListLoadCallback());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
        //TODO save instance stat to out stat

        outState.putBundle("photoListManager",
                photoListManager.onSaveInstanceState());


    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        //Restore instance stat
        photoListManager.onRestoreInstanceState(savedInstanceState.getBundle("photoListManager"));


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(), text,
                Toast.LENGTH_SHORT).show();
    }
    /////////////Listener/////////////////
    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            reloadData();
        }
    };
    AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           if(position<photoListManager.getCount()) {
               TreeItemDao dao = photoListManager.getDao().get(position);
               FragmentListener listener = (FragmentListener) getActivity();
               listener.onPhotoItemClicked(dao);
           }
        }
    };
    ///////////////Inner class////////////////////

    class PhotoListLoadCallback implements Callback<List<TreeItemDao>>{
        @Override
        public void onResponse(Call<List<TreeItemDao>> call, Response<List<TreeItemDao>> response) {
            swipeRefreshLayout.setRefreshing(false);
            if (response.isSuccessful()) {
                List<TreeItemDao> dao = response.body();
                photoListManager.setDao(dao);
                //TODO reminder photoList
                //listAdapter.setDao((List<TreeItemDao>) dao);
                listAdapter.setDao(photoListManager.getDao());
                listAdapter.notifyDataSetChanged();

            } else {
                try {
                    showToast(response.errorBody()
                            .string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailure(Call<List<TreeItemDao>> call, Throwable t) {
            swipeRefreshLayout.setRefreshing(false);
           showToast(t.toString());


        }
    }
}
