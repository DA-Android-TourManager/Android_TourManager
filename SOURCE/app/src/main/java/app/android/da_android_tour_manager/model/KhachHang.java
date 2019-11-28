package app.android.da_android_tour_manager.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class KhachHang {
    public String name;
    public String email;
    public String loaiKH;
    public String diaChi;
    public String sdt;
    String avartar;

    public KhachHang() {

    }

    public KhachHang(String name, String email, String loaiKH, String diaChi, String sdt, String avartar) {
        this.name = name;
        this.email = email;
        this.loaiKH = loaiKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.avartar = avartar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }
}
