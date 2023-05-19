package servicio;

import dao.AltersBADA;

public class ServicioSkins {
    public static String jugadorLoggeado = "";
    public static void desbloquearSkin(int numSkin) {
        AltersBADA.alterSkin(numSkin, jugadorLoggeado);
    }
}
