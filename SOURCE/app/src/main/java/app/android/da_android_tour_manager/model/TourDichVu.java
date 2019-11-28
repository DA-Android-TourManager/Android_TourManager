package app.android.da_android_tour_manager.model;

public class TourDichVu {
    String ctCungCapDVKey;
    String name;
    String tourKey;

    public String getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(String giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    String giaDichVu;

    public String getCtCungCapDVKey() {
        return ctCungCapDVKey;
    }

    public void setCtCungCapDVKey(String ctCungCapDVKey) {
        this.ctCungCapDVKey = ctCungCapDVKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTourKey() {
        return tourKey;
    }

    public void setTourKey(String tourKey) {
        this.tourKey = tourKey;
    }

    public TourDichVu(String ctCungCapDVKey, String name, String tourKey, String giaDichVu) {
        this.ctCungCapDVKey = ctCungCapDVKey;
        this.name = name;
        this.tourKey = tourKey;
        this.giaDichVu = giaDichVu;
    }

    public TourDichVu() {

    }
}
