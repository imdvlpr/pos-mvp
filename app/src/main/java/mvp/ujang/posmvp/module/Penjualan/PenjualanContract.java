package mvp.ujang.posmvp.module.Penjualan;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.Penjualan.model.Produk;

public class PenjualanContract  {

    public interface PenjualanView extends BaseView<Presenter> {
        void listProduk(List<Produk> response);
        void onSuccess();
        void onFailure();
        void onFailed();
    }

    public interface Presenter extends BasePresenter {
        void loadData();
    }
}
