package com.fadelpamungkas.core.di

import androidx.room.Room
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.fadelpamungkas.core.data.localsource.AppDatabase
import com.fadelpamungkas.core.data.localsource.LocalDataSource
import com.fadelpamungkas.core.data.remotesource.ApiRequest
import com.fadelpamungkas.core.data.remotesource.RemoteDataSource
import com.fadelpamungkas.core.domain.repository.AppRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<AppRepository> {
        com.fadelpamungkas.core.data.AppRepository(
            get(),
            get(),
            get()
        )
    }
}

val databaseModule = module {
    factory { get<AppDatabase>().appDAO() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("execration".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/")
            .client(get())
            .build()
        retrofit.create(ApiRequest::class.java)
    }
}