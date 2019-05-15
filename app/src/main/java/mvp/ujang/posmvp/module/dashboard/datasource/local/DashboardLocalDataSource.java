package mvp.ujang.posmvp.module.dashboard.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.datasource.DashboardDataSource;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.utils.Connection;

public class DashboardLocalDataSource implements DashboardDataSource {

    private static DashboardLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private DashboardLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static DashboardLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new DashboardLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        ArrayList<DashboardDetail> list = new ArrayList<>();

        String where = "";
        if (dashboardDetail.getMode().equals("Harian"))
            where = "where date(b.tgl_transaksi) = '"+dashboardDetail.getTanggal()+"'";
        else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where strftime('%m', b.tgl_transaksi) = substr('00'||"+dashboardDetail.getBulan()+",-2)";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where strftime('%Y', b.tgl_transaksi) = '"+dashboardDetail.getTahun()+"'";

        String query = "select a.*,b.*,c.*,d.*,e.*,f.*,g.* from \n" +
                "(select ifnull(sum(total_pembayaran),0) as gross_sales from transaksi b "+where+") a,\n" +
                "(select ifnull(sum((harga_barang * jumlah )),0) as refunds from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+") b,\n" +
                "(select ifnull(sum((total_pembayaran)),0) as gross_profit from transaksi b "+where+") c,\n" +
                "(select ifnull(sum((total_pembayaran)),0) as net_sales from transaksi b "+where+") d,\n" +
                "(select ifnull(count(*),0) as total_sales from transaksi b "+where+") e,\n" +
                "(select ifnull(sum(jumlah),0) as total_item from detail_transaksi a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+") f,\n" +
                "(select ifnull(count(distinct a.kd_transaksi),0) as total_refund from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+")g\n";


//        String query = "select a.*,b.*,c.*,d.*,e.*,f.*,g.* from \n" +
//                "(select ifnull(sum(total_pembayaran),0) as gross_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') a,\n" +
//                "(select ifnull(sum((harga_barang * jumlah )),0) as refunds from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"') b,\n" +
//                "(select ifnull(sum((total_pembayaran)),0) as gross_profit from transaksi where date(tgl_transaksi) = '"+tanggal+"') c,\n" +
//                "(select ifnull(sum((total_pembayaran)),0) as net_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') d,\n" +
//                "(select ifnull(count(*),0) as total_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') e,\n" +
//                "(select ifnull(sum(jumlah),0) as total_item from detail_transaksi a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"') f,\n" +
//                "(select ifnull(count(distinct a.kd_transaksi),0) as total_refund from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"')g\n";
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
                        DashboardDetail item = new DashboardDetail();
                        item.setGrossProfit(cursor.getString(cursor.getColumnIndexOrThrow("gross_profit")));
                        item.setGrossSales(cursor.getString(cursor.getColumnIndexOrThrow("gross_sales")));
                        item.setNetSales(cursor.getString(cursor.getColumnIndexOrThrow("net_sales")));
                        item.setRefunds(cursor.getString(cursor.getColumnIndexOrThrow("refunds")));
                        item.setTotalRefund(cursor.getString(cursor.getColumnIndexOrThrow("total_refund")));
                        item.setTotalItem(cursor.getString(cursor.getColumnIndexOrThrow("total_item")));
                        item.setTotalSales(cursor.getString(cursor.getColumnIndexOrThrow("total_sales")));
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

    @Override
    public void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        String where = "";
        if (dashboardDetail.getMode().equals("Harian"))
            where = "where tahun = strftime('%Y','"+dashboardDetail.getTanggal()+"')";
        else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where tahun = strftime('%Y','"+dashboardDetail.getTanggal()+"')";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where tahun = '"+dashboardDetail.getTahun()+"'";


        ArrayList<DashboardDetail> list = new ArrayList<>();
        String query = "WITH DATEDATA(N) AS\n" +
                "                (\n" +
                "                        SELECT 1\n" +
                "                        UNION ALL\n" +
                "                        SELECT N+1\n" +
                "                        FROM DATEDATA\n" +
                "                        WHERE N < 12\n" +
                "                )\n" +
                "\n" +
                "\n" +
                "        SELECT substr('00'||N,-2) as bulan,IFNULL((b.GROSS_SALES),0) as gross_sales\n" +
                "        FROM DATEDATA  A left join (SELECT strftime('%m',tgl_transaksi) as bulan,strftime('%Y',tgl_transaksi) as tahun,IFNULL(SUM(TOTAL_PEMBAYARAN),0) AS GROSS_SALES FROM TRANSAKSI\n" +
                "        "+where+"           \n" +
                "                group by strftime('%m',tgl_transaksi) , strftime('%Y',tgl_transaksi)) B ON substr('00'||N,-2) = b.bulan\n";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_dashboard","success_dashboard"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        DashboardDetail item = new DashboardDetail();
                        item.setBulan(cursor.getString(cursor.getColumnIndexOrThrow("bulan")));
                        item.setGrossSales(cursor.getString(cursor.getColumnIndexOrThrow("gross_sales")));
                        list.add(item);

                        Log.d("data_dashboard",item.toString());
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_dashboard","error_dashboard",e);
        }

        connection.close();
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        String where = "";
        if (dashboardDetail.getMode().equals("Harian"))
            where = "where date(a.tgl_transaksi) = '"+dashboardDetail.getTanggal()+"'";
        else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where strftime('%m', a.tgl_transaksi) = substr('00'||"+dashboardDetail.getBulan()+",-2)";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where strftime('%Y', a.tgl_transaksi) = '"+dashboardDetail.getTahun()+"'";

        ArrayList<DashboardDetail> list = new ArrayList<>();
        String query = "        SELECT C.nama_barang,SUM(B.JUMLAH) AS total, SUM((B.JUMLAH * B.HARGA_BARANG)) AS total_harga\n" +
                "        FROM TRANSAKSI A JOIN DETAIL_TRANSAKSI B ON A.KD_TRANSAKSI = B.KD_TRANSAKSI JOIN Barang C ON B.KD_BARANG = C.KD_BARANG "+where+" group by c.nama_barang,c.kd_barang ";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_dashboard","success_dashboard"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        DashboardDetail item = new DashboardDetail();
                        item.setNamaBarang(cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")));
                        item.setTotal(cursor.getString(cursor.getColumnIndexOrThrow("total")));
                        item.setTotalHarga(cursor.getString(cursor.getColumnIndexOrThrow("total_harga")));
                        list.add(item);

                        Log.d("data_dashboard",item.toString());
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_dashboard","error_dashboard",e);
        }

        connection.close();
    }

//    @Override
//    public void loadStruk(DashboardDetail strukDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
//        ArrayList<DashboardDetail> list = new ArrayList<>();
//        String query = "select T.kd_transaksi,T.tgl_transaksi,T.total_pembayaran,T.transaksi_oleh,D.kd_barang,D.jumlah,D.harga_barang,B.nama_barang\n" +
//                "from Transaksi T join Detail_Transaksi D on T.kd_transaksi = D.kd_transaksi join Barang B on D.kd_barang = B.kd_barang " +
//                "WHERE T.KD_TRANSAKSI = '"+strukDetail.getKdTransaksi()+"'" +
//                "\n";
//        connection = new Connection(context);
//        connection.open();
//        database = connection.dbHelper().getReadableDatabase();
//        database = connection.database();
//
//        try {
//            Cursor cursor;
//            cursor = database.rawQuery(query,null);
//            Log.d("success_struk","success_struk"+cursor.getCount());
//            if (cursor.getCount() > 0) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        DashboardDetail item = new DashboardDetail();
//                        item.setKdTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("kd_transaksi")));
//                        item.setKdBarang(cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")));
//                        item.setTglTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("tgl_transaksi")));
//                        item.setJumlah(cursor.getString(cursor.getColumnIndexOrThrow("jumlah")));
//                        item.setHargaBarang(cursor.getString(cursor.getColumnIndexOrThrow("harga_barang")));
//                        item.setNamaBarang(cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")));
//                        item.setTotalPembayaran(cursor.getString(cursor.getColumnIndexOrThrow("total_pembayaran")));
//                        list.add(item);
//
//                        Log.d("data_struk",item.toString());
//
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//            callback.onLoadSuccess(list);
//        } catch (Exception e) {
//            Log.d("error_struk","error_struk",e);
//        }
//
//        connection.close();
//    }
//
//    @Override
//    public void refundBarang(DashboardDetail strukDetail, @NonNull Callback.AddCallback<DashboardDetail> callback) {
//        connection = new Connection(context);
//        connection.open();
//        database = connection.dbHelper().getReadableDatabase();
//        database = connection.database();
//
//        ContentValues values = new ContentValues();
//        values.put("kd_barang", strukDetail.getKdBarang());
//        values.put("kd_transaksi",strukDetail.getKdTransaksi());
//        values.put("jumlah",strukDetail.getJumlah());
//        values.put("harga_barang",strukDetail.getHargaBarang());
//        long returnValue = database.insert("Refund", null, values);
//
//        if (returnValue!=0)
//            callback.onAddSuccess(strukDetail);
//        else
//            callback.onAddFailed();
//    }

}


