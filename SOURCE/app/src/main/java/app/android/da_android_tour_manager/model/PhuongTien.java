package app.android.da_android_tour_manager.model;

public class PhuongTien {
    public String loaiPhuongTienKey, hangKey, name;
    public int sucChua, gia;

    public PhuongTien() {
    }

    public PhuongTien(String loaiPhuongTienKey, String hangKey, String name, int sucChua, int gia) {
        this.loaiPhuongTienKey = loaiPhuongTienKey;
        this.hangKey = hangKey;
        this.name = name;
        this.sucChua = sucChua;
        this.gia = gia;
    }

    public String getLoaiPhuongTienKey() {
        return loaiPhuongTienKey;
    }

    public void setLoaiPhuongTienKey(String loaiPhuongTienKey) {
        this.loaiPhuongTienKey = loaiPhuongTienKey;
    }

    public String getHangKey() {
        return hangKey;
    }

    public void setHangKey(String hangKey) {
        this.hangKey = hangKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
