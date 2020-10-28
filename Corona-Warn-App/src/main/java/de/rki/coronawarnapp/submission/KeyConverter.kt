package de.rki.coronawarnapp.submission

import de.rki.coronawarnapp.server.protocols.KeyExportFormat
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.TemporaryExposureKey

interface KeyConverter {

    fun toExternalFormat(
        key: TemporaryExposureKey,
        riskValue: Int,
        daysSinceOnsetOfSymptoms: Int
    ): KeyExportFormat.TemporaryExposureKey
}
