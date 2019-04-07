package mvp.ujang.posmvp.module.produk.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.model.Produk;

public interface ProdukDataSource {
    void loadProduk(@NonNull Callback.LoadCallback<Produk> loadProdukCallback);
    void searchProduk(Produk parameter,@NonNull Callback.LoadCallback<Produk> loadProdukCallback);
    void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback);
    void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback);
    void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback);
}