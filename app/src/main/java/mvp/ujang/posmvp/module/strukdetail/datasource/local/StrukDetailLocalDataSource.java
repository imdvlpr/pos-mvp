package mvp.ujang.posmvp.module.strukdetail.datasource.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.strukdetail.datasource.StrukDetailDataSource;
import mvp.ujang.posmvp.module.strukdetail.model.StrukDetail;
import mvp.ujang.posmvp.module.strukdetail.view.StrukDetailActivity;
import mvp.ujang.posmvp.utils.Connection;

public class StrukDetailLocalDataSource implements StrukDetailDataSource {

    private static StrukDetailLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private StrukDetailLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static StrukDetailLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new StrukDetailLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadStruk(StrukDetail strukDetail, @NonNull Callback.LoadCallback<StrukDetail> callback) {
        ArrayList<StrukDetail> list = new ArrayList<>();
        String query = "select T.kd_transaksi,T.tgl_transaksi,T.total_pembayaran,T.transaksi_oleh,D.kd_barang,D.jumlah,D.harga_barang,B.nama_barang\n" +
                "from Transaksi T join Detail_Transaksi D on T.kd_transaksi = D.kd_transaksi join Barang B on D.kd_barang = B.kd_barang " +
                "WHERE T.KD_TRANSAKSI = '"+strukDetail.getKdTransaksi()+"'" +
                "\n";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_struk","success_struk"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        StrukDetail item = new StrukDetail();
                        item.setKdTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("kd_transaksi")));
                        item.setKdBarang(cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")));
                        item.setTglTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("tgl_transaksi")));
                        item.setJumlah(cursor.getString(cursor.getColumnIndexOrThrow("jumlah")));
                        item.setHargaBarang(cursor.getString(cursor.getColumnIndexOrThrow("harga_barang")));
                        item.setNamaBarang(cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")));
                        item.setTotalPembayaran(cursor.getString(cursor.getColumnIndexOrThrow("total_pembayaran")));
                        list.add(item);

                        Log.d("data_struk",item.toString());

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_struk","error_struk",e);
        }

        connection.close();
    }
}


