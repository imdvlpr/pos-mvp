package mvp.ujang.posmvp.module.Penjualan.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ajts.androidmads.sqliteimpex.SQLiteImporterExporter;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.module.Penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.Penjualan.model.Produk;

public class PenjualanPresenter implements PenjualanContract.Presenter {

    private PenjualanContract.PenjualanView view;
    private Context context;
    private SQLiteDatabase database;
    private SQLiteImporterExporter dbHelper;
    public static String db = "POS_DB.sqlite";

    public PenjualanPresenter(PenjualanContract.PenjualanView view,Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {
        dbHelper = new SQLiteImporterExporter(context, db);
        try {
            dbHelper.importDataBaseFromAssets();
        } catch (Exception e) {
            e.printStackTrace();
        }

        database = dbHelper.getWritableDatabase();

        List<Produk> list = new ArrayList<>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor=database.query("Barang", new String[] { "SATUAN","GAMBAR_BARANG","STOK_BARANG","HARGA_JUAL_BARANG","ID_KATEGORI","KD_BARANG","DESKRIPSI_BARANG","NAMA_BARANG"},null,null, null, null, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Produk produk = new Produk(cursor.getString(cursor.getColumnIndexOrThrow("SATUAN")),cursor.getString(cursor.getColumnIndexOrThrow("GAMBAR_BARANG")),cursor.getString(cursor.getColumnIndexOrThrow("STOK_BARANG")),cursor.getString(cursor.getColumnIndexOrThrow("HARGA_JUAL_BARANG")),cursor.getInt(cursor.getColumnIndexOrThrow("ID_KATEGORI")),cursor.getString(cursor.getColumnIndexOrThrow("KD_BARANG")),cursor.getString(cursor.getColumnIndexOrThrow("DESKRIPSI_BARANG")),cursor.getString(cursor.getColumnIndexOrThrow("NAMA_BARANG")));
                        list.add(produk);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            view.listProduk(list);
            Log.d("list_data",""+list.size());
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
    }
}
