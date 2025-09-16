package Entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;
    @AllArgsConstructor //Constructor con todos los parametros
    @NoArgsConstructor //Constructor vacio
    @Setter //Todos los metodos set (uno por atributo)
    @Getter //Todos los metodos get (uno por atributo)
    @ToString(exclude = "sucursales") //Excluir sucursales para evitar recursión infinita
    @SuperBuilder //Builder que contempla herencias, podriamos usar @Builder tambien
    public class Empresa {
        private Long id;
        private String nombre;
        private String razonSocial;
        private Integer cuit;
        private String logo;

        @Builder.Default //Cuando uso el .build(), si no coloco un valor para sucursales en el builder, me va a devolver el HashSet vacio en vez de un Null
        private Set<Sucursal> sucursales = new HashSet<>(); //Asociacion con Sucursales, relacion de 1 a N
}
