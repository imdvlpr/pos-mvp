package mvp.ujang.posmvp.module.strukdetail.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.strukdetail.datasource.local.StrukDetailLocalDataSource;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.module.strukdetail.view.StrukDetailActivity;

public class StrukDetailRepository implements StrukDetailDataSource {
    private static StrukDetailRepository sInstance = null;
    private StrukDetailLocalDataSource strukDetailLocalDataSource;

    private StrukDetailRepository(@NonNull StrukDetailLocalDataSource strukDetailLocalDataSource) {
        this.strukDetailLocalDataSource = strukDetailLocalDataSource;
    }

    public static StrukDetailRepository getInstance(@NonNull StrukDetailLocalDataSource strukDetailLocalDataSource) {
        if (sInstance == null)
            sInstance = new StrukDetailRepository(strukDetailLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadStruk(StrukDetail strukDetail, @NonNull Callback.LoadCallback<StrukDetail> callback) {
        strukDetailLocalDataSource.loadStruk(strukDetail,callback);
    }
}
