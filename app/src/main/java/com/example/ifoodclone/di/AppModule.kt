package com.example.ifoodclone.di

import com.example.ifoodclone.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.example.ifoodclone.domain.usecase.AutenticacaoUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideAutenticacaoUseCase(autenticacaoRepositoryImpl: AutenticacaoRepositoryImpl): AutenticacaoUseCase {
        return AutenticacaoUseCase(autenticacaoRepositoryImpl)
    }

    @Provides
    fun provideAutenticacaoRepositoryImpl(firebaseAuth: FirebaseAuth): AutenticacaoRepositoryImpl {
        return AutenticacaoRepositoryImpl(firebaseAuth)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}