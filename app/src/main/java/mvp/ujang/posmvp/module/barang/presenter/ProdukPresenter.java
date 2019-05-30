package mvp.ujang.posmvp.module.barang.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.barang.ProdukContract;
import mvp.ujang.posmvp.module.barang.view.ProdukFragment;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.barang.BarangUsecase;
import mvp.ujang.posmvp.utils.Common;

public class ProdukPresenter implements ProdukContract.Presenter {

    private ProdukContract.ProdukView view;
    private KategoriUsecase  kategoriUsecase;
    private BarangUsecase barangUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;
    private String TAG = ProdukFragment.class.getSimpleName();

    public ProdukPresenter(BarangUsecase barangUsecase,
                           KategoriUsecase kategoriUsecase,
                           ProdukContract.ProdukView view,
                           Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.barangUsecase = barangUsecase;
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
        barangUsecase.loadProduk(new Callback.LoadCallback<Barang>() {
            @Override
            public void onLoadSuccess(List<Barang> response) {
                view.listProduk(response);
                Common.printTimeMillis(TAG+" Load Data Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Barang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void searchProduk(@NonNull Barang param) {
        final long startTime = System.currentTimeMillis();
        barangUsecase.searchBarag(param, new Callback.LoadCallback<Barang>() {
            @Override
            public void onLoadSuccess(List<Barang> response) {
                view.listProduk(response);
                Common.printTimeMillis(TAG+" Search Data Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Search Data Barang",startTime,System.currentTimeMillis());
            }
        });
    }


    @Override
    public void addProduk(@NonNull Barang barang) {
        final long startTime = System.currentTimeMillis();
        barangUsecase.addBarang(barang, new Callback.AddCallback<Barang>() {
            @Override
            public void onAddSuccess(@NonNull Barang response) {
                view.addProduk(response);
                Common.printTimeMillis(TAG+" Add Data Barang",startTime,System.currentTimeMillis());

            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Data Barang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void editProduk(@NonNull Barang barang) {

    }

    @Override
    public void deleteProduk(@NonNull Barang barang) {

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
