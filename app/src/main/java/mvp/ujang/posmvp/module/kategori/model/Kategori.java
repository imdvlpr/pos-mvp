package mvp.ujang.posmvp.module.kategori.model;

public class Kategori {
    private String nama;
    private int gambar;
    private String  jumlahBarang;

    public Kategori(String nama, String jumlahBarang, int gambar) {
        this.nama = nama;
        this.jumlahBarang = jumlahBarang;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(String jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }
}
