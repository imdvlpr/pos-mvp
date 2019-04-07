package mvp.ujang.posmvp.module.produk;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class ProdukContract {

    public interface ProdukView extends BaseView<Presenter> {
            void listProduk(List<Produk> response);
            void listKategori(List<Kategori> response);
            void addProduk(Produk response);
            void detailProduk(Produk response);
    }

    public interface Presenter extends BasePresenter {
        void loadProduk();

        void searchProduk(@NonNull Produk param);

        void addProduk(@NonNull Produk produk);

        void editProduk(@NonNull Produk produk);

        void deleteProduk(@NonNull Produk produk);

        void loadKategori();
    }


}
