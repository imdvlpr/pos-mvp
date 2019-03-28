package mvp.ujang.posmvp.module.Penjualan.presenter;

import mvp.ujang.posmvp.module.Penjualan.PenjualanContract;

public class PenjualanPresenter implements PenjualanContract.Presenter {

    private PenjualanContract.PenjualanView view;

    public PenjualanPresenter(PenjualanContract.PenjualanView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {

    }
}
