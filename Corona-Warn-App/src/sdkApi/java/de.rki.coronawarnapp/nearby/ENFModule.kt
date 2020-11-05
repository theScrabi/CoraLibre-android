package de.rki.coronawarnapp.nearby

import android.content.Context
import dagger.Module
import dagger.Provides
import org.coralibre.android.sdk.fakegms.nearby.Nearby
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureNotificationClient
import javax.inject.Singleton

@Module
class ENFModule {
    @Singleton
    @Provides
    fun exposureNotificationClient(context: Context): ExposureNotificationClient =
        Nearby.getExposureNotificationClient(context)
}
