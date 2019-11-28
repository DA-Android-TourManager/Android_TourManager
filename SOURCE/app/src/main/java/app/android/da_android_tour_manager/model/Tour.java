package app.android.da_android_tour_manager.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Tour {
    String loaiTourKey, name, phuongTienKey, diemDi, diemDen, thoiGian, moTa;
    String hinhAnh;
    String bangGiatourKey;
    String ngayDi;
    String ngayVe;

    public Tour(String loaiTourKey, String name, String phuongTienKey, String diemDi, String diemDen, String thoiGian, String moTa, String hinhAnh, String bangGiatourKey, String ngayDi, String ngayVe) {
        this.loaiTourKey = loaiTourKey;
        this.name = name;
        this.phuongTienKey = phuongTienKey;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGian = thoiGian;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.bangGiatourKey = bangGiatourKey;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
    }

    public Tour() {
    }

    public String getLoaiTourKey() {
        return loaiTourKey;
    }

    public void setLoaiTourKey(String loaiTourKey) {
        this.loaiTourKey = loaiTourKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhuongTienKey() {
        return phuongTienKey;
    }

    public void setPhuongTienKey(String phuongTienKey) {
        this.phuongTienKey = phuongTienKey;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getBangGiatourKey() {
        return bangGiatourKey;
    }

    public void setBangGiatourKey(String bangGiatourKey) {
        this.bangGiatourKey = bangGiatourKey;
    }

    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
    }
}
