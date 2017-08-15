package mainairai.rhere.com.liveat500px.manager.http;

import java.util.List;

import mainairai.rhere.com.liveat500px.dao.TreeItemDao;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by lek on 6/6/2560.
 */

public interface ApiService {

    @POST("plant_by_season.php?lang_code=1&season_id=0")
    Call<List<TreeItemDao>> loadPhotolist();
}
