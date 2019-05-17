package mvp.ujang.posmvp.module.produk.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.view.PembayaranActivity;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.produk.ProdukContract;
import mvp.ujang.posmvp.module.produk.view.ProdukFragment;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.penjualan.PenjualanUsecase;
import mvp.ujang.posmvp.usecase.produk.ProdukUsecase;
import mvp.ujang.posmvp.utils.Common;

public class ProdukPresenter implements ProdukContract.Presenter {

    private ProdukContract.ProdukView view;
    private KategoriUsecase  kategoriUsecase;
    private ProdukUsecase produkUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;
    private String TAG = ProdukFragment.class.getSimpleName();

    public ProdukPresenter(ProdukUsecase produkUsecase,
                           KategoriUsecase kategoriUsecase,
                           ProdukContract.ProdukView view,
                           Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.produkUsecase = produkUsecase;
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
        final long startTime = System.currentTimeMillis();
        produkUsecase.loadProduk(new Callback.LoadCallback<Produk>() {
            @Override
            public void onLoadSuccess(List<Produk> response) {
                view.listProduk(response);
                Common.printTimeMillis(TAG+" Load Data Produk",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Produk",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void searchProduk(@NonNull Produk param) {
        final long startTime = System.currentTimeMillis();
        produkUsecase.searchProduk(param, new Callback.LoadCallback<Produk>() {
            @Override
            public void onLoadSuccess(List<Produk> response) {
                view.listProduk(response);
                Common.printTimeMillis(TAG+" Search Data Produk",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Search Data Produk",startTime,System.currentTimeMillis());
            }
        });
    }


    @Override
    public void addProduk(@NonNull Produk produk) {
        final long startTime = System.currentTimeMillis();
        produkUsecase.addProduk(produk, new Callback.AddCallback<Produk>() {
            @Override
            public void onAddSuccess(@NonNull Produk response) {
                view.addProduk(response);
                Common.printTimeMillis(TAG+" Add Data Produk",startTime,System.currentTimeMillis());

            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Data Produk",startTime,System.currentTimeMillis());
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
        final long startTime = System.currentTimeMillis();
        kategoriUsecase.loadKategori(new Callback.LoadCallback<Kategori>() {
            @Override
            public void onLoadSuccess(List<Kategori> response) {
                view.listKategori(response);
                Common.printTimeMillis(TAG+" Load Data Kategori",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Kategori",startTime,System.currentTimeMillis());
            }
        });
    }

}
