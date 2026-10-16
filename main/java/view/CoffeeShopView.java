package view;

import java.util.Scanner;
import java.util.List;
import model.MenuItem;
import model.Kopi;
import model.Teh;
import model.Roti;
import model.EnumUkuran;

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

