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

