## Ejercicio 1

Escribir una función `describirEstudiante` que reciba un nombre (`String`), una edad (`Int`) y un correo que puede ser null (`String?`). Debe retornar un String con este formato: `"Nombre: X, Edad: X, Correo: X"`. Si el correo es null, mostrar `"No registrado"`.

Pista: usar el operador Elvis `?:` y string templates.

Salida esperada:

`describirEstudiante("Carlos", 22, "carlos@mail.com")
→ "Nombre: Carlos, Edad: 22, Correo: carlos@mail.com"

describirEstudiante("Ana", 21, null)
→ "Nombre: Ana, Edad: 21, Correo: No registrado"`

`// Solución:`

```kotlin
fun main() {
    val estudiante = describirEstudiante(nombre = "Ana", edad = 12)
    println(estudiante)
}

fun describirEstudiante(nombre: String, edad: Int, correo: String? = null ): String {
    val correo = if (correo == null) "Correo no registrado" else null  
    return "Estudiante: $nombre, Edad: $edad, Correo: $correo"
}
```

## Ejercicio 2

Escribir una función `calcularDescuento` que reciba un tipo de cliente (`String`) y un monto (`Double`). Según el tipo de cliente se aplica un descuento:

- `"premium"` → 20%
- `"regular"` → 10%
- `"nuevo"` → 5%
- Cualquier otro → 0%

La función retorna el monto final (ya con el descuento aplicado).

Pista: usar `when` sobre `tipoCliente.lowercase()`.

Salida esperada:

`calcularDescuento("premium", 100000.0) → 80000.0
calcularDescuento("regular", 50000.0)  → 45000.0
calcularDescuento("otro", 30000.0)     → 30000.0`

`// Solución:`
```kotlin
fun main() {
    println(calcularDescuento("Premium", 100000.0))
    println(calcularDescuento("regular", 50000.0))
    println(calcularDescuento("otro", 30000.0))
}
    

fun calcularDescuento(cliente: String, monto: Double): Double {
    val cliente = cliente.lowercase()
    val monto = when (cliente){
        "premium" -> monto * 0.8
        "regular" -> monto * 0.9
        "nuevo" -> monto * 0.95
       	else -> monto
    }
    return monto
}
```

## **Ejercicio 3**

Dada la siguiente lista de estudiantes, filtrar los que aprobaron (nota >= 3.0), ordenarlos de mayor a menor nota, y quedarse solo con los nombres.

```kotlin
data class Estudiante(val nombre: String, val nota: Double)

val estudiantes = listOf(
    Estudiante("María", 4.5),
    Estudiante("Pedro", 2.8),
    Estudiante("Laura", 3.9),
    Estudiante("Juan", 2.5),
    Estudiante("Sofía", 4.2)
)
```

Resultado esperado: `[María, Sofía, Laura]`

Pista: encadenar `.filter { }`, `.sortedByDescending { }` y `.map { }`.

`// Solución:`
```kotlin
data class Estudiante(val nombre: String, val nota: Double)

val estudiantes = listOf(
    Estudiante("María", 4.5),
    Estudiante("Pedro", 2.8),
    Estudiante("Laura", 3.9),
    Estudiante("Juan", 2.5),
    Estudiante("Sofía", 4.2)
)

fun main() {
    val resultado = estudiantes
        .filter { it.nota >= 3.0 }              
        .sortedByDescending { it.nota }         
        .map { it.nombre }                      
    println(resultado)
}

```

## Ejercicio 4

Crear una data class `Producto` con propiedades `nombre` (String), `precio` (Double) y `cantidad` (Int). Luego escribir una función `resumenCarrito` que reciba una lista de productos y retorne un String con el total de artículos y el monto total.

Datos de prueba

```
val carrito = listOf(
    Producto("Laptop", 2500000.0, 1),
    Producto("Mouse", 45000.0, 2),
    Producto("Teclado", 120000.0, 1)
)
```

Resultado esperado: `"Artículos: 4, Total: $2710000.00"`

Pista: `.sumOf { }` sirve para sumar sobre una propiedad calculada.

`// Solución:`

```kotlin
data class Producto(val nombre: String, val precio: Double, val cantidad: Int)

fun resumenCarrito(productos: List<Producto>): String {
    val totalArticulos = productos.sumOf { it.cantidad }                
    val montoTotal = productos.sumOf { it.precio * it.cantidad }     
    return "Artículos: $totalArticulos, Total: $${"%.2f".format(montoTotal)}"
}

fun main() {
    val carrito = listOf(
        Producto("Laptop", 2500000.0, 1),
        Producto("Mouse", 45000.0, 2),
        Producto("Teclado", 120000.0, 1)
    )

    println(resumenCarrito(carrito))
}

```