package paqxpress.test;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import paqxpress.core.*;

public class ConsoleApp {
    private static Scanner cin = new Scanner(System.in);
    private static PqManager pqXpress = null;

    public static void main(String[] args) throws Exception {

        String entrada = "";
        final String altaCliente = "1";
        final String listaClientes = "2";
        final String altaNuevoEnvio = "3";
        final String consultarEnvio = "4";
        final String cambiarEnvio = "5";
        final String listarEnvioCliente = "6";
        final String listarEnvioReparto = "7";
        final String salir = "x";

        launchApp();

        while (!entrada.equals(salir)) {
            printMenu();
            entrada = cin.nextLine().toLowerCase();

            switch (entrada) {
                case altaCliente:
                    altaCliente();
                    break;
                
                case listaClientes:
                    listaClientes();
                    break;

                case altaNuevoEnvio:
                    altaNuevoEnvio();
                    break;

                case consultarEnvio:
                    consultarEnvio();
                    break;
                    
                case cambiarEnvio:
                    cambiarEnvio();
                    break;

                case listarEnvioCliente:
                    listarEnvioCliente();
                    break;

                case listarEnvioReparto:
                    listarEnvioReparto();
                    break;

                default:
                    break;
            }
        }
    }

    public static void printMenu(){
        clear();
        String menu = "\n\t#######################################\n";
              menu += "\t#                                     #\n";
              menu += "\t#           p q X p r e s s           #\n";
              menu += "\t#                                     #\n";
              menu += "\t# --->--->--->--->--->--->--->--->--- #\n";
              menu += "\t#                                     #\n";
              menu += "\t# [1] - Alta cliente                  #\n";
              menu += "\t# [2] - Lista clientes                #\n";
              menu += "\t# [3] - Alta nuevo envío              #\n";
              menu += "\t# [4] - Consultar estado envío        #\n";
              menu += "\t# [5] - Cambiar estado envío          #\n";
              menu += "\t# [6] - Listar envíos por cliente     #\n";
              menu += "\t# [7] - Listar envíos en reparto      #\n";
              menu += "\t# [X] - Salir                         #\n";
              menu += "\t#                                     #\n";
              menu += "\t#######################################\n";
              menu += "\nOpción > ";
        System.out.print(menu);
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void altaCliente() {
        clear();
        System.out.println("\nAlta nuevo cliente >\n");
        System.out.print("NIF/CIF: ");
        String dni = cin.nextLine().toUpperCase();
        System.out.print("Nombre/Razón Social: ");
        String nombre = cin.nextLine();
        System.out.println("\n[C]onfirmar | []-Cancelar");
        if(cin.nextLine().toUpperCase().equals("C")) {
            pqXpress.altaCliente(dni, nombre);
            clear();
            System.out.println("\nCliente creado");
            System.out.print("\nPulse [RET] para continuar ");
            cin.nextLine();
        }
    }

    public static void listaClientes() {
        clear();
        List<Cliente> lista = pqXpress.listaClientes();
        System.out.println("\nLista de clientes >\n");
        System.out.println("NIF/CIF \tNOMBRE");
        System.out.println("-----------------------------------------------------");
        for (Cliente cli : lista) {
            System.out.println(cli.getIdCli() + "\t" + cli.getNombre());
        }
        System.out.print("\nPulse [RET] para continuar ");
        cin.nextLine();
    }

    public static void altaNuevoEnvio() {
        String dni = "", destinatario, destino;
        Cliente cliente = null;
        Boolean exit = false;
        Map<String, String> clientes = pqXpress.mapClientes();

        while (exit == false) {
            clear();
            System.out.println("\nAlta de nuevo envío >\n");
            System.out.print("NIF/CIF ([L]ista clientes): ");
            dni = cin.nextLine().toUpperCase();
            if(dni.equals("L")){
                listaClientes();
                continue;
            }
            System.out.print("\nDestinatario: ");
            destinatario = cin.nextLine();
            System.out.print("\nDestino: ");
            destino = cin.nextLine();
            //Verificamos datos
            if(dni.length() != 9 || destinatario == null || destino == null) {
                System.out.println("Datos no válidos");
                System.out.print("\nPulse [RET] para continuar | [X] menú principal: ");
                if(cin.nextLine().toUpperCase().equals("X")) {
                    exit=true;
                    break;
                }
                continue;
            }
            if(!clientes.keySet().contains(dni)) {
                System.out.println("Cliente no registrado");
                System.out.print("\nPulse [RET] para continuar | [X] menú principal: ");
                if(cin.nextLine().toUpperCase().equals("X")) {
                    exit=true;
                    break;
                }
                continue;
            }
            System.out.println("\n[C]onfirmar | []-Cancelar");
            if(cin.nextLine().toUpperCase().equals("C")) {
                for (Cliente cli : pqXpress.listaClientes()) {
                    if(cli.getIdCli().equals(dni))
                    cliente = cli;
                }
                int id = pqXpress.altaEnvio(cliente, destinatario, destino);;
                clear();
                System.out.println("\nNuevo envío creado con ID: " + id);
                System.out.print("\nPulse [RET] para continuar ");
                cin.nextLine();
                exit = true;
            }
        }
    }

    public static void consultarEnvio() throws Exception{
        boolean exit = false;
        Integer id = 0;
        String msg = "";

        while (exit == false) {
            clear();
            System.out.println(msg);
            System.out.println("\nConsultar estado de un envío >\n");
            System.out.print("ID del envío: ");
            
            try{
                id = cin.nextInt();   
            }catch (InputMismatchException e){
                msg = "El ID sólo debe contener números.";
                
            }
            Set<Integer> listaEnvios = pqXpress.getPaquetes().keySet();
            if(!listaEnvios.contains(id)) {
                System.out.println("No se encuentra el ID [" + id + "] en el sistema");
                System.out.print("\nPulse [RET] para continuar ");
                cin.nextLine();
                continue;
            }else{
                Paquete pack = pqXpress.getPaquetes().get(id);
                clear();
                System.out.println("\nDatos del envío " + id + " >\n");
                System.out.println("---------------------------------------------------");
                System.out.println("ID:            " + pack.getIdPack());
                System.out.println("CLIENTE:       " + pack.getCliente().getNombre());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("FECHA:         " + pack.getfAlta().format(formatter));
                System.out.println("DESTINATARIO:  " + pack.getDestinatario());
                System.out.println("DESTINO:       " + pack.getDestino());
                System.out.println("SITUACION:     " + pack.getEstado().informaEstado());
                System.out.println("\nPulse [RET] para continuar ");
                cin.nextLine();
                cin.nextLine();
                exit = true;
            }
        }   
    }

    public static void cambiarEnvio() {
        boolean exit = false;
        Integer id = 0;
        String msg = "";

        while (exit == false) {
            clear();
            System.out.println(msg);
            System.out.println("Cambio de estado del envío >\n");
            System.out.print("ID del envío: ");
            try{
                id = cin.nextInt();   
            }catch (InputMismatchException e){
                msg = "\n¡El ID sólo debe contener números!\n";
                id = 0;
                cin.nextLine();
            }
            if(id == 0) continue;
            Set<Integer> listaEnvios = pqXpress.getPaquetes().keySet();
            if(!listaEnvios.contains(id)) {
                clear();
                System.out.println("\nNo se encuentra el ID [" + id + "] en el sistema");
                System.out.print("\nPulse [RET] para continuar ");
                cin.nextLine();
                cin.nextLine();
                continue;
            }else{
                Paquete pack = pqXpress.getPaquetes().get(id);
                clear();
                System.out.println("El paquete con ID [" + id + "] se encuentra en situación: \n");
                System.out.println("--> " + pack.getEstado().informaEstado() + "\n");
                System.out.println("¿Desea promover su estado? ");
                System.out.println("\n[C]onfirmar | []-Cancelar");
                cin.nextLine();
                if(cin.nextLine().toUpperCase().equals("C")) {
                   pack.cambiaEstado();
                   clear();
                   System.out.println("Se ha modificado el estado del envío [" + id + "] a: \n"); 
                   System.out.println("--> " + pack.getEstado().informaEstado() + "\n");
                   System.out.print("\nPulse [RET] para continuar ");
                   cin.nextLine();
                   break;
                } 
            }
        }
    }

    public static void listarEnvioCliente() {
        String dni = "";
        Boolean exit = false;
        Map<String, String> clientes = pqXpress.mapClientes();

        while (exit == false) {
            clear();
            System.out.println("\nAlta de nuevo envío >\n");
            System.out.print("NIF/CIF ([L]ista clientes): ");
            dni = cin.nextLine().toUpperCase();
            if(dni.equals("L")){
                listaClientes();
                continue;
            }

            if(clientes.keySet().contains(dni)) {
                clear();
                System.out.println("\nNIF/CIF: " + dni);
                System.out.println("Nombre: " + clientes.get(dni) + "\n\n");
                String f="FECHA", h="HORA", i="ID_PAQ", d="DESTINO", s="SITUACION";
                String l="-------------------------------------------------------------------------------------------------------------------";
                System.out.printf("%-12s %-7s %-8s %-30s %-30s\n", f, h, i, d, s);
                System.out.println(l);
                
                DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("HH:mm");
                
                for (Map.Entry<Integer, Paquete> liPaq : pqXpress.getPaquetes().entrySet()) {
                    Paquete p = liPaq.getValue();
                    if(p.getCliente().getIdCli().equals(dni)){
                        String fVal = p.getfAlta().format(formatterF);
                        String hVal = p.getfAlta().format(formatterH);
                        int iVal = p.getIdPack();
                        String dVal = p.getDestino();
                        String sVal = p.informaEstado();
                        
                        System.out.printf("%-12s %-7s %-8s %-30s %-30s\n",fVal,hVal,iVal,dVal,sVal);
                        
                        
                    }
                }
                System.out.println(l);
                System.out.print("\nPulse [RET] para continuar ");
                cin.nextLine();
                exit = true;
                break;
            }
        }
    }

    public static void listarEnvioReparto() {
        Map<Integer, Paquete> listaEnvios = pqXpress.getPaquetes();
        Set<Paquete> listaEnReparto = new HashSet<>();

        for (Paquete env : listaEnvios.values()) {
            if(env.getEstado() instanceof EnReparto)
                listaEnReparto.add(env);
        }
        clear();
        System.out.println("\nLista de envíos en reparto >\n");
        String f="FECHA", h="HORA", i="ID_PAQ", d="DESTINO", s="SITUACION";
        String l="-------------------------------------------------------------------------------------------------------------------";
        System.out.printf("%-12s %-7s %-8s %-30s %-30s\n", f, h, i, d, s);

        System.out.println(l);
        
        DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterH = DateTimeFormatter.ofPattern("HH:mm");
        if(listaEnReparto.size() == 0) System.out.println("No hay pedidos en reparto.");
        for (Paquete p : listaEnReparto) {
            String fVal = p.getfAlta().format(formatterF);
            String hVal = p.getfAlta().format(formatterH);
            int iVal = p.getIdPack();
            String dVal = p.getDestino();
            String sVal = p.informaEstado();
            
            System.out.printf("%-12s %-7s %-8s %-30s %-30s\n",fVal,hVal,iVal,dVal,sVal);
        }
        System.out.println(l);
        System.out.print("\nPulse [RET] para continuar ");
        cin.nextLine();
    }

    public static void launchApp() {
        pqXpress = new PqManager();
    }
}

