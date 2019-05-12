package mvp.ujang.posmvp.module.dashboard.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.DashboardContract;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.usecase.dashboard.DashboardDetailUsecase;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.DashboardView view;
    private DashboardDetailUsecase dashboardDetailUsecase;
    private Context context;

    public DashboardPresenter(DashboardDetailUsecase dashboardDetailUsecase,
                              DashboardContract.DashboardView view,
                              Context context) {
        this.dashboardDetailUsecase = dashboardDetailUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadInfoPenjualan(null);
    }


    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail) {
        dashboardDetailUsecase.loadInfoPenjualan(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoPenjualan(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void loadInfoGross(DashboardDetail dashboardDetail) {
        dashboardDetailUsecase.loadInfoGross(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoGross(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail) {
        dashboardDetailUsecase.loadListBarang(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoBarang(response);
            }

            @Override
            public void onLoadFailed() {

            }
        });
    }
}
