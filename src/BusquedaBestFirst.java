import java.util.*;

public class BusquedaBestFirst {

    public List<Nodo> buscar(Nodo raiz, String objetivo) {
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(Nodo::getHeuristica)); // Prioriza nodos por heurística
        Map<Nodo, Nodo> camino = new HashMap<>(); // Guarda el nodo padre de cada nodo visitado

        cola.add(raiz); // Inicia la búsqueda desde la raíz
        camino.put(raiz, null);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll(); // Toma el nodo con menor heurística

            if (actual.getValor().equals(objetivo)) {
                return reconstruirCamino(camino, actual); // Devuelve el camino si se encuentra el objetivo
            }

            for (Nodo hijo : actual.getHijos()) {
                if (!camino.containsKey(hijo)) {
                    cola.add(hijo);
                    camino.put(hijo, actual);
                }
            }
        }
        return null; // No se encontró el objetivo
    }

    // Reconstruye el camino desde el objetivo hasta la raíz
    private List<Nodo> reconstruirCamino(Map<Nodo, Nodo> camino, Nodo destino) {
        List<Nodo> ruta = new ArrayList<>();
        Nodo actual = destino;

        while (actual != null) {
            ruta.add(0, actual); // Inserta en orden inverso
            actual = camino.get(actual);
        }
        return ruta;
    }
}
