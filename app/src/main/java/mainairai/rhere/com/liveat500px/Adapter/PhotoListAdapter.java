package mainairai.rhere.com.liveat500px.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import mainairai.rhere.com.liveat500px.view.PhotoListItem;

/**
 * Created by lek on 6/6/2560.
 */

public class PhotoListAdapter extends BaseAdapter {

    int lastPosition = -1;

    List<TreeItemDao> dao;

    public void setDao(List<TreeItemDao> dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;

       // return PhotoListManager.getInstance().getDao().;
        return dao.size();

        //TODO get size
    }

    @Override
    public Object getItem(int position) {

        return dao.get(position);
        //TODO get position
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoListItem item;
        if (convertView != null)
            item = (PhotoListItem) convertView;
        else
            item = new PhotoListItem(parent.getContext());

        TreeItemDao dao = (TreeItemDao) getItem(position);
        item.setNameText(dao.getName());
        item.setDescText(dao.getScientificName()+"\n"+dao.getFamilyName());
        item.setImageUrl(dao.getThumbnail());

        /*if(position>lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition= position;
        }*/
        return item;


    }


}
