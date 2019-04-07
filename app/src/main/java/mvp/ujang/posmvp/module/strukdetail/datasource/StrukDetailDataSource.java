package mvp.ujang.posmvp.module.strukdetail.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.module.strukdetail.view.StrukDetailActivity;

public interface StrukDetailDataSource {
    void loadStruk(StrukDetail strukDetail, @NonNull Callback.LoadCallback<StrukDetail> callback);
//    void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback);
//    void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback);
//    void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback);
//    void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback);
}