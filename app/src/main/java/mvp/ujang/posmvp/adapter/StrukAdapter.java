package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.module.struk.model.Struk;
import mvp.ujang.posmvp.utils.Common;

public class StrukAdapter extends RecyclerView.Adapter<StrukAdapter.MyViewHolder> {

    private Context mContext;
    private List<Struk> strukList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView     tvTotalBayar;
        public TextView     tvWaktu;
        public TextView     tvKdTransaksi;
        public TextView     header;
        public RelativeLayout body;
        public MyViewHolder(View view) {
            super(view);
            tvTotalBayar    = view.findViewById(R.id.tv_total);
            tvWaktu         = view.findViewById(R.id.tv_waktu);
            tvKdTransaksi   = view.findViewById(R.id.tv_kdTransaksi);
            header          = view.findViewById(R.id.header);
            body            = view.findViewById(R.id.body);
        }
    }

    public StrukAdapter(Context mContext, List<Struk> strukList) {
        this.mContext = mContext;
        this.strukList = strukList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_struk, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Struk struk = strukList.get(position);
        if (struk.isStatus()){
            holder.header.setVisibility(View.VISIBLE);
            holder.body.setVisibility(View.GONE);
        }else{
            holder.header.setVisibility(View.GONE);
            holder.body.setVisibility(View.VISIBLE);
            holder.tvTotalBayar.setText(Common.convertToRupiah(struk.getTotalPembayaran()));
            holder.tvWaktu.setText(struk.getWaktu());
            holder.tvKdTransaksi.setText("#"+struk.getKdTransaksi());
        }
        holder.header.setText(Common.convertDateLong(struk.getTanggal()));

    }

    @Override
    public int getItemCount() {
        return strukList.size();
    }
}