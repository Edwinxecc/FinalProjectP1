package src;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
public class Facturacion {

    private String cedula;
    private String nombre;
    private int numAsientos;

    public Facturacion(String cedula, String nombre, int numAsientos){
        this.cedula = cedula;
        this.nombre = nombre;
        this.numAsientos = numAsientos;
    }

    public String GenerarMail(){
        String email = nombre.charAt(0) + "";
        String dominio = "@cliente.mail", temp;
        if (nombre.length() > 10) {
            for (int i = 1; i < nombre.length(); i++) {
                temp = nombre.charAt(i) + "";
                if (temp.equalsIgnoreCase(" ")) {
                    email += nombre.charAt(i+1);
                }
            }
            return email + dominio;
        }
        return "NOmail@cliente.mail";
    }

    public String GenerarNumero (){
        String number = "09", numerosParaGenerar = "0987654321";
        int n;
        Random num = new Random();
        for (int i = 0; i < 8; i++) {
            n = num.nextInt(9);
            number += n + "";
        }
        return number;
    }

    public void GenerarFacturaEnArchivo(){
        LocalDateTime tiempo = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String Fecha = tiempo.format(formato);
        
        try(PrintWriter writer = new PrintWriter(new FileWriter(cedula+".txt"))) {
            writer.println("-------------------------");
            writer.println("|         Factura       ");
            writer.println("|      Code name JAJ    ");
            writer.println("-------------------------");
            writer.println(" ");
            writer.println("| Fecha: " + Fecha);
            writer.println("| Nombre: " + nombre);
            writer.println("| Cedula: " + cedula);
            writer.println("| Celular: " + GenerarNumero());
            writer.println("| Correo: " + GenerarMail());
            writer.println("-------------------------");
            writer.println(" ");
            writer.println("| Asientos comprados: " + numAsientos + "    V.U=2.5$");
            writer.println("| Valor a pagar: " + (numAsientos * 2.50) + "$");
            writer.println("-------------------------");
            writer.println("|       Grupo Code      ");
            writer.println("**************************");
            writer.println(" Factura guardada como: " + cedula + ".txt");
            writer.println(" ");
        } catch (IOException e) {
            System.out.println("Error al generar la factura: " + e.getMessage());
        }
    }

}