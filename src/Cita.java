import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cita {
    public HashMap<String, String> fecha = new HashMap<>();
    public HashMap<String, String> cita = new HashMap<>();
    private String archivoTxt = "./src/db/Citas.txt";
    private File archivo = new File(archivoTxt);
    private List<String> id = new ArrayList<>();
    private int i = 0;

    public void guardar(String fecha, String motivo, String paciente, String doctor){
        if (!archivo.exists()){
            crearArchivo();
        }else {
            cargarArchivo();
        }
        i = 1;
        int z = 1;
        cita.put(paciente, doctor);
        this.fecha.put(fecha, motivo);
        List<String> fechas = new ArrayList<>();
        List<String> actores = new ArrayList<>();
        String lista = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))){
            for (Map.Entry<String, String> aux : this.fecha.entrySet()){
                fechas.add(aux.getKey());
                fechas.add(aux.getValue());
            }
            for (Map.Entry<String, String> aux2 : cita.entrySet()){
                actores.add(aux2.getKey());
                actores.add(aux2.getValue());
            }
            for (int i = 0; i < fechas.size(); i = i + 2){
                lista = lista.concat(this.i + ";" + fechas.get(i) + ";" + fechas.get(z) + ";" + actores.get(i) + ";" + actores.get(z) + ";\n");
                this.i++;
                z = z + 2;
            }
            writer.write(lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void crearArchivo(){
        try {
            archivo.createNewFile();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void cargarArchivo(){
        String aux, str = "";
        int a = 1, b = 2, d = 3, f = 4;
        try (BufferedReader buff = new BufferedReader(new FileReader(archivoTxt))){
            while ((aux = buff.readLine()) != null){
                str = str.concat(aux);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] tokens = str.split(";");
        for (int i = 0; i < tokens.length; i = i + 5) {
            id.add(tokens[i]);
            fecha.put(tokens[a], tokens[b]);
            cita.put(tokens[d], tokens[f]);
            a = a + 5;
            b = b + 5;
            d = d + 5;
            f = f + 5;
        }
    }
    public void mostrarCitas(){
        String aux, str = "";
        int x = 0;
        try (BufferedReader buff = new BufferedReader(new FileReader(archivoTxt))){
            while ((aux = buff.readLine()) != null){
                str = str.concat(aux);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] tokens = str.split(";");
        for (String string : tokens){
            System.out.print(string + ", ");
            x++;
            if (x == 5){
                System.out.print("\n");
                x = 0;
            }
        }
    }
}
