package mvp.ujang.posmvp.module.kategori.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;


import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.kategori.KategoriContract;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;

public class KategoriPresenter implements KategoriContract.Presenter {

    private KategoriContract.KategoriView view;
    private Context context;
    private KategoriUsecase kategoriUsecase;

    public KategoriPresenter(KategoriUsecase kategoriUsecase, KategoriContract.KategoriView view, Context context) {
        this.view = view;
        this.context = context;
        this.kategoriUsecase = kategoriUsecase;
    }


    @Override
    public void start() {
        loadKategori();
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
    public void addKategori(@NonNull Kategori kategori) {
        kategoriUsecase.addKategori(kategori, new Callback.AddCallback<Kategori>() {
            @Override
            public void onAddSuccess(@NonNull Kategori response) {
                view.addKategori(response);
            }

            @Override
            public void onAddFailed() {

            }
        });
    }

    @Override
    public void editKategori(@NonNull Kategori kategori) {

    }

    @Override
    public void deleteKategori(@NonNull Kategori kategori) {

    }
}
