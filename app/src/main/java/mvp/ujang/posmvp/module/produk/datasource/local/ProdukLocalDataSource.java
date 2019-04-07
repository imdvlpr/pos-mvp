package mvp.ujang.posmvp.module.produk.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.produk.datasource.ProdukDataSource;
import mvp.ujang.posmvp.module.produk.model.Produk;
import mvp.ujang.posmvp.utils.Connection;

public class ProdukLocalDataSource implements ProdukDataSource {

    private static ProdukLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private ProdukLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static ProdukLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new ProdukLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadProduk(@NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        ArrayList<Produk> list = new ArrayList<Produk>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.query("Barang", new String[] { "satuan","gambar_barang","stok_barang","harga_jual_barang","id_kategori","kd_barang","deskripsi_barang","nama_barang"},null,null, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Produk item = new Produk(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            Log.d("loadData",list.toString());
            loadProdukCallback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void searchProduk(Produk parameter, @NonNull Callback.LoadCallback<Produk> loadProdukCallback) {
        String query =  "(id_kategori = '"+parameter.getIdKategori()+ "' or "+parameter.getIdKategori()+" = 0) "+
                " and (nama_barang like '%"+parameter.getNamaBarang()+  "%' or '"+parameter.getNamaBarang()+"' isnull) ";

        ArrayList<Produk> list = new ArrayList<Produk>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.query("Barang", new String[] { "satuan","gambar_barang","stok_barang","harga_jual_barang","id_kategori","kd_barang","deskripsi_barang","nama_barang"},query,null, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Produk item = new Produk(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            loadProdukCallback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void addProduk(@NonNull Produk produk, @NonNull Callback.AddCallback<Produk> addProdukCallback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        ContentValues values = new ContentValues();
        values.put("kd_barang", produk.getKdBarang());
        values.put("nama_barang",produk.getNamaBarang());
        values.put("harga_jual_barang",produk.getHargaJualBarang());
        values.put("satuan",produk.getSatuan());
        values.put("stok_barang",produk.getStokBarang());
        values.put("deskripsi_barang",produk.getDeskripsiBarang());
        values.put("gambar_barang",produk.getGambarBarang());
        values.put("id_kategori",produk.getIdKategori());
        long returnValue = database.insert("Barang", null, values);

        if (returnValue!=0)
            addProdukCallback.onAddSuccess(produk);
        else
            addProdukCallback.onAddFailed();
    }

    @Override
    public void editProduk(@NonNull Produk produk, @NonNull Callback.EditCallback<Produk> editProdukCallback) {

    }

    @Override
    public void deleteProduk(@NonNull Produk produk, @NonNull Callback.DeleteCallback<Produk> deleteProdukCallback) {

    }
}


