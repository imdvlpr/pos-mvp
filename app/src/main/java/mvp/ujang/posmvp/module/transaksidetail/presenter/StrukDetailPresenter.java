package mvp.ujang.posmvp.module.transaksidetail.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksidetail.StrukDetailContract;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;
import mvp.ujang.posmvp.module.transaksidetail.view.StrukDetailActivity;
import mvp.ujang.posmvp.usecase.transaksidetail.TransaksiDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class StrukDetailPresenter implements StrukDetailContract.Presenter {

    private StrukDetailContract.StrukView view;
    private TransaksiDetailUsecase strukUsecase;
    private Context context;
    private String TAG = StrukDetailActivity.class.getSimpleName();

    public StrukDetailPresenter(TransaksiDetailUsecase strukUsecase,
                                StrukDetailContract.StrukView view,
                                Context context) {
        this.strukUsecase = strukUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadStruk(null);
    }



    @Override
    public void loadStruk(TransksiDetail transksiDetail) {
        final long startTime = System.currentTimeMillis();
        strukUsecase.loadTransaksi(transksiDetail,new Callback.LoadCallback<TransksiDetail>() {
            @Override
            public void onLoadSuccess(List<TransksiDetail> response) {
                view.listStruk(response);
                Common.printTimeMillis(TAG+" Load Data Transaksi Detail",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Transaksi Detail",startTime,System.currentTimeMillis());
            }
        });
    }
}
