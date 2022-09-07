package uz.unidev.contactauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.unidev.contactauth.domain.repositories.auth.AuthRepository
import uz.unidev.contactauth.domain.repositories.auth.AuthRepositoryImpl
import uz.unidev.contactauth.domain.repositories.contact.ContactRepository
import uz.unidev.contactauth.domain.repositories.contact.ContactRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun bindContactRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository
}