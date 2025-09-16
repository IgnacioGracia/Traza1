package Entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @SuperBuilder
public class Localidad {
    private Long id;
    private String nombre;

    private Provincia provincia;

    // Evitar recursión infinita usando if ternario
    @Override
    public String toString() {
        return "Localidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", provincia=" + (provincia != null ? provincia.getNombre() : null) + //Esto se lee como "Si la provincia existe, dame su nombre; si no, dame un nulo"
                '}';
    }
}
