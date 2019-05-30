package mvp.ujang.posmvp.module.barang;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class ProdukContract {

    public interface ProdukView extends BaseView<Presenter> {
            void listProduk(List<Barang> response);
            void listKategori(List<Kategori> response);
            void addProduk(Barang response);
            void detailProduk(Barang response);
    }

    public interface Presenter extends BasePresenter {
        void loadProduk();

        void searchProduk(@NonNull Barang param);

        void addProduk(@NonNull Barang barang);

        void editProduk(@NonNull Barang barang);

        void deleteProduk(@NonNull Barang barang);

        void loadKategori();
    }


}
