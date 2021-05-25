package com.fadelpamungkas.bookmark.di

import com.fadelpamungkas.bookmark.BookmarkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkViewModel(get()) }
}