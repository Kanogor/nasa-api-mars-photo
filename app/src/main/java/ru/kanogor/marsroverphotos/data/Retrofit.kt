package ru.kanogor.marsroverphotos.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

private const val BASE_URL = "https://api.nasa.gov/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideSearchMarsPhoto(retrofit: Retrofit): SearchMarsPhoto =
        retrofit.create(SearchMarsPhoto::class.java)

    @Singleton
    @Provides
    fun provideRepository(searchMarsPhoto: SearchMarsPhoto) = MarsPhotoRepository(searchMarsPhoto)

}

interface SearchMarsPhoto {
    @GET("/mars-photos/api/v1/rovers/spirit/photos?")
    suspend fun getMarsPhoto(
        @Query("page") page: Int,
        @Query("sol") sol: Int = 10,
        @Query("api_key") api_key: String = API_KEY
    ): Response<MarsPhotosDto>

    private companion object {
        private const val API_KEY = "JbyxYIx7TqhejfDGV0jeoU2u5dL6AsrAws6XIaGq"
    }
}