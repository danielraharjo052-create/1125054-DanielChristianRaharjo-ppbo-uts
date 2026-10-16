package controller;

import java.util.ArrayList;
import java.util.List;

import model.MenuItem;
import model.Kopi;
import model.Teh;
import model.Roti;
import model.EnumUkuran;
import exception.StokTidakCukupException;
import exception.MenuTidakDitemukanException;
import exception.InputTidakValidException;
import view.CoffeeShopView;

public class CoffeeShopController {
    private ArrayList<MenuItem> daftarMenu;
    private CoffeeShopView view;

    public CoffeeShopController() {
        daftarMenu = new ArrayList<>();
        view = new CoffeeShopView();

        daftarMenu.add(
            new Kopi("M-01", "Kopi Susu Mas Halland", 15000, 5)
        );

        daftarMenu.add(
            new Teh("M-02", "Teh Tarik Ahay", 12000, 10)
        );

        daftarMenu.add(
            new Roti("M-03", "Roti Cokelattttt!", 10000, 8)
        );
    }

    public void mulai() {
        boolean berjalan = true;

        while (berjalan) {
            view.tampilkanPilihanMenu();

            int pilihan = view.inputAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    pesanMenu();
                    break;

                case 2:
                    view.tampilkanMenu(daftarMenu);
                    break;

                case 0:
                    berjalan = false;
                    view.tampilkanPesan("Hatur suwun");
                    break;

                default:
                    view.tampilkanPesan("Opsinya ngga valid toh e mas");
            }
        }
    }

    private MenuItem cariMenu(String id) {
        for (MenuItem menu : daftarMenu) {
            if (menu.getId().equalsIgnoreCase(id)) {
                return menu;
            }
        }

        throw new MenuTidakDitemukanException(
            "Menu ber-ID " + id + " ndak ketemu mas"
        );
    }

    private void pesanMenu() {
        MenuItem menu;

        while (true) {
            view.tampilkanMenu(daftarMenu);
            String id = view.inputTeks("Masukkan ID menunya: ");

            try {
                menu = cariMenu(id);
                break;
            } catch (MenuTidakDitemukanException e) {
                view.tampilkanPesan(e.getMessage());
                view.tampilkanPesan("Masukin Idnya lagi maseh");
            }
        }

        int jumlah;

        while (true) {
            jumlah = view.inputAngka("Jumlah pesanan: ");

            try {
                if (jumlah <= 0) {
                    throw new InputTidakValidException(
                        "Jumlah pesanan harus lebih dari 0 woe"
                    );
                }

                if (jumlah > menu.getStok()) {
                    throw new StokTidakCukupException(
                        "Stok ga cukup ya mas, Stok tersedia: " + menu.getStok()
                    );
                }

                break;
            } catch (InputTidakValidException |
                     StokTidakCukupException e) {
                view.tampilkanPesan(e.getMessage());
            }
        }

        try {
            view.inputKustomisasi(menu);

            double total;
            String kodePromo = view.inputTeks(
                "Masukkan kode promo (Enter aja mas kalau ndak ada): "
            );

            if (menu instanceof Kopi || menu instanceof Teh) {
                EnumUkuran ukuran = view.inputUkuran();

                total = menu.hitungHargaTotal(jumlah, ukuran);

                if (kodePromo.equalsIgnoreCase("Icikiwir2026")) {
                    total = total * 0.90;
                } else if (kodePromo.equalsIgnoreCase("MasAmbaHeli")) {
                    total = total * 0.85;
                } else if (!kodePromo.isEmpty()) {
                    view.tampilkanPesan(
                        "Kode promo ndak valid. Harga normal ya maseh."
                    );
                    kodePromo = "";
                }

            } else {
                total = menu.hitungHargaTotal(jumlah, kodePromo);

                if (!kodePromo.isEmpty()
                    && !kodePromo.equalsIgnoreCase("Icikiwir2026")
                    && !kodePromo.equalsIgnoreCase("MasAmbaHeli")) {
                    view.tampilkanPesan(
                        "Kode promo ndak valid mas. Harga normal ya maseh."
                    );
                    kodePromo = "";
                    total = menu.hitungHargaTotal(jumlah);
                }
            }

            menu.kurangiStok(jumlah);

            view.cetakStruk(menu, jumlah, total, kodePromo);

        } catch (InputTidakValidException e) {
            view.tampilkanPesan("Kustomisasi gagal: " + e.getMessage());
            view.tampilkanPesan(
                "Pesanan cancel ya mas. Stok ndak berubah."
            );
        }
    }
}