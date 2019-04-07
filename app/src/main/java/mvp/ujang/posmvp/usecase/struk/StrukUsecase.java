package mvp.ujang.posmvp.usecase.struk;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.datasource.ProdukDataSource;
import mvp.ujang.posmvp.module.produk.datasource.ProdukRepository;
import mvp.ujang.posmvp.module.produk.datasource.local.ProdukLocalDataSource;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.struk.datasource.StrukDataSource;
import mvp.ujang.posmvp.module.struk.datasource.StrukRepository;
import mvp.ujang.posmvp.module.struk.datasource.local.StrukLocalDataSource;
import mvp.ujang.posmvp.module.struk.model.Struk;

public class StrukUsecase implements StrukDataSource {

    private static StrukUsecase sInstance = null;
    private StrukRepository strukRepository;

    public StrukUsecase(Context context) {
        this.strukRepository = StrukRepository.getInstance(StrukLocalDataSource.getInstance(context));
    }

    public static StrukUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new StrukUsecase(context);

        return sInstance;
    }

    @Override
    public void loadStruk(@NonNull Callback.LoadCallback<Struk> callback) {
        strukRepository.loadStruk(callback);
    }
}
