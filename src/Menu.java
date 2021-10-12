import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Menu {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        Doctor doctor = new Doctor();
        Paciente paciente = new Paciente();
        Cita cita = new Cita();
        List<String> credenciales;
        int aceptado = 0, opcion = 0;
        boolean accept;
        Menu m = new Menu();
        do {
            credenciales = m.Login();
            accept = m.admin(credenciales);
            if (accept = true){
                aceptado = 1;
            }
        }while (aceptado == 0);

        do {
            m.Menu();
            opcion = Integer.parseInt(m.reader.readLine());
            switch (opcion){
                case 1:
                    System.out.println("Ingresa el nombre del doctor: ");
                    String nombre = m.reader.readLine();
                    System.out.println("Ingresa el apellido del doctor");
                    String apellido = m.reader.readLine();
                    doctor.guardar(nombre, apellido);
                    System.out.println("Guardado");
                    break;
                case 2:
                    System.out.println("Ingresa el nombre del paciente: ");
                    String nombrep = m.reader.readLine();
                    System.out.println("Ingresa el apellido del paciente: ");
                    String apellidop = m.reader.readLine();
                    paciente.guardar(nombrep, apellidop);
                    System.out.println("Guardado");
                    break;
                case 3:
                    System.out.println("Ingresa el nombre del doctor: ");
                    String nombred = m.reader.readLine();
                    System.out.println("Ingresa el nombre del paciente: ");
                    String nombrepc = m.reader.readLine();
                    System.out.println("Ingresa la fecha: ");
                    String fecha = m.reader.readLine();
                    System.out.println("Ingresa el motivo: ");
                    String motivo = m.reader.readLine();
                    cita.guardar(fecha, motivo, nombred, nombrepc);
                    System.out.println("Guardado");
                    break;
                case 4:
                    System.out.println("Citas agendadas: ");
                    cita.mostrarCitas();
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }while (opcion != 5);


    }
    public List<String> Login() throws IOException {
        List<String> usuario = new ArrayList<>();
        System.out.println("Login");
        System.out.println("===============================");
        System.out.println("Ingrese su id: ");
        usuario.add(reader.readLine());
        System.out.println("Ingrese su contraseña: ");
        usuario.add(reader.readLine());
        return usuario;
    }
    public void Menu(){
        System.out.println("Administracion de citas");
        System.out.println("===============================");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1- Alta de  Doctor");
        System.out.println("2- Alta de Paciente");
        System.out.println("3- Crear Cita");
        System.out.println("4- Ver Citas");
        System.out.println("5- SALIR");
    }
    public boolean admin(List<String> cred)throws IOException{
        boolean accept;
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/administradores.properties"));
        System.out.println(prop.get("user"));
        String id = prop.get("id").toString();
        String pass = prop.get("password").toString();
        if (cred.get(0).equals(id) && cred.get(1).equals(pass)){
            accept = true;
        }else
            accept = false;
        return accept;
    }
}
