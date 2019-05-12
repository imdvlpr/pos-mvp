package mvp.ujang.posmvp.module.refund.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.RefundContract;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;

public class RefundDetailPresenter implements RefundContract.Presenter {

    private RefundContract.StrukView view;
    private RefundDetailUsecase strukUsecase;
    private Context context;

    public RefundDetailPresenter(RefundDetailUsecase strukUsecase,
                                 RefundContract.StrukView view,
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
    public void loadStruk(RefundDetail strukDetail) {
        strukUsecase.loadStruk(strukDetail,new Callback.LoadCallback<RefundDetail>() {
            @Override
            public void onLoadSuccess(List<RefundDetail> response) {
                view.listStruk(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void refundBarang(RefundDetail strukDetail) {
        strukUsecase.refundBarang(strukDetail, new Callback.AddCallback<RefundDetail>() {
            @Override
            public void onAddSuccess(@NonNull RefundDetail response) {
            }

            @Override
            public void onAddFailed() {
            }
        });
    }

    @Override
    public void loadTotalRefund(RefundDetail strukDetail) {
        strukUsecase.loadTotalRefund(strukDetail, new Callback.LoadCallback<RefundDetail>() {
            @Override
            public void onLoadSuccess(List<RefundDetail> response) {
                view.totalRefund(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }
}
