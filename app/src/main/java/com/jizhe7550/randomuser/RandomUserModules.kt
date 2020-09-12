package com.jizhe7550.randomuser

import com.readystatesoftware.chuck.ChuckInterceptor
import com.jizhe7550.randomuser.api.ApiService
import com.jizhe7550.randomuser.api.RandomUserService
import com.jizhe7550.randomuser.data.RandomUserRepository
import com.jizhe7550.randomuser.db.RandomUserLocalCache
import com.jizhe7550.randomuser.db.UserDatabase
import com.jizhe7550.randomuser.model.User
import com.jizhe7550.randomuser.ui.user_detail.UserDetailViewModel
import com.jizhe7550.randomuser.ui.user_list.UserListViewModel
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val allModules
    get() = listOf(
        dataModule,
        userListViewModel,
        userDetailViewModel
    )

val dataModule = module {

    single<RadomUserIdlingResource> { SimpleRandomUserIdlingResource() }

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<Interceptor> { ChuckInterceptor(androidContext()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single<UserDatabase> {
        UserDatabase.getInstance(androidContext())
    }

    single<RandomUserRepository> {
        val retrofitApiService = get<Retrofit>().create(ApiService::class.java)
        val localCache = RandomUserLocalCache(get(), Executors.newSingleThreadExecutor())
        val randomUserService = RandomUserService(retrofitApiService)
        RandomUserRepository(randomUserService, localCache, get())
    }
}

val userListViewModel = module {
    viewModel { UserListViewModel(get()) }
}

val userDetailViewModel = module {
    viewModel { (user: User) ->
        UserDetailViewModel(user)
    }
}