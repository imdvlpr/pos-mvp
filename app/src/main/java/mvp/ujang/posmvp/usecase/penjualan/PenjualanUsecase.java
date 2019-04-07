package mvp.ujang.posmvp.usecase.penjualan;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.datasource.ProdukDataSource;
import mvp.ujang.posmvp.module.produk.datasource.ProdukRepository;
import mvp.ujang.posmvp.module.produk.datasource.local.ProdukLocalDataSource;
import mvp.ujang.posmvp.module.produk.model.Produk;

public class PenjualanUsecase implements ProdukDataSource {

    private static PenjualanUsecase sInstance = null;
    private ProdukRepository produkRepository;

    public PenjualanUsecase(Context context) {
        this.produkRepository = ProdukRepository.getInstance(ProdukLocalDataSource.getInstance(context));
    }

    public static PenjualanUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new PenjualanUsecase(context);

        return sInstance;
    }

    @Override
    public void loadProduk(@NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        produkRepository.loadProduk(loadProdukCallback);
    }

    @Override
    public void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        produkRepository.searchProduk(parameter,loadProdukCallback);
    }

    @Override
    public void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback) {
        produkRepository.addProduk(produk,addProdukCallback);
    }

    @Override
    public void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback) {

    }

    @Override
    public void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback) {

    }

}
