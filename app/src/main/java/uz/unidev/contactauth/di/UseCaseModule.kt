package uz.unidev.contactauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.unidev.contactauth.domain.usecases.auth.LoginUseCase
import uz.unidev.contactauth.domain.usecases.auth.LogoutUseCase
import uz.unidev.contactauth.domain.usecases.auth.RegisterUseCase
import uz.unidev.contactauth.domain.usecases.auth.UnregisterUseCase
import uz.unidev.contactauth.domain.usecases.auth.impl.LoginUseCaseImpl
import uz.unidev.contactauth.domain.usecases.auth.impl.LogoutUseCaseImpl
import uz.unidev.contactauth.domain.usecases.auth.impl.RegisterUseCaseImpl
import uz.unidev.contactauth.domain.usecases.auth.impl.UnregisterUseCaseImpl
import uz.unidev.contactauth.domain.usecases.contact.AddContactUseCase
import uz.unidev.contactauth.domain.usecases.contact.DeleteContactUseCase
import uz.unidev.contactauth.domain.usecases.contact.GetAllContactsUseCase
import uz.unidev.contactauth.domain.usecases.contact.UpdateContactUseCase
import uz.unidev.contactauth.domain.usecases.contact.impl.AddContactUseCaseImpl
import uz.unidev.contactauth.domain.usecases.contact.impl.DeleteContactUseCaseImpl
import uz.unidev.contactauth.domain.usecases.contact.impl.GetAllContactsUseCaseImpl
import uz.unidev.contactauth.domain.usecases.contact.impl.UpdateContactUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindLogoutUseCase(logoutUseCaseImpl: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    fun bindRegisterUseCase(registerUseCaseImpl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    fun bindUnregisterUseCase(unregisterUseCaseImpl: UnregisterUseCaseImpl): UnregisterUseCase

    @Binds
    fun bindAddContactUseCase(addContactUseCaseImpl: AddContactUseCaseImpl): AddContactUseCase

    @Binds
    fun bindDeleteContactUseCase(deleteContactUseCaseImpl: DeleteContactUseCaseImpl): DeleteContactUseCase

    @Binds
    fun bindGetAllContactsUseCase(getAllContactUseCaseImpl: GetAllContactsUseCaseImpl): GetAllContactsUseCase

    @Binds
    fun bindUpdateContactUseCase(updateContactUseCaseImpl: UpdateContactUseCaseImpl): UpdateContactUseCase
}