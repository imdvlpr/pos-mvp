package mvp.ujang.posmvp.module.transaksidetail;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;

public class StrukDetailContract {

    public interface StrukView extends BaseView<Presenter> {
        void listStruk(List<TransksiDetail> response);
    }

    public interface Presenter extends BasePresenter {
        void loadStruk(TransksiDetail transksiDetail);
    }


}
