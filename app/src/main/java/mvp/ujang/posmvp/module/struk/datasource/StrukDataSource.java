package mvp.ujang.posmvp.module.struk.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.struk.model.Struk;

public interface StrukDataSource {
    void loadStruk(@NonNull Callback.LoadCallback<Struk> callback);
//    void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback);
//    void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback);
//    void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback);
//    void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback);
}