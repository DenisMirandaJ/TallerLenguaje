package com.tallerLenguaje1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
    private ArrayList<GananciaDiaria> ganancias;

    Controlador() {
        ganancias = new ArrayList<>();
    }

    public ArrayList<String> leerArchivo(String file) throws IOException {
        ArrayList<String> s = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = br.readLine();

            while (line != null) {
                s.add(line);
                line = br.readLine();
            }
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            return s;
        }
    }

    public GananciaDiaria getGanancia(String dia) {
        int index = buscarGananciaDiaria(dia);
        if (index != -1)
            return ganancias.get(buscarGananciaDiaria(dia));
        return null;
    }

    public int buscarGananciaDiaria(String dia) {
        for (int i=0; i<ganancias.size(); i++) {
            if (ganancias.get(i).getDia().equals(dia))
                return i;
        }
        return -1;
    }

    public void leerRegistros() throws IOException {
        ArrayList<String> lines = leerArchivo("Registros.txt");
        for (String line: lines) {
            String tipoMoneda = GananciaDiaria.obtenerTipoDeMoneda(line);
            int cantidad = GananciaDiaria.obtenerCantidad(line);
            String dia = GananciaDiaria.obtenerDia(line);
            String monedaDestino = GananciaDiaria.obtenerMonedaDestino(line);

            int index = buscarGananciaDiaria(dia);
            GananciaDiaria g;
            if (index == -1) {
                g = new GananciaDiaria(dia);
            } else {
                g = ganancias.get(index);
            }

            switch (tipoMoneda) {
                case "dolar":
                    g.agregarGanancia(cantidad, tipoMoneda);
                    ganancias.add(g);
                    break;
                case "pesos":
                    g.agregarGanancia(cantidad, tipoMoneda);
                    ganancias.add(g);
                    break;
                default:
                    g.agregarGanancia(cantidad, tipoMoneda, monedaDestino);
                    ganancias.add(g);
            }
        }

    }
}
