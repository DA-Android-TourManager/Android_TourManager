package app.android.da_android_tour_manager.model;

public class DatTour {
    String tourKey, ngayDat, soLuongNL, soLuongTE, thanhTien, phuongTienKey, maKH;

    public DatTour(String tourKey, String ngayDat, String soLuongNL, String soLuongTE, String thanhTien, String phuongTienKey, String maKH) {
        this.tourKey = tourKey;
        this.ngayDat = ngayDat;
        this.soLuongNL = soLuongNL;
        this.soLuongTE = soLuongTE;
        this.thanhTien = thanhTien;
        this.phuongTienKey = phuongTienKey;
        this.maKH = maKH;
    }

    public DatTour() {
    }

    public String getTourKey() {
        return tourKey;
    }

    public void setTourKey(String tourKey) {
        this.tourKey = tourKey;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getSoLuongNL() {
        return soLuongNL;
    }

    public void setSoLuongNL(String soLuongNL) {
        this.soLuongNL = soLuongNL;
    }

    public String getSoLuongTE() {
        return soLuongTE;
    }

    public void setSoLuongTE(String soLuongTE) {
        this.soLuongTE = soLuongTE;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getPhuongTienKey() {
        return phuongTienKey;
    }

    public void setPhuongTienKey(String phuongTienKey) {
        this.phuongTienKey = phuongTienKey;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
}
