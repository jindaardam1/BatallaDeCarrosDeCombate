package servicio;

import dao.AltersBADA;
import dao.ConsultasBADA;
import dao.records.Skin;
import main.BatallaDeCarrosDeCombate;

import java.util.ArrayList;

public class ServicioSkins {
    public static void desbloquearSkin(int numSkin) {
        AltersBADA.alterSkin(numSkin, BatallaDeCarrosDeCombate.nickname.NICKNAME());
    }

    public static ArrayList<Skin> getSkins() {
        return ConsultasBADA.obtenerSkins();
    }
}
