package model;

import java.util.List;
import exception.InputTidakValidException;

public class Kopi extends MenuItem implements Customizable {
    private int rum;
    private int es;
    private int gula;
    private int karamel;

    public Kopi(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() != 4) {
            throw new InputTidakValidException(
                "Kustomisasi Kopi harus berisi 4 angka ya mas."
            );
        }

        customize(customizations.stream()
            .mapToInt(Integer::intValue)
            .toArray());
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations.length != 4) {
            throw new InputTidakValidException(
                "Kustomisasi Kopi harus berisi 4 angka maseh."
            );
        }

        if (customizations[0] < 0 || customizations[0] > 1 ||
            customizations[1] < 0 || customizations[1] > 5 ||
            customizations[2] < 0 || customizations[2] > 5 ||
            customizations[3] < 0 || customizations[3] > 5) {
            throw new InputTidakValidException(
                "Jumlah kustomisasi Kopi melebihi batas woe."
            );
        }

        rum = customizations[0];
        es = customizations[1];
        gula = customizations[2];
        karamel = customizations[3];
    }

    @Override
    public double hitungHargaTotal() {
        return getHargaBase() + (rum * 3000) + (karamel * 3000);
    }

    @Override
    public String getOrderDetail() {
        return "Kopi " + getNama() + " / Rum: " + rum + " / Es: " + es + " / Gula: " + gula + " / Karamel: " + karamel;
            
    }
}