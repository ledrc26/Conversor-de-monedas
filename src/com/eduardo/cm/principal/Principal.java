/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.eduardo.cm.principal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 *
 * @author ledrc
 */
public class Principal
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/183aea20a954729e3eb9bdc9/latest/USD")).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = (String) response.body();

        JsonObject jOb = JsonParser.parseString(json).getAsJsonObject().getAsJsonObject("conversion_rates");
        int opc = 0;
        Scanner scanner = new Scanner(System.in);
        do
        {
            menu();
            opc = scanner.nextInt();
            if (opc != 9)
            {
                System.out.println("Ingresa la cantidad a convertir: ");
                int cantidad = scanner.nextInt();
                switch (opc)
                {
                    case 1:
                        System.out.println(cantidad + " = " + convertir(jOb.get("ARS").getAsDouble(), cantidad, true));
                        break;
                    case 2:
                        System.out.println(cantidad + " = " + convertir(jOb.get("ARS").getAsDouble(), cantidad, false));
                        break;
                    case 3:
                        System.out.println(cantidad + " = " + convertir(jOb.get("BRL").getAsDouble(), cantidad, true));
                        break;
                    case 4:
                        System.out.println(cantidad + " = " + convertir(jOb.get("BRL").getAsDouble(), cantidad, false));
                        break;
                    case 5:
                        System.out.println(cantidad + " = " + convertir(jOb.get("COP ").getAsDouble(), cantidad, true));
                        break;
                    case 6:
                        System.out.println(cantidad + " = " + convertir(jOb.get("COP").getAsDouble(), cantidad, false));
                        break;
                    case 7:
                        System.out.println(cantidad + " = " + convertir(jOb.get("CLP").getAsDouble(), cantidad, true));
                        break;
                    case 8:
                        System.out.println(cantidad + " = " + convertir(jOb.get("CLP").getAsDouble(), cantidad, false));
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            }
        } while (opc != 9);

    }

    public static void menu()
    {
        System.out.println("""
                           **********************************************************
                           Sea bienbenido/a al conversor de Moneda =)
                           
                           1) Dólar =>> Peso argentino
                           2) Peso argentino =>> Dólar
                           3) Dólar =>> Real brasileño
                           4) Real braseleño =>> Dólar
                           5) Dólar =>> Peso Colombiano
                           6) Peso colombiano =>> Dólar
                           7) Dólar =>> Peso Chileno
                           8) Peso Chileno =>> Dólar
                           9) Salir
                           Elije una opcion:
                           **********************************************************
                           """);
    }

    public static double convertir(double moneda_cambio, double cantidad, boolean bandera)
    {
        if (bandera)
        {
            return moneda_cambio * cantidad;
        } else
        {
            return cantidad / moneda_cambio;
        }
    }

}
