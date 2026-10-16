package model;

public abstract class MenuItem {
    private String id;
    private String nama;
    private double hargaBase;
    private int stok;

    public MenuItem(String id, String nama, double hargaBase, int stok) {
        this.id = id;
        this.nama = nama;
        this.hargaBase = hargaBase;
        this.stok = stok;
    }

    public abstract double hitungHargaTotal();

    public abstract String getOrderDetail();

    public double hitungHargaTotal(int jumlah) {
        return hitungHargaTotal() * jumlah;
    }

    public double hitungHargaTotal(int jumlah, String kodePromo) {
        double total = hitungHargaTotal(jumlah);

        if (kodePromo == null) {
            return total;
        }

        if (kodePromo.equalsIgnoreCase("Icikiwir2026")) {
            total = total * 0.90;
        } else if (kodePromo.equalsIgnoreCase("MasAmbaHeli")) {
            total = total * 0.85;
        }

        return total;
    }

    public double hitungHargaTotal(int jumlah, EnumUkuran ukuran) {
        return (hitungHargaTotal() + ukuran.getTambahanHarga()) * jumlah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHargaBase() {
        return hargaBase;
    }

    public void setHargaBase(double hargaBase) {
        this.hargaBase = hargaBase;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void kurangiStok(int jumlah) {
        stok -= jumlah;
    }


}