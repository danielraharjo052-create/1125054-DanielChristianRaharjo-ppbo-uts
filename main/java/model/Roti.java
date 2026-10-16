package model;

import java.util.List;
import exception.InputTidakValidException;

public class Roti extends MenuItem implements Customizable {
    private int gula;
    private int meses;
    private int karamel;

    public Roti(String id, String nama, double hargaBase, int stok) {
        super(id, nama, hargaBase, stok);
    }

    @Override
    public void customize(List<Integer> customizations) {
        if (customizations.size() != 3) {
            throw new InputTidakValidException(
                "Kustomisasi Roti harus berisi 3 angka ya mas."
            );
        }

        customize(customizations.stream()
            .mapToInt(Integer::intValue)
            .toArray());
    }

    @Override
    public void customize(int[] customizations) {
        if (customizations.length != 3) {
            throw new InputTidakValidException(
                "Kustomisasi Roti harus berisi 3 angka maseh."
            );
        }

        if (customizations[0] < 0 || customizations[0] > 5 ||
            customizations[1] < 0 || customizations[1] > 5 ||
            customizations[2] < 0 || customizations[2] > 5) {
            throw new InputTidakValidException(
                "Jumlah kustomisasi Roti melebihi batas woe."
            );
        }

        gula = customizations[0];
        meses = customizations[1];
        karamel = customizations[2];
    }

    @Override
    public double hitungHargaTotal() {
        return getHargaBase() + (meses * 500) + (karamel * 3000);
    }

    @Override
    public String getOrderDetail() {
        return "Roti " + getNama() + " / Gula: " + gula + " / Meses: " + meses + " / Karamel: " + karamel;
    }
}