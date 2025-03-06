package models;

public class SinhVien {
	private String tenSV;
	private String maSV;
	private String diaChi;
	
	public SinhVien(String  tenSV, String maSV, String diaChi) {
		// TODO Auto-generated constructor stub
		this.tenSV = tenSV;
		this.maSV = maSV;
		this.diaChi = diaChi;
	
		
	}
	
	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}
	public String getTenSV() {
		return tenSV;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaSV() {
		return maSV;
	}
	
	

}
