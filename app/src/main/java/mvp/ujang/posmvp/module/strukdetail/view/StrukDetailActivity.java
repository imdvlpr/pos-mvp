package mvp.ujang.posmvp.module.strukdetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.StrukDetailAdapter;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.module.refund.view.RefundDetailActivity;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.strukdetail.StrukDetailContract;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.module.strukdetail.presenter.StrukDetailPresenter;
import mvp.ujang.posmvp.usecase.strukdetail.StrukDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class StrukDetailActivity extends BaseActivity implements StrukDetailContract.StrukView {

    private RecyclerView recyclerView;
    private TextView tanggal;
    private TextView kdTransaksi;
    private TextView total;
    private TextView tunai;
    private TextView kembalian;
    private StrukDetailAdapter adapter;
    //Context Component
    private Context context;

    private StrukDetailPresenter strukDetailPresenter;
    private ArrayList<StrukDetail> strukList = new ArrayList<>();
    private ArrayList<Struk> newStrukList = new ArrayList<>();
    private String transaksi  = "";
    private String tglTransaksi = "";
    private String totalTransaksi = "";
    private String uangTunai = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk_detail);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            transaksi       = extras.getString("kdTransaksi");
            tglTransaksi    = extras.getString("tglTransaksi");
            totalTransaksi  = extras.getString("totalTransaksi");
            uangTunai       = extras.getString("tunai");
        }

        context = this;
        strukDetailPresenter  = new StrukDetailPresenter(StrukDetailUsecase.getInstance(context),
                this,context);

        findViews();
        initViews();
        initListeners();
        fetchData();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
        tanggal      = findViewById(R.id.tanggal);
        kdTransaksi  = findViewById(R.id.kd_transaksi);
        total        = findViewById(R.id.total);
        tunai        = findViewById(R.id.tunai);
        kembalian    = findViewById(R.id.kembalian);
    }

    @Override
    public void initViews() {
        getSupportActionBar().setTitle("#"+transaksi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateList();

        tanggal.setText(Common.convertDateLong(tglTransaksi));
        kdTransaksi.setText("#"+transaksi);
        total.setText(Common.convertToRupiah(totalTransaksi));
        tunai.setText(Common.convertToRupiah(uangTunai));
        kembalian.setText(Common.convertToRupiah(String.valueOf(Integer.parseInt(uangTunai)-Integer.parseInt(totalTransaksi))));
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.refund:
                Intent i = new Intent(context, RefundDetailActivity.class);
                i.putExtra("kdTransaksi",transaksi);
                i.putExtra("tglTransaksi",tglTransaksi);
                i.putExtra("totalTransaksi",totalTransaksi);
                i.putExtra("tunai",uangTunai);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void generateList(){
        adapter = new StrukDetailAdapter(context, strukList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void fetchData(){
        StrukDetail strukDetail = new StrukDetail();
        strukDetail.setKdTransaksi(transaksi);
        strukDetailPresenter.loadStruk(strukDetail);
    }

    @Override
    public void listStruk(List<StrukDetail> response) {
        strukList.clear();
        strukList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull StrukDetailContract.Presenter presenter) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refund, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
