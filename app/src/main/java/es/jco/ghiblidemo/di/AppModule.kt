package es.jco.ghiblidemo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource
import es.jco.ghiblidemo.data.database.AppRoomDatabase
import es.jco.ghiblidemo.data.database.RoomConstants
import es.jco.ghiblidemo.data.database.RoomDataSource
import es.jco.ghiblidemo.data.server.APIService
import es.jco.ghiblidemo.data.server.ServerConstants
import es.jco.ghiblidemo.data.server.ServerDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * App module
 * This class declares all providers to inject application modules
 *
 * @constructor Create empty App module
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides for data server

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // Annotation -> To improve the request security, if the API had an API KEY, it could be
        //               added to the header in an HTTP interceptor
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(ServerConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(ServerConstants.HTTP_READ_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(ServerConstants.HTTP_WRITE_TIMEOUT, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServerConstants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService): RemoteDataSource {
        return ServerDataSource(apiService)
    }

    // Provides for local database

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            AppRoomDatabase::class.java,
            RoomConstants.DBNAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(appRoomDatabase: AppRoomDatabase): LocalDataSource {
        return RoomDataSource(appRoomDatabase)
    }
}