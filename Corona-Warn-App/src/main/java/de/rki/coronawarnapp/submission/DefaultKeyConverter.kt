package de.rki.coronawarnapp.submission

import com.google.protobuf.ByteString
import de.rki.coronawarnapp.server.protocols.KeyExportFormat
import dagger.Reusable
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.TemporaryExposureKey
import javax.inject.Inject

@Reusable
class DefaultKeyConverter @Inject constructor() : KeyConverter {
    override fun toExternalFormat(
        key: TemporaryExposureKey,
        riskValue: Int,
        daysSinceOnsetOfSymptoms: Int
    ) =
        KeyExportFormat.TemporaryExposureKey.newBuilder()
            .setKeyData(ByteString.readFrom(key.keyData.inputStream()))
            .setRollingStartIntervalNumber(key.rollingStartIntervalNumber)
            .setRollingPeriod(key.rollingPeriod)
            .setTransmissionRiskLevel(riskValue)
            .setDaysSinceOnsetOfSymptoms(daysSinceOnsetOfSymptoms)
            .build()
}
