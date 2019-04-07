package mvp.ujang.posmvp.module.strukdetail;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.module.strukdetail.view.StrukDetailActivity;

public class StrukDetailContract {

    public interface StrukView extends BaseView<Presenter> {
        void listStruk(List<StrukDetail> response);
    }

    public interface Presenter extends BasePresenter {
        void loadStruk(StrukDetail strukDetail);
    }


}
