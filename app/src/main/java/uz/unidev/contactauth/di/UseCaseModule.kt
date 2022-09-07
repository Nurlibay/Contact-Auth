package uz.unidev.contactauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import uz.unidev.contactauth.domain.usecases.auth.LoginUseCase
import uz.unidev.contactauth.domain.usecases.auth.impl.LoginUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn
interface UseCaseModule {

    @[Binds Singleton]
    fun bindLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase


}