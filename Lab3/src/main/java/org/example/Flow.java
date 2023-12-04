package src.main.java.org.example;
import src.main.java.org.example.Option;

import java.util.List;

public class Flow  {
    int id;
    String name_msg;
    List<Option> options;

    public Flow(int id, String name_msg, List<Option> options) {
        this.id = id;
        this.name_msg = name_msg;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_msg() {
        return name_msg;
    }

    public void setName_msg(String name_msg) {
        this.name_msg = name_msg;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void flowAddOption(Option option){
        boolean optionExists = options.stream().anyMatch(o -> o.getCode() == option.getCode());

        if (!optionExists) {
            // Si no existe, agregar la opción al flujo
            this.options.add(option);
        } else {
            // Si existe, puedes manejarlo de alguna manera, como imprimir un mensaje o realizar otra acción
            System.out.println("La opción con código " + option.getCode() + " ya existe en el flujo.");
        }
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", name_msg='" + name_msg + '\'' +
                ", \noptions=" + options +
                '}';
    }
}
