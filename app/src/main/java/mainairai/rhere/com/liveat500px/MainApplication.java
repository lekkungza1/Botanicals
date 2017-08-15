package mainairai.rhere.com.liveat500px;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by lek on 6/6/2560.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
