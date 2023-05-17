package modelo.tanques;

public class TanqueBlanco extends TanqueSinMinas {

    private boolean visibilidad;

    public TanqueBlanco() {
        super(1, 1, 4, 4, 1);
        visibilidad = true;
    }

    public void volverseInvisible() {
        visibilidad = false;
    }

    @Override
    public void dispararBala() {

    }

    @Override
    public void recargarBala() {

    }
}
