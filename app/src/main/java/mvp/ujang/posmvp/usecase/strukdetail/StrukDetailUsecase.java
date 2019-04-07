package mvp.ujang.posmvp.usecase.strukdetail;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.struk.datasource.StrukDataSource;
import mvp.ujang.posmvp.module.struk.datasource.StrukRepository;
import mvp.ujang.posmvp.module.struk.datasource.local.StrukLocalDataSource;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.strukdetail.datasource.StrukDetailDataSource;
import mvp.ujang.posmvp.module.strukdetail.datasource.StrukDetailRepository;
import mvp.ujang.posmvp.module.strukdetail.datasource.local.StrukDetailLocalDataSource;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;

public class StrukDetailUsecase implements StrukDetailDataSource{

    private static StrukDetailUsecase sInstance = null;
    private StrukDetailRepository strukDetailRepository;

    public StrukDetailUsecase(Context context) {
        this.strukDetailRepository = StrukDetailRepository.getInstance(StrukDetailLocalDataSource.getInstance(context));
    }

    public static StrukDetailUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new StrukDetailUsecase(context);

        return sInstance;
    }

    @Override
    public void loadStruk(StrukDetail strukDetail, @NonNull Callback.LoadCallback<StrukDetail> callback) {
        strukDetailRepository.loadStruk(strukDetail,callback);
    }
}
