package mvp.ujang.posmvp.module.produk.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.datasource.local.ProdukLocalDataSource;
import mvp.ujang.posmvp.module.produk.model.Produk;

public class ProdukRepository implements ProdukDataSource {
    private static ProdukRepository sInstance = null;
    private ProdukLocalDataSource produkLocalDataSource;

    private ProdukRepository(@NonNull ProdukLocalDataSource produkLocalDataSource) {
        this.produkLocalDataSource = produkLocalDataSource;
    }

    public static ProdukRepository getInstance(@NonNull ProdukLocalDataSource produkLocalDataSource) {
        if (sInstance == null)
            sInstance = new ProdukRepository(produkLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadProduk(@NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        produkLocalDataSource.loadProduk(loadProdukCallback);
    }

    @Override
    public void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        produkLocalDataSource.searchProduk(parameter,loadProdukCallback);
    }

    @Override
    public void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback) {
        produkLocalDataSource.addProduk(produk,addProdukCallback);
    }

    @Override
    public void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback) {

    }

    @Override
    public void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback) {

    }

}
