package de.rki.coronawarnapp.risk

import de.rki.coronawarnapp.server.protocols.ApplicationConfigurationOuterClass
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureSummary

interface RiskLevelCalculation {

    fun calculateRiskScore(
        attenuationParameters: ApplicationConfigurationOuterClass.AttenuationDuration,
        exposureSummary: ExposureSummary
    ): Double
}
