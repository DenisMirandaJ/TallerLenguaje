package com.tallerLenguaje1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GananciaDiaria {

    HashMap<String, Integer> gananciaPesos;
    HashMap<String, Integer> gananciaDolares;
    private int precioDolar;
    private int precioPeso;

    private String dia;

    GananciaDiaria(String day) {
        this.dia = day;
        gananciaDolares = new HashMap<>();
        gananciaPesos = new HashMap<>();
        precioDolar = 0;
        precioPeso = 0;
        gananciaPesos.put("bitcoin", 0);
        gananciaPesos.put("litecoin", 0);
        gananciaPesos.put("ripple", 0);
        gananciaPesos.put("peercoin", 0);
        gananciaPesos.put("dogecoin", 0);
        gananciaPesos.put("pesos", 0);
        gananciaDolares.put("bitcoin", 0);
        gananciaDolares.put("litecoin", 0);
        gananciaDolares.put("ripple", 0);
        gananciaDolares.put("peercoin", 0);
        gananciaDolares.put("dogecoin", 0);
        gananciaDolares.put("dolar", 0);
    }

    public void setPrecioDolar(int precioDolar) {
        this.precioDolar = precioDolar;
    }

    public void setPrecioPeso(int precioPeso) {
        this.precioPeso = precioPeso;
    }

    public void agregarGanancia(int cantidad, String monedaAlt, String monedaDestino) {
        if (monedaDestino.equals("dolar")) {
            gananciaDolares.put(monedaAlt,gananciaDolares.get(monedaAlt) + cantidad);
        } else if (monedaDestino.equals("pesos")) {
            gananciaPesos.put(monedaAlt,gananciaPesos.get(monedaAlt) + cantidad);
        }
    }

    public void agregarGanancia(int cantidad, String tipoDineroReal) {
        if (tipoDineroReal.equals("dolar")) {
            gananciaDolares.put("dolar", gananciaDolares.get("dolar") + cantidad);
        } else if (tipoDineroReal.equals("pesos")) {
            gananciaPesos.put("pesos", gananciaPesos.get("pesos") + cantidad);
        }
    }

    public int getDolar() {
        return gananciaDolares.get("dolares");
    }

    public int getPesos() {
        return gananciaPesos.get("pesos");
    }

    public int getMoneda(String tipomoneda, String monedaDestino) {
        if (monedaDestino.equals("dolar")) {
            return gananciaDolares.get(tipomoneda);
        } else {
            return gananciaPesos.get(tipomoneda);
        }
    }

    public int getPrecioDolar() {
        return precioDolar;
    }

    public int getPrecioPeso() {
        return precioPeso;
    }

    public String getDia() {
        return dia;
    }

    public static String obtenerTipoDeMoneda(String line){
        Pattern regexMonedaAlternativa = Pattern.compile("alt(\\[|\\{)([a-z]+)");
        Matcher m = regexMonedaAlternativa.matcher(line);
        if (m.find()) {
            return m.group(2);
        }
        Pattern regexMoneda = Pattern.compile("\\((dolar|pesos)");
        m = regexMoneda.matcher(line);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static int obtenerCantidad(String line){
        Pattern regexCantidad = Pattern.compile("[\\[\\(\\{]([0-9]+)[\\]\\)\\}]");
        Matcher m = regexCantidad.matcher(line);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    public static String obtenerDia(String line) {
        Pattern regexDia = Pattern.compile("Dia([0-9]+)");
        Matcher m = regexDia.matcher(line);
        if (m.find()) {
            return m.group(0);
        }
        return "";
    }

    public static String obtenerMonedaDestino(String line) {
        Pattern regexDia = Pattern.compile("([\\[\\{])(Dia([0-9]+))");
        Matcher m = regexDia.matcher(line);
        if (m.find()) {
            if (m.group(1).equals("{")) {
                return "dolar";
            } else {
                return "pesos";
            }
        }
        return "";
    }
}
