package es.jco.ghiblidemo.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Use case module
 * This class declares all providers to inject the use cases
 *
 * @constructor Create empty Use case module
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule