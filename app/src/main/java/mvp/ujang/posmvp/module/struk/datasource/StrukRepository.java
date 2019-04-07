package mvp.ujang.posmvp.module.struk.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.datasource.ProdukDataSource;
import mvp.ujang.posmvp.module.produk.datasource.local.ProdukLocalDataSource;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.struk.datasource.local.StrukLocalDataSource;
import mvp.ujang.posmvp.module.struk.model.Struk;

public class StrukRepository implements StrukDataSource {
    private static StrukRepository sInstance = null;
    private StrukLocalDataSource strukLocalDataSource;

    private StrukRepository(@NonNull StrukLocalDataSource strukLocalDataSource) {
        this.strukLocalDataSource = strukLocalDataSource;
    }

    public static StrukRepository getInstance(@NonNull StrukLocalDataSource strukLocalDataSource) {
        if (sInstance == null)
            sInstance = new StrukRepository(strukLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadStruk(@NonNull Callback.LoadCallback<Struk> callback) {
        strukLocalDataSource.loadStruk(callback);
    }
}
