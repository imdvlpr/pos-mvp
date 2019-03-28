package mvp.ujang.posmvp.module.Penjualan.model;

import com.google.gson.annotations.SerializedName;

public class Produk{

	@SerializedName("SATUAN")
	private String SATUAN;

	@SerializedName("GAMBAR_BARANG")
	private String GAMBARBARANG;

	@SerializedName("STOK_BARANG")
	private String STOKBARANG;

	@SerializedName("HARGA_JUAL_BARANG")
	private String HARGAJUALBARANG;

	@SerializedName("ID_KATEGORI")
	private Integer IDKATEGORI;

	@SerializedName("KD_BARANG")
	private String KDBARANG;

	@SerializedName("DESKRIPSI_BARANG")
	private String DESKRIPSIBARANG;

	@SerializedName("NAMA_BARANG")
	private String NAMABARANG;

	public Produk(String SATUAN, String GAMBARBARANG, String STOKBARANG, String HARGAJUALBARANG, Integer IDKATEGORI, String KDBARANG, String DESKRIPSIBARANG, String NAMABARANG) {
		this.SATUAN = SATUAN;
		this.GAMBARBARANG = GAMBARBARANG;
		this.STOKBARANG = STOKBARANG;
		this.HARGAJUALBARANG = HARGAJUALBARANG;
		this.IDKATEGORI = IDKATEGORI;
		this.KDBARANG = KDBARANG;
		this.DESKRIPSIBARANG = DESKRIPSIBARANG;
		this.NAMABARANG = NAMABARANG;
	}

	public String getSATUAN() {
		return SATUAN;
	}

	public void setSATUAN(String SATUAN) {
		this.SATUAN = SATUAN;
	}

	public String getGAMBARBARANG() {
		return GAMBARBARANG;
	}

	public void setGAMBARBARANG(String GAMBARBARANG) {
		this.GAMBARBARANG = GAMBARBARANG;
	}

	public String getSTOKBARANG() {
		return STOKBARANG;
	}

	public void setSTOKBARANG(String STOKBARANG) {
		this.STOKBARANG = STOKBARANG;
	}

	public String getHARGAJUALBARANG() {
		return HARGAJUALBARANG;
	}

	public void setHARGAJUALBARANG(String HARGAJUALBARANG) {
		this.HARGAJUALBARANG = HARGAJUALBARANG;
	}

	public Integer getIDKATEGORI() {
		return IDKATEGORI;
	}

	public void setIDKATEGORI(Integer IDKATEGORI) {
		this.IDKATEGORI = IDKATEGORI;
	}

	public String getKDBARANG() {
		return KDBARANG;
	}

	public void setKDBARANG(String KDBARANG) {
		this.KDBARANG = KDBARANG;
	}

	public String getDESKRIPSIBARANG() {
		return DESKRIPSIBARANG;
	}

	public void setDESKRIPSIBARANG(String DESKRIPSIBARANG) {
		this.DESKRIPSIBARANG = DESKRIPSIBARANG;
	}

	public String getNAMABARANG() {
		return NAMABARANG;
	}

	public void setNAMABARANG(String NAMABARANG) {
		this.NAMABARANG = NAMABARANG;
	}
}