package mvp.ujang.posmvp.module.dashboard.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;

public interface DashboardDataSource {
    void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
    void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
    void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);

    //    void loadStruk(DashboardDetail strukDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
//    void refundBarang(DashboardDetail strukDetail, @NonNull Callback.AddCallback<DashboardDetail> callback);
//    void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback);
//    void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback);
//    void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback);
//    void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback);
}