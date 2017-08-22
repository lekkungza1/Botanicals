package mainairai.rhere.com.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import mainairai.rhere.com.liveat500px.R;
import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import me.relex.circleindicator.CircleIndicator;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TreeDetailFragment extends Fragment {

    public  interface  LocationListener{
        void onLocationBtnClicked(TreeItemDao dao);
    }

    public  interface  FullScreenListener{
        void onFullscreenImage(boolean isShow);
    }

    public TreeDetailFragment() {
        super();
    }

    TreeItemDao dao;

    ViewPager imageSlide,fullImageSlide;

    TextView tvTreeName;
    TextView tvSciName;
    TextView tvTreeLocalName;
    TextView tvTreeDetail;
    Button btnLocation;
    Button btnShare;
    int galSize;
    CircleIndicator indicator;



    @SuppressWarnings("unused")
    public static TreeDetailFragment newInstance(TreeItemDao dao) {
        TreeDetailFragment fragment = new TreeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tree_detail, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        //TODO Location looper
       /* List<Location> a = dao.getLocations();
        int size = dao.getLocations().size();
        Location b = a.get(0);
        String c = b.getLat();
        String d = b.getLng();*/
        final List<String> Gal;
        Gal = dao.getGallery();
        galSize = Gal.size();

        imageSlide = (ViewPager)rootView.findViewById(R.id.detailSlideShow);
        fullImageSlide = (ViewPager)rootView.findViewById(R.id.fullImageSlide);
        indicator = (CircleIndicator)rootView.findViewById(R.id.indicator);

        imageSlide.setAdapter(imageAdapter);
        fullImageSlide.setAdapter(imageAdapter);

        imageSlide.addOnPageChangeListener(onPageChanged);

        indicator.setViewPager(imageSlide);


        tvTreeName=(TextView)rootView.findViewById(R.id.tvTreeName);
        tvSciName=(TextView)rootView.findViewById(R.id.tvSciName);
        tvTreeLocalName=(TextView)rootView.findViewById(R.id.tvTreeLocName);
        tvTreeDetail=(TextView)rootView.findViewById(R.id.TreeDetail);
        btnLocation=(Button)rootView.findViewById(R.id.btnLocation);

        tvTreeName.setText("Name :"+dao.getName().toString());
        tvSciName.setText("Scientific Name :"+dao.getScientificName());
        tvTreeLocalName.setText("Local Name :"+dao.getLocalName());
        tvTreeDetail.setText("Detail :"+dao.getDetail());
       /* int num = dao.getLocations().size()-1;
        for (int i=0;i<=num;i++) {
           String aa = tvTreeDetail.getText().toString();
            tvTreeDetail.setText(num+":: "+aa+" , "+dao.getLocations().get(i).getLat());
        }*/
        btnLocation.setOnClickListener(locationBtnClick);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    PagerAdapter imageAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return galSize;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final List<String> Gal;
            Gal = dao.getGallery();
            galSize = Gal.size();


            ImageView img = new ImageView(container.getContext());

            img.setOnClickListener(imageClicked);

            Glide.with(getContext())
                    .load(Gal.get(position))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
            container.addView(img);

            return img;
        }

        View.OnClickListener imageClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Image click show fullscreen

                if(fullImageSlide.getVisibility()==View.VISIBLE){
                fullImageSlide.setVisibility(View.GONE);

                    FullScreenListener listener = (FullScreenListener) getActivity();
                    listener.onFullscreenImage(true);
                    }

                else{
                fullImageSlide.setVisibility(View.VISIBLE);
                    FullScreenListener listener = (FullScreenListener) getActivity();
                    listener.onFullscreenImage(false);
                }

            }
        };

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    ViewPager.OnPageChangeListener onPageChanged = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            fullImageSlide.setCurrentItem(imageSlide.getCurrentItem());
        }
    };

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }


    ////////////////////////////////////////////////////////////////////////////////
    View.OnClickListener locationBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LocationListener listener = (LocationListener) getActivity();
            listener.onLocationBtnClicked(dao);

        }
    };


}
