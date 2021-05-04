package com.gmg.user.di

import com.gmg.user.data.repo.UserRepo
import org.koin.dsl.module

val repoModule  = module {
    single {
        UserRepo(get ())
    }
}