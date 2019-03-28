package mvp.ujang.posmvp.module.kategori.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.KategoriAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class KategoriFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private KategoriAdapter adapter;
    private List<Kategori> kategoriList;

    public KategoriFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kategori, null);

        findViews(view);
        initViews(view);
        initListeners(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void initViews(View view) {
        kategoriList = new ArrayList<>();
        generateList(kategoriList);
        loadData();
    }

    @Override
    public void initListeners(View view) {

    }

    public void generateList(List<Kategori> geoparkDataList){
        adapter = new KategoriAdapter(getContext(), geoparkDataList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        int[] covers = new int[]{
                R.color.colorAccent,
                R.color.blue_violet,
                R.color.indian_red,
                R.color.yellow_green,
                R.color.cadet_blue,
                R.color.deep_pink,
                R.color.dark_sea_green,
                R.color.dim_gray,
        };

        Kategori a = new Kategori("Kopi", "10 Barang", covers[0]);
        kategoriList.add(a);

        a = new Kategori("Roko", "100 Barang", covers[1]);
        kategoriList.add(a);

        a = new Kategori("Sabun", "130 Barang", covers[2]);
        kategoriList.add(a);

        a = new Kategori("Sembako", "200 Barang", covers[3]);
        kategoriList.add(a);

        a = new Kategori("Sampo", "200 Barang", covers[4]);
        kategoriList.add(a);

        a = new Kategori("Aksesoris", "100 Barang", covers[5]);
        kategoriList.add(a);

        adapter.notifyDataSetChanged();
    }
}
