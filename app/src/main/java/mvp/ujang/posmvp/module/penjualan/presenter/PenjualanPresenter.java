package mvp.ujang.posmvp.module.penjualan.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.penjualan.PenjualanUsecase;

public class PenjualanPresenter implements PenjualanContract.Presenter {

    private PenjualanContract.PenjualanView view;
    private KategoriUsecase  kategoriUsecase;
    private PenjualanUsecase penjualanUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;

    public PenjualanPresenter(PenjualanUsecase penjualanUsecase,
                              KategoriUsecase kategoriUsecase,
                              KeranjangUsecase keranjangUsecase,
                              PenjualanContract.PenjualanView view,
                              Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.penjualanUsecase = penjualanUsecase;
        this.keranjangUsecase = keranjangUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadPenjualan();
    }

    @Override
    public void loadPenjualan() {
        penjualanUsecase.loadPenjualan(new Callback.LoadCallback<Penjualan>() {
            @Override
            public void onLoadSuccess(List<Penjualan> response) {
                view.listProduk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void searchPenjualan(@NonNull Penjualan param) {
        penjualanUsecase.searchPenjualan(param, new Callback.LoadCallback<Penjualan>() {
            @Override
            public void onLoadSuccess(List<Penjualan> response) {
                view.listProduk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
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

    @Override
    public void addKeranjang(@NonNull Keranjang keranjang) {
        keranjangUsecase.addKeranjang(keranjang, new Callback.AddCallback<Keranjang>() {
            @Override
            public void onAddSuccess(@NonNull Keranjang response) {

            }

            @Override
            public void onAddFailed() {

            }
        });
    }

    @Override
    public void loadKeranjang() {
        keranjangUsecase.loadKeranjang(new Callback.LoadCallback<Keranjang>() {
            @Override
            public void onLoadSuccess(List<Keranjang> response) {
                view.listKeranjang(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

}
