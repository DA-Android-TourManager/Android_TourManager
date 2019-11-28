package app.android.da_android_tour_manager.model;

public class ChiTietTour {
    public String maTour, hinh1, hinh2, moTa1, moTa2, moTa3;

    public ChiTietTour() {
    }

    public ChiTietTour(String maTour, String hinh1, String hinh2, String moTa1, String moTa2, String moTa3) {
        this.maTour = maTour;
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
        this.moTa1 = moTa1;
        this.moTa2 = moTa2;
        this.moTa3 = moTa3;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public String getMoTa1() {
        return moTa1;
    }

    public void setMoTa1(String moTa1) {
        this.moTa1 = moTa1;
    }

    public String getMoTa2() {
        return moTa2;
    }

    public void setMoTa2(String moTa2) {
        this.moTa2 = moTa2;
    }

    public String getMoTa3() {
        return moTa3;
    }

    public void setMoTa3(String moTa3) {
        this.moTa3 = moTa3;
    }
}
