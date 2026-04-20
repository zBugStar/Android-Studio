1. ¿Cuál es la diferencia entre val y var?
    
    val: valor inmutable (no se puede cambiar después de asignarlo).
    var: variable mutable (sí se puede modificar).

2. ¿Para qué sirve el operador ?: (Elvis)? 

    Sirve para dar un valor por defecto si algo es null.
    Ejemplo: val nombre = usuario?.nombre ?: "Desconocido"

3. ¿Qué genera automáticamente una data class que una clase normal no?

    Métodos útiles como toString(), equals(), hashCode() y copy().
    Además, permite usar destructuring (val (id, titulo) = tarea).

4. ¿Qué hace el Adapter en un RecyclerView?

    Conecta los datos (lista de objetos) con la vista (cada fila del RecyclerView).
    Se encarga de inflar el layout y asignar valores a los elementos visuales.

5. ¿Por qué usar View Binding en lugar de findViewById?

    - Es más seguro y rápido: genera referencias automáticas a las vistas.
    - Evita errores de tipo y elimina la necesidad de buscar vistas manualmente.
    - Hace el código más limpio y fácil de mantener.