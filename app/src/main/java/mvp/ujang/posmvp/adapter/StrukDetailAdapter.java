package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.utils.Common;

public class StrukDetailAdapter extends RecyclerView.Adapter<StrukDetailAdapter.MyViewHolder> {

    private Context mContext;
    private List<StrukDetail> strukList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView     tvTotalBayar;
        public TextView     namaBarang;
        public TextView     jumlah;
        public MyViewHolder(View view) {
            super(view);
            tvTotalBayar    = view.findViewById(R.id.total);
            namaBarang      = view.findViewById(R.id.nama_barang);
            jumlah          = view.findViewById(R.id.jumlah);
        }
    }

    public StrukDetailAdapter(Context mContext, List<StrukDetail> strukList) {
        this.mContext = mContext;
        this.strukList = strukList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_struk_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        StrukDetail struk = strukList.get(position);
        holder.tvTotalBayar.setText(Common.convertToRupiah(String.valueOf(Integer.parseInt(struk.getJumlah())*Integer.parseInt(struk.getHargaBarang()))));
        holder.namaBarang.setText(struk.getNamaBarang());
        holder.jumlah.setText(struk.getJumlah()+" x "+struk.getHargaBarang());
    }

    @Override
    public int getItemCount() {
        return strukList.size();
    }
}