import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nodo {
    private String valor;
    private int heuristica;
    private List<Nodo> hijos;

    public Nodo(String valor, int heuristica) {
        this.valor = valor;
        this.heuristica = heuristica;
        this.hijos = new ArrayList<>();
    }

    public String getValor() {
        return valor;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(valor, nodo.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
