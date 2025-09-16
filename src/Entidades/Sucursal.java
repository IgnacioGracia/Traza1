package Entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalTime;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @ToString(exclude = "empresa")  // Excluir empresa para evitar recursión infinita
    @SuperBuilder
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;

    private Domicilio domicilio; //Asociacion con Domicilio, relacion de 1 a 1
    private Empresa empresa; //Bidireccionalidad con Empresa, relacion de 1 a 1
/* //Esto seria en caso de no usar lombock y tener que codear el ToString
    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horarioApertura=" + horarioApertura +
                ", horarioCierre=" + horarioCierre +
                ", esCasaMatriz=" + esCasaMatriz +
                ", domicilio=" + domicilio +  // Aquí se imprime el domicilio, que ya tiene su propia lógica de toString
                '}';
    }
   */
}
