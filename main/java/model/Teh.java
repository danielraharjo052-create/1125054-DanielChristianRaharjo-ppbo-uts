package model;

import java.util.List;
import exception.InputTidakValidException;

public class Teh extends MenuItem implements Customizable {
    private int boba;
    private int es;
    private int gula;
    private int karamel;

    public Teh(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() != 4) {
            throw new InputTidakValidException(
                "Kustomisasi Teh harus berisi 4 angka ya mas."
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
                "Kustomisasi Teh harus berisi 4 angk ya mas."
            );
        }

        if (customizations[0] < 0 || customizations[0] > 3 ||
            customizations[1] < 0 || customizations[1] > 5 ||
            customizations[2] < 0 || customizations[2] > 5 ||
            customizations[3] < 0 || customizations[3] > 5) {
            throw new InputTidakValidException(
                "Jumlah kustomisasi Teh melebihi batas woe."
            );
        }

        boba = customizations[0];
        es = customizations[1];
        gula = customizations[2];
        karamel = customizations[3];
    }

    @Override
    public double hitungHargaTotal() {
        return getHargaBase() + (boba * 1000) + (karamel * 3000);
    }

    @Override
    public String getOrderDetail() {
        return "Teh " + getNama() + " / Boba: " + boba + " / Es: " + es + " / Gula: " + gula + " / Karamel: " + karamel;
    }
}