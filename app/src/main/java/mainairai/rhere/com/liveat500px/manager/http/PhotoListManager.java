package mainairai.rhere.com.liveat500px.manager.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;
import java.util.List;

import mainairai.rhere.com.liveat500px.dao.TreeItemDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoListManager {


    private Context mContext;
    private List<TreeItemDao> dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public List<TreeItemDao> getDao() {
        return dao;
    }

    public void setDao(List<TreeItemDao> dao) {
        this.dao = dao;
    }
    public  int getCount(){
        if (dao==null)
            return 0;
        return dao.size();
    }

    public Bundle onSaveInstanceState(){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("dao", (ArrayList<? extends Parcelable>) dao);
        return bundle;
    }
    public void  onRestoreInstanceState(Bundle savedInstanceState){
            dao=savedInstanceState.getParcelableArrayList("dao");
    }
}
