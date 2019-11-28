package app.android.da_android_tour_manager.model;

public class BangGiaTour {
    public String getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(String giaTour) {
        this.giaTour = giaTour;
    }

    public String getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(String ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    String giaTour, ngayApDung;

    public BangGiaTour(String giaTour, String ngayApDung) {
        this.giaTour = giaTour;
        this.ngayApDung = ngayApDung;
    }

    public BangGiaTour(){

    }
}
