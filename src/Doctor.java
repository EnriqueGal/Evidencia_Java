import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor {
    public HashMap<String, String> doctores = new HashMap<>();
    private String archivoTxt = "./src/db/Doctores.txt";
    private File archivo = new File(archivoTxt);
    private List<String> id = new ArrayList<>();
    private int i = 0;

    public void guardar(String nombre, String apellido){
        if (!archivo.exists()){
            crearArchivo();
        }else {
            cargarArchivo();
        }
        i = 1;
        doctores.put(nombre, apellido);
        String lista = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))){
            for (Map.Entry<String, String> aux : doctores.entrySet()){
                lista = lista.concat("ID:"+ i + ";" + aux.getKey() + ";" + aux.getValue() + ";\n");
                i++;
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
            System.out.println("Ocurrio un error intene de nuevo");
            e.printStackTrace();
        }
    }
    public void cargarArchivo(){
        doctores.clear();
        id.clear();
        String aux, str = "";
        int a = 1, b = 2;
        try (BufferedReader buff = new BufferedReader(new FileReader(archivoTxt))){
            while ((aux = buff.readLine()) != null){
                str = str.concat(aux);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] tokens = str.split(";");
        for (int i = 0; i < tokens.length; i = i + 3) {
            id.add(tokens[i]);
            doctores.put(tokens[a], tokens[b]);
            a = a + 3;
            b = b + 3;
        }
    }
}
