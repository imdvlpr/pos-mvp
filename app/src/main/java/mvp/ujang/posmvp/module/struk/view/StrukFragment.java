package mvp.ujang.posmvp.module.struk.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import mvp.ujang.posmvp.adapter.StrukAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.struk.StrukContract;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.struk.presenter.StrukPresenter;
import mvp.ujang.posmvp.module.strukdetail.view.StrukDetailActivity;
import mvp.ujang.posmvp.usecase.struk.StrukUsecase;
import mvp.ujang.posmvp.utils.RecyclerItemClickListener;

public class StrukFragment extends BaseFragment implements StrukContract.StrukView {

    private RecyclerView recyclerView;
    private StrukAdapter adapter;
    //Context Component
    private Context context;

    private StrukPresenter strukPresenter;
    private ArrayList<Struk> strukList = new ArrayList<>();
    private ArrayList<Struk> newStrukList = new ArrayList<>();
    public StrukFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_struk, null);
        context = getActivity().getApplicationContext();
        strukPresenter  = new StrukPresenter(StrukUsecase.getInstance(context),
                this,context);

        findViews(view);
        initViews(view);
        initListeners(view);
        fetchData();
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
        generateList();
    }

    @Override
    public void initListeners(View view) {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,recyclerView,
                new RecyclerItemClickListener.OnItemClickListener(){

                    @Override
                    public void onItemClick(View view, int position) {
                        if (!newStrukList.get(position).isStatus()){
                            Intent i = new Intent(context, StrukDetailActivity.class);
                            i.putExtra("kdTransaksi",newStrukList.get(position).getKdTransaksi());
                            i.putExtra("tglTransaksi",newStrukList.get(position).getTanggal());
                            i.putExtra("totalTransaksi",newStrukList.get(position).getTotalPembayaran());
                            i.putExtra("tunai",newStrukList.get(position).getTunai());
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                }));
    }

    public void generateList(){
        adapter = new StrukAdapter(getContext(), newStrukList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listStruk(List<Struk> response) {
        strukList.clear();
        newStrukList.clear();
        strukList.addAll(response);

        for (int i = 0;i<strukList.size();i++){
            Struk item = new Struk();
            item.setTanggal(strukList.get(i).getTanggal());
            item.setStatus(true);

            if (i == 0)
                newStrukList.add(item);
            else
                if (!strukList.get(i).getGroupingDate().equals(strukList.get(i-1).getGroupingDate()))
                    newStrukList.add(item);


            newStrukList.add(strukList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull StrukContract.Presenter presenter) {

    }


    public void fetchData(){
        strukPresenter.loadStruk();
    }
}

