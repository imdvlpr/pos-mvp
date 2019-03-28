package mvp.ujang.posmvp.module.Penjualan.model;

public class Produk {
    private String id;
    private String nama;
    private String harga;
    private int image;

    public Produk(String nama, String harga, int image) {
        this.nama = nama;
        this.harga = harga;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
