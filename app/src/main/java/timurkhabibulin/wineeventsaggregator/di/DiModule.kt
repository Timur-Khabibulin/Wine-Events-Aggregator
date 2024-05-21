package timurkhabibulin.wineeventsaggregator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timurkhabibulin.wineeventsaggregator.data.RepositoryImpl
import timurkhabibulin.wineeventsaggregator.domain.Repository

@Module
@InstallIn(SingletonComponent::class)
interface DiModule {
    @Binds
    fun provideRepository(impl: RepositoryImpl): Repository
}
