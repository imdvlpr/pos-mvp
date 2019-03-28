package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.Penjualan.model.Produk;
import mvp.ujang.posmvp.module.Penjualan.view.PenjualanFragment;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.MyViewHolder> {

    private Context mContext;
    private List<Produk> produkList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public ImageView imgProduk;
        public TextView tvHarga;
        public Button btnPesan;

        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_produk);
            tvHarga = view.findViewById(R.id.tv_harga_produk);
            imgProduk = view.findViewById(R.id.img_produk);
            btnPesan = view.findViewById(R.id.btn_pesan);
        }
    }

    public PenjualanAdapter(Context mContext, List<Produk> produkList) {
        this.mContext = mContext;
        this.produkList = produkList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_penjualan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Produk produk = produkList.get(position);
        holder.tvNama.setText(produk.getNama());
        holder.tvHarga.setText(produk.getHarga());
        holder.btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PenjualanFragment fragment = new PenjualanFragment();
                fragment.showBottomSheetDialog();
            }
        });

        Glide.with(mContext).load(produk.getImage()).into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return produkList.size();
    }
}