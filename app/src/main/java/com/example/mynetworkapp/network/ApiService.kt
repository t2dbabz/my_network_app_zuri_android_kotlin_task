package com.example.mynetworkapp.network

import com.example.mynetworkapp.model.Character
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rickandmortyapi.com/"

interface ApiService{

    @GET("api/character")
    suspend fun getCharacters(): ApiResponse

}

private val moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retroFit
    get() = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

object Api{
    val apiService: ApiService by lazy { retroFit.create(ApiService::class.java) }
}

class ApiResponse(val results: List<Character>)