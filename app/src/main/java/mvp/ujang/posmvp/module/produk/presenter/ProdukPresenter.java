package mvp.ujang.posmvp.module.produk.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.produk.ProdukContract;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.penjualan.PenjualanUsecase;

public class ProdukPresenter implements ProdukContract.Presenter {

    private ProdukContract.ProdukView view;
    private KategoriUsecase  kategoriUsecase;
    private PenjualanUsecase penjualanUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;

    public ProdukPresenter(PenjualanUsecase penjualanUsecase,
                           KategoriUsecase kategoriUsecase,
                           ProdukContract.ProdukView view,
                           Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.penjualanUsecase = penjualanUsecase;
        this.keranjangUsecase = keranjangUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadProduk();
    }

    @Override
    public void loadProduk() {
        penjualanUsecase.loadProduk(new Callback.LoadCallback<Produk>() {
            @Override
            public void onLoadSuccess(List<Produk> response) {
                view.listProduk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void searchProduk(@NonNull Produk param) {
        penjualanUsecase.searchProduk(param, new Callback.LoadCallback<Produk>() {
            @Override
            public void onLoadSuccess(List<Produk> response) {
                view.listProduk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }


    @Override
    public void addProduk(@NonNull Produk produk) {
        penjualanUsecase.addProduk(produk, new Callback.AddCallback<Produk>() {
            @Override
            public void onAddSuccess(@NonNull Produk response) {
                view.addProduk(response);
            }

            @Override
            public void onAddFailed() {

            }
        });
    }

    @Override
    public void editProduk(@NonNull Produk produk) {

    }

    @Override
    public void deleteProduk(@NonNull Produk produk) {

    }

    @Override
    public void loadKategori() {
        kategoriUsecase.loadKategori(new Callback.LoadCallback<Kategori>() {
            @Override
            public void onLoadSuccess(List<Kategori> response) {
                view.listKategori(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

}
