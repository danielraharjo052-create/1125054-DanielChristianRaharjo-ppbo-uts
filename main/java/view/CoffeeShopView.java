package view;

import java.util.List;
import java.util.Scanner;
import model.EnumUkuran;
import model.Kopi;
import model.MenuItem;
import model.Roti;
import model.Teh;

public class CoffeeShopView {
    private Scanner input = new Scanner(System.in);

    public int inputAngka(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input nya angka dong mas.");
            }
        }
    }

    public String inputTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    public void tampilkanMenu(List<MenuItem> daftarMenu) {
        System.out.println("\nCOFFEE SHOP MAS AMBA ");

        for (MenuItem menu : daftarMenu) {
            System.out.println(
                menu.getId() + " / " + menu.getNama() + " / Harga: Rp" + menu.getHargaBase() + " / Stok: " + menu.getStok()
            );
        }

        System.out.println("------------------------");
    }

    public void tampilkanPilihanMenu() {
        System.out.println("\n1. Pesan Menu Mas Amba");
        System.out.println("2. Lihat Menu Mas AMba");
        System.out.println("0. Keluar");
    }

    public void inputKustomisasi(MenuItem menu) {
        System.out.println("\nCustom");

        if (menu instanceof Kopi) {
            System.out.println("Kopi: Rum (0-1), Es (0-5), Gula (0-5), Karamel (0-5)");
            int rum = inputAngka("Jumlah Rum: ");
            int es = inputAngka("Jumlah Es: ");
            int gula = inputAngka("Jumlah Gula: ");
            int karamel = inputAngka("Jumlah Karamel: ");

            ((Kopi) menu).customize(
                new int[]{rum, es, gula, karamel}
            );

        } else if (menu instanceof Teh) {
            System.out.println("Teh: Boba (0-3), Es (0-5), Gula (0-5), Karamel (0-5)");
            int boba = inputAngka("Jumlah Boba: ");
            int es = inputAngka("Jumlah Es: ");
            int gula = inputAngka("Jumlah Gula: ");
            int karamel = inputAngka("Jumlah Karamel: ");

            ((Teh) menu).customize(
                new int[]{boba, es, gula, karamel}
            );

        } else if (menu instanceof Roti) {
            System.out.println("Roti: Gula (0-5), Meses (0-5), Karamel (0-5)");
            int gula = inputAngka("Jumlah Gula: ");
            int meses = inputAngka("Jumlah Meses: ");
            int karamel = inputAngka("Jumlah Karamel: ");

            ((Roti) menu).customize(
                new int[]{gula, meses, karamel}
            );
        }
    }

    public EnumUkuran inputUkuran() {
        while (true) {
            System.out.println("\nUkuran Minuman:");
            System.out.println("1. SMALL +Rp0");
            System.out.println("2. MEDIUM +Rp3000");
            System.out.println("3. LARGE +Rp5000");

            int pilihan = inputAngka("Pilih ukuran: ");

            switch (pilihan) {
                case 1:
                    return EnumUkuran.SMALL;
                case 2:
                    return EnumUkuran.MEDIUM;
                case 3:
                    return EnumUkuran.LARGE;
                default:
                    System.out.println("Pilih aturan sendiri dah:");
            }
        }
    }

    public void cetakStruk(
        MenuItem menu,
        int jumlah,
        double total,
        String kodePromo
    ) {
        System.out.println("\nSTRUK PEMESANAN MAS AMBA");
        System.out.println("ID Menu : " + menu.getId());
        System.out.println("Nama Menu : " + menu.getNama());
        System.out.println("Jumlah : " + jumlah);
        System.out.println("Detail : " + menu.getOrderDetail());
        System.out.println("Promo : " +
            (kodePromo.isEmpty() ? "-" : kodePromo));
        System.out.println("Total : Rp" + total);
        System.out.println("Sisa Stok : " + menu.getStok());
        System.out.println("-------------------------------");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}