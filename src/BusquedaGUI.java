import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BusquedaGUI extends JFrame {

    private JTextField campoInicio;  // Campo para nodo inicial
    private JTextField campoDestino; // Campo para nodo destino
    private JTextArea areaResultado; // Área para mostrar resultados
    private BusquedaBestFirst busqueda;
    private Nodo raiz;

    public BusquedaGUI(Nodo raiz) {
        this.raiz = raiz;
        this.busqueda = new BusquedaBestFirst();

        // Configuración de la ventana principal
        setTitle("Búsqueda Primero el Mejor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear y añadir paneles a la ventana
        JPanel panelEntrada = crearPanelEntrada();
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
    }

    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        // Etiquetas y campos de texto
        panel.add(new JLabel("Nodo de Inicio:"));
        campoInicio = new JTextField();
        panel.add(campoInicio);

        panel.add(new JLabel("Nodo de Destino:"));
        campoDestino = new JTextField();
        panel.add(campoDestino);

        // Botón para buscar
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(e -> realizarBusqueda());
        panel.add(new JLabel());  // Espacio vacío
        panel.add(botonBuscar);

        return panel;
    }

    private void realizarBusqueda() {
        String inicio = campoInicio.getText().trim();
        String destino = campoDestino.getText().trim();

        // Validación de entrada
        if (inicio.isEmpty() || destino.isEmpty()) {
            areaResultado.setText("Por favor, ingrese los nodos de inicio y destino.");
            return;
        }

        // Buscar nodo inicial en el árbol
        Nodo nodoInicio = buscarNodo(raiz, inicio);
        if (nodoInicio == null) {
            areaResultado.setText("El nodo de inicio no existe en el árbol.");
            return;
        }

        // Realizar búsqueda
        List<Nodo> camino = busqueda.buscar(nodoInicio, destino);
        if (camino != null) {
            StringBuilder resultado = new StringBuilder("Camino encontrado:\n");
            for (Nodo nodo : camino) {
                resultado.append(nodo.getValor()).append(" -> ");
            }
            resultado.setLength(resultado.length() - 4);
            areaResultado.setText(resultado.toString());
        } else {
            areaResultado.setText("No se encontró un camino hacia el nodo destino.");
        }
    }

    private Nodo buscarNodo(Nodo nodo, String valor) {
        if (nodo.getValor().equals(valor)) {
            return nodo;
        }
        for (Nodo hijo : nodo.getHijos()) {
            Nodo encontrado = buscarNodo(hijo, valor);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Crear nodos
        Nodo raiz = new Nodo("A", 3);
        Nodo b = new Nodo("B", 1);
        Nodo c = new Nodo("C", 4);
        Nodo d = new Nodo("D", 2);
        Nodo e = new Nodo("E", 5);
        Nodo f = new Nodo("F", 2);
        Nodo g = new Nodo("G", 6);
        Nodo h = new Nodo("H", 3);
        Nodo i = new Nodo("I", 4);
        Nodo j = new Nodo("J", 1);
        Nodo k = new Nodo("K", 2);

        // Construir el árbol
        raiz.agregarHijo(b);
        raiz.agregarHijo(c);

        b.agregarHijo(d);
        b.agregarHijo(e);

        c.agregarHijo(f);
        c.agregarHijo(g);

        f.agregarHijo(h);
        f.agregarHijo(i);

        g.agregarHijo(j);

        j.agregarHijo(k);
        i.agregarHijo(k);

        // Crear e iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            BusquedaGUI ventana = new BusquedaGUI(raiz);
            ventana.setVisible(true);
        });
    }

}
