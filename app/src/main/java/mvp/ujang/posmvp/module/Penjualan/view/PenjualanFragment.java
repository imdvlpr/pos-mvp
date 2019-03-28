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
import mvp.ujang.posmvp.module.Penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.Penjualan.model.Produk;
import mvp.ujang.posmvp.module.Penjualan.presenter.PenjualanPresenter;

public class PenjualanFragment extends BaseFragment implements PenjualanContract.PenjualanView {

    private PenjualanPresenter mPresenter;
    private RecyclerView recyclerView;
    private PenjualanAdapter adapter;
    private List<Produk> produkList;
    Button pencet;

    public PenjualanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penjualan, null);
        mPresenter = new PenjualanPresenter(this,getActivity().getApplicationContext());
        findViews(view);
        initViews(view);
        initListeners(view);
        fetchData();
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

    public void showBottomSheetDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();
    }

    public void fetchData(){
        mPresenter.loadData();
    }

    @Override
    public void listProduk(List<Produk> response) {
        produkList.clear();
        produkList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void setPresenter(PenjualanContract.Presenter presenter) {
    }
}
