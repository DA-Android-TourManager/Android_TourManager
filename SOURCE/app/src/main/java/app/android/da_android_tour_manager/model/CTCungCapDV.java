package app.android.da_android_tour_manager.model;

public class CTCungCapDV {
    String doiTacKey, donGia, loaiDichVuKey;

    public CTCungCapDV(String doiTacKey, String donGia, String loaiDichVuKey) {
        this.doiTacKey = doiTacKey;
        this.donGia = donGia;
        this.loaiDichVuKey = loaiDichVuKey;
    }

    public CTCungCapDV() {
    }

    public String getDoiTacKey() {
        return doiTacKey;
    }

    public void setDoiTacKey(String doiTacKey) {
        this.doiTacKey = doiTacKey;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getLoaiDichVuKey() {
        return loaiDichVuKey;
    }

    public void setLoaiDichVuKey(String loaiDichVuKey) {
        this.loaiDichVuKey = loaiDichVuKey;
    }
}
