package com.tallerLenguaje1;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.print("Registros.txt : ");
        ParserMonedas.main(args);
        System.out.print("Conversiones.txt : ");
        ParserConversiones.main(args);
        System.out.print("Variaciones.txt : ");
        ParserVariaciones.main(args);

        Controlador controlador = new Controlador();
        controlador.leerRegistros();
        GananciaDiaria g = controlador.getGanancia("Dia2");
        System.out.println(g.getMoneda("peercoin", "pesos"));
    }
}
