package mvp.ujang.posmvp.module.transaksi.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.TransaksiContract;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.module.transaksi.view.StrukFragment;
import mvp.ujang.posmvp.usecase.transaksi.TransaksiUsecase;
import mvp.ujang.posmvp.utils.Common;

public class TransaksiPresenter implements TransaksiContract.Presenter {

    private TransaksiContract.StrukView view;
    private TransaksiUsecase transaksiUsecase;
    private Context context;
    private String TAG = StrukFragment.class.getSimpleName();

    public TransaksiPresenter(TransaksiUsecase transaksiUsecase,
                              TransaksiContract.StrukView view,
                              Context context) {
        this.transaksiUsecase = transaksiUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadStruk();
    }


    @Override
    public void loadStruk() {
        final long startTime = System.currentTimeMillis();
        transaksiUsecase.loadTransaksi(new Callback.LoadCallback<Transaksi>() {
            @Override
            public void onLoadSuccess(List<Transaksi> response) {
                view.listTransaksi(response);
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }
        });
    }
}
