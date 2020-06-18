package de.rki.coronawarnapp.nearby

import de.rki.coronawarnapp.CoronaWarnApplication
import org.coralibre.android.sdk.fakegms.nearby.Nearby

object ExposureNotificationClientFactory {
    fun createClient(): Nearby {
        return Nearby.getExposureNotificationClient(CoronaWarnApplication.getAppContext())
    }
}
