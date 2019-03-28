package mvp.ujang.posmvp.module.Penjualan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.PenjualanAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.Penjualan.model.Produk;

public class PenjualanFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private PenjualanAdapter adapter;
    private List<Produk> produkList;
    Button pencet;

    public PenjualanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penjualan, null);

        findViews(view);
        initViews(view);
        initListeners(view);

        return view;
    }

    public static PenjualanFragment newInstance(int tabSelected) {
        PenjualanFragment fragment = new PenjualanFragment();
        Bundle args = new Bundle();
        args.putInt("position", tabSelected);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        pencet = view.findViewById(R.id.btn);
    }

    @Override
    public void initViews(View view) {
        produkList = new ArrayList<>();
        generateList(produkList);
        loadData();
    }

    @Override
    public void initListeners(View view) {
        pencet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }

    public void generateList(List<Produk> produkList){
        adapter = new PenjualanAdapter(getContext(), produkList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        int[] covers = new int[]{
                R.drawable.img_produk,
                R.drawable.img_produk2,
                R.drawable.img_produk3,
                R.drawable.img_produk4};

        Produk a = new Produk("Kopi Good Day", "Rp. 10.000", covers[0]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Natural", "Rp. 15.000", covers[1]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Anti Bakteri", "Rp. 25.000", covers[2]);
        produkList.add(a);

        a = new Produk("Sampo Head & Shoulders", "Rp. 35.000", covers[3]);
        produkList.add(a);

        a = new Produk("Kopi Good Day", "Rp. 10.000", covers[0]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Natural", "Rp. 15.000", covers[1]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Anti Bakteri", "Rp. 25.000", covers[2]);
        produkList.add(a);

        a = new Produk("Sampo Head & Shoulders", "Rp. 35.000", covers[3]);
        produkList.add(a);

        a = new Produk("Kopi Good Day", "Rp. 10.000", covers[0]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Natural", "Rp. 15.000", covers[1]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Anti Bakteri", "Rp. 25.000", covers[2]);
        produkList.add(a);

        a = new Produk("Sampo Head & Shoulders", "Rp. 35.000", covers[3]);
        produkList.add(a);

        a = new Produk("Kopi Good Day", "Rp. 10.000", covers[0]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Natural", "Rp. 15.000", covers[1]);
        produkList.add(a);

        a = new Produk("Sabun Dettol Anti Bakteri", "Rp. 25.000", covers[2]);
        produkList.add(a);

        a = new Produk("Sampo Head & Shoulders", "Rp. 35.000", covers[3]);
        produkList.add(a);

        adapter.notifyDataSetChanged();
    }

    public void showBottomSheetDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);

        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();
    }
}
