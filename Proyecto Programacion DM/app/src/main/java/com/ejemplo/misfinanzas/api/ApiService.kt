// ApiService.kt
// Interfaz que define los endpoints de la API
package com.ejemplo.misfinanzas.api

// @GET indica que es una petición HTTP GET
import retrofit2.http.GET
// @Query agrega parámetros a la URL (?clave=valor)
import retrofit2.http.Query

// Cada función de esta interfaz es un endpoint de la API
// Retrofit genera la implementación automáticamente
interface ApiService {

    // @GET define la ruta relativa del endpoint
    // "latest" se concatena con la BASE_URL del RetrofitClient
    // suspend indica que se ejecuta en una coroutine (segundo plano)
    @GET("latest")
    suspend fun obtenerTasas(
        // @Query agrega parámetros a la URL
        // Resultado: /latest?base=USD
        @Query("base") monedaBase: String = "USD"
    ): TasaCambioResponse
    // El tipo de retorno es la data class que representa la respuesta JSON
    // Gson la convierte automáticamente
}