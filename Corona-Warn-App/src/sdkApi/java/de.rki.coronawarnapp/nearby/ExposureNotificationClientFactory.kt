package de.rki.coronawarnapp.nearby

import de.rki.coronawarnapp.CoronaWarnApplication
import org.coralibre.android.sdk.fakegms.nearby.Nearby
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureNotificationClient

object ExposureNotificationClientFactory {
    fun createClient(): ExposureNotificationClient {
        return Nearby.getExposureNotificationClient(CoronaWarnApplication.getAppContext())
    }
}
