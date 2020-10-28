package de.rki.coronawarnapp.nearby

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.coralibre.android.sdk.fakegms.nearby.Nearby
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureNotificationClient

@Module
class NearbyModule {

    @Reusable
    @Provides
    fun provideENF(context: Context): ExposureNotificationClient {
        return ExposureNotificationClientFactory.createClient()
    }
}
