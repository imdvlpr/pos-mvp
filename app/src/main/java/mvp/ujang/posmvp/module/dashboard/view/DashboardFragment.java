package mvp.ujang.posmvp.module.dashboard.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;

import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;
import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.DashboardBarangAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.dashboard.DashboardContract;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.module.dashboard.presenter.DashboardPresenter;
import mvp.ujang.posmvp.usecase.dashboard.DashboardDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class DashboardFragment extends BaseFragment implements DashboardContract.DashboardView {

    private Context context;

    private DashboardPresenter dashboardPresenter;
    private BarChart chart ;
    private ArrayList<BarEntry> chartValue ;
    private ArrayList<String> chartLabels ;
    private BarDataSet Bardataset ;
    private BarData BARDATA ;
    private DashboardBarangAdapter adapter;
    private List<DashboardDetail> dashboardBarangList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TextView totalSales;
    private TextView totalItems;
    private TextView totalRefund;
    private TextView grossSales;
    private TextView refunds;
    private TextView netSales;
    private TextView grossProfit;
    private DatePickerEditText datePicker;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_detail, null);
        context = getActivity().getApplicationContext();

        dashboardPresenter = new DashboardPresenter(DashboardDetailUsecase.getInstance(context),
                this,context);

        findViews(view);
        initViews(view);
        initListeners(view);
        DashboardDetail data = new DashboardDetail();
        data.setTanggal(Common.getDateByFormat("yyyy-MM-dd"));
        fetchData(data);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void findViews(View view) {
        chart       = view.findViewById(R.id.chart1);
        totalSales  = view.findViewById(R.id.totalSales);
        totalItems  = view.findViewById(R.id.totalItems);
        totalRefund = view.findViewById(R.id.totalRefund);
        grossSales  = view.findViewById(R.id.grossSales);
        refunds     = view.findViewById(R.id.refunds);
        netSales    = view.findViewById(R.id.netSales);
        grossProfit = view.findViewById(R.id.grossProfit);
        datePicker  = view.findViewById(R.id.datePicker);
        recyclerView= view.findViewById(R.id.recycler_view);
        datePicker.setManager(getActivity().getSupportFragmentManager());
        datePicker.setText(Common.getDateByFormat("dd/MM/yyyy"));
    }

    @Override
    public void initViews(View view) {
        generateList();
    }

    @Override
    public void initListeners(View view) {
        datePicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(context,datePicker.getText().toString(),Toast.LENGTH_LONG).show();
                DashboardDetail data = new DashboardDetail();
                data.setTanggal(Common.convertDate(datePicker.getText().toString(),"dd/MM/yyyy","yyyy-MM-dd"));
                fetchData(data);
            }
        });
    }

    public void generateList(){
        adapter = new DashboardBarangAdapter(getContext(), dashboardBarangList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void generateDataView(List<DashboardDetail> dataInfoPenjualan){
        DashboardDetail data = dataInfoPenjualan.get(0);
        totalItems.setText(data.getTotalItem());
        totalSales.setText(data.getTotalSales());
        totalRefund.setText(data.getTotalRefund());
        grossSales.setText(Common.convertToRupiah(data.getGrossSales()));
        refunds.setText(Common.convertToRupiah(data.getRefunds()));
        netSales.setText(Common.convertToRupiah(data.getNetSales()));
        grossProfit.setText(Common.convertToRupiah(data.getGrossProfit()));
    }

    private void fetchData(DashboardDetail dashboardDetail){
        dashboardPresenter.loadInfoPenjualan(dashboardDetail);
        dashboardPresenter.loadInfoGross(dashboardDetail);
        dashboardPresenter.loadListBarang(dashboardDetail);
    }

    private void generateChart(List<DashboardDetail> data){
        int[] colors = {Color.rgb(97, 203, 218)};
        chartValue = new ArrayList<>();
        chartLabels = new ArrayList<String>();
        addValueChart(data);
        addLabelsChart(data);
        Bardataset = new BarDataSet(chartValue, "Gross Sales");
        BARDATA = new BarData(chartLabels, Bardataset);
        Bardataset.setColors(colors);
        chart.setData(BARDATA);
        chart.animateY(3000);
    }


    public void addValueChart(List<DashboardDetail> data){
        for (int i = 0 ;i<data.size();i++)
            chartValue.add(new BarEntry(Float.parseFloat(data.get(i).getGrossSales()), i));
    }

    public void addLabelsChart(List<DashboardDetail> data){
        for (int i = 0 ;i<data.size();i++)
            chartLabels.add(Common.getMonthName(data.get(i).getBulan()));
    }



    @Override
    public void listInfoPenjualan(List<DashboardDetail> response) {
        Log.d("detailDashboard",response.toString());
        generateDataView(response);
    }

    @Override
    public void listInfoGross(List<DashboardDetail> response) {
        Log.d("detailInfoGross",response.toString());
        generateChart(response);
    }

    @Override
    public void listInfoBarang(List<DashboardDetail> response) {
        Log.d("detailInfoGross",response.toString());
        dashboardBarangList.clear();
        dashboardBarangList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull DashboardContract.Presenter presenter) {

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSearch=menu.findItem(R.id.action_search);
        if(itemSearch!=null)
            itemSearch.setVisible(false);

        MenuItem itemKeranjang=menu.findItem(R.id.action_keranjang);
        if(itemKeranjang!=null)
            itemKeranjang.setVisible(false);
    }
}
