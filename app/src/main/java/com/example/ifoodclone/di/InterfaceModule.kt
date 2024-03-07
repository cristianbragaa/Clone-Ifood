package com.example.ifoodclone.di

import com.example.ifoodclone.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.example.ifoodclone.data.remote.firebase.repository.IAutenticacaoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindIAutenticacaoRepository(
        autenticacaoRepositoryImpl: AutenticacaoRepositoryImpl
    ) : IAutenticacaoRepository

}