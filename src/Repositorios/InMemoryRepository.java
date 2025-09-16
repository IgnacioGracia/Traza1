package Repositorios;
import java.lang.reflect.Method; //Uso reflection y los uso con bloques try-catch
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T> {
    protected Map<Long, T> data = new HashMap<>();
    protected AtomicLong idGenerator = new AtomicLong();

    public T save(T entity) {
        long id = idGenerator.incrementAndGet();
        // Suponiendo que las entidades tienen un metodo setId
        try {
            String clase;
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
            clase = entity.getClass().getName();
            System.out.println(clase + "   id :" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.put(id, entity);
        return entity;
    }//Le paso una entidad generica, invoca a su clase setId y le coloca un Id generado, despues lo coloca dentro del Map


    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }
    //Le doy un ID y me devuelve el objeto del map con ese ID, uso el optional para que no suceda un error si no hay objeto con ese id


    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }
    //Devuelve una lista generica de con todos los objetos de data

    public Optional<T> genericUpdate(Long id, T updatedEntity) {
        if (!data.containsKey(id)) {
            return Optional.empty();
        }

        try {
            // Establecer el mismo ID en la entidad actualizada para mantener la coherencia
            Method setIdMethod = updatedEntity.getClass().getMethod("setId", Long.class);
            setIdMethod.invoke(updatedEntity, id);

            data.put(id, updatedEntity);
            return Optional.of(updatedEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }// Le doy un ID y el objeto actualizado, si no hay objeto con ese id devuelve un optional vacio, si existe le setea el mismo id al nuevo objeto y actualiza el mapa con otro valor para la misma clave

    public Optional<T> genericDelete(Long id) {
        if (!data.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.ofNullable(data.remove(id));
    }// Elimina del data el objeto con el id que le pase

    public List<T> genericFindByField(String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        try {
            for (T entity : data.values()) {
                Method getFieldMethod = entity.getClass().getMethod("get" + capitalize(fieldName));
                Object fieldValue = getFieldMethod.invoke(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    results.add(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }//Le doy un campo y un valor y me devuelve los objetos de data con ese mismo valor en el mismo campo

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }//Coloca la primer letra en mayuscula para la convencion de los get
}
