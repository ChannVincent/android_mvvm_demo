package chann.vincent.mvvm

import android.app.Application
import chann.vincent.mvvm.data.db.LeBonCoinDatabase
import chann.vincent.mvvm.data.network.*
import chann.vincent.mvvm.data.repository.AlbumRepository
import chann.vincent.mvvm.data.repository.AlbumRepositoryImpl
import chann.vincent.mvvm.ui.album.AlbumListViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))

        // dependency injection with kodein : pre-populate values
        bind() from singleton { LeBonCoinDatabase(instance()) }
        bind() from singleton { instance<LeBonCoinDatabase>().currentAlbumDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { LeBonCoinApiService(instance()) }
        bind<AlbumNetworkDataSource>() with singleton { AlbumNetworkDataSourceImpl(instance()) }
        bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance(), instance()) }
        bind() from provider { AlbumListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}