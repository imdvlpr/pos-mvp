package mvp.ujang.posmvp.module.struk;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.struk.model.Struk;

public class StrukContract {

    public interface StrukView extends BaseView<Presenter> {
        void listStruk(List<Struk> response);
    }

    public interface Presenter extends BasePresenter {
        void loadStruk();
    }


}
