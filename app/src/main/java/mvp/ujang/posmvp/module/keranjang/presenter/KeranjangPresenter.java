package mvp.ujang.posmvp.module.keranjang.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.KeranjangContract;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;

public class KeranjangPresenter implements KeranjangContract.Presenter {

    private KeranjangContract.KeranjangView view;
    private KeranjangUsecase keranjangUsecase;
    private Context context;

    public KeranjangPresenter(KeranjangUsecase keranjangUsecase, KeranjangContract.KeranjangView view, Context context) {
        this.keranjangUsecase = keranjangUsecase;
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadKeranjang();
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
    public void editKeranjang(@NonNull Keranjang keranjang) {
        keranjangUsecase.editKeranjang(keranjang, new Callback.EditCallback<Keranjang>() {
            @Override
            public void onEditSuccess(@NonNull Keranjang response) {

            }

            @Override
            public void onEditFailed() {

            }
        });
    }

    @Override
    public void deleteKeranjang(@NonNull Keranjang keranjang) {
        keranjangUsecase.deleteKeranjang(keranjang, new Callback.DeleteCallback<Keranjang>() {
            @Override
            public void onDeleteSuccess(@NonNull Keranjang produk) {

            }

            @Override
            public void onDeleteFailed() {

            }
        });
    }

    @Override
    public void loadKategori() {
    }

}
