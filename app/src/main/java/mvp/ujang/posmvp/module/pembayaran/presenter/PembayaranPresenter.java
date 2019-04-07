package mvp.ujang.posmvp.module.pembayaran.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.PembayaranContract;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;
import mvp.ujang.posmvp.usecase.pembayaran.PembayaranUsecase;

public class PembayaranPresenter implements PembayaranContract.Presenter {

    private PembayaranContract.PembayaranView view;
    private PembayaranUsecase pembayaranUsecase;
    private Context context;

    public PembayaranPresenter(PembayaranUsecase pembayaranUsecase,
                               PembayaranContract.PembayaranView view,
                               Context context) {
        this.pembayaranUsecase = pembayaranUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        totalPembayaran();
    }


    @Override
    public void totalPembayaran() {
        pembayaranUsecase.loadTotalPembayaran(new Callback.LoadCallback<Pembayaran>() {
            @Override
            public void onLoadSuccess(List<Pembayaran> response) {
                view.totalPembayaran(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void addPembayaran(@NonNull Pembayaran pembayaran) {
        pembayaranUsecase.addPembayaran(pembayaran, new Callback.AddCallback<Pembayaran>() {
            @Override
            public void onAddSuccess(@NonNull Pembayaran response) {

            }

            @Override
            public void onAddFailed() {

            }
        });
    }
}
