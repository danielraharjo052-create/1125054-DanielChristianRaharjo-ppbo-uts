package model;

public enum EnumUkuran {
    SMALL(0),
    MEDIUM(3000),
    LARGE(5000);

    private final double tambahanHarga;

    EnumUkuran(double tambahanHarga) {
        this.tambahanHarga = tambahanHarga;
    }

    public double getTambahanHarga() {
        return tambahanHarga;
    }
}