package de.rki.coronawarnapp.nearby

import android.util.Log
import dagger.Module
import dagger.Provides
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureConfiguration
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureInformation
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureNotificationClient
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureSummary
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.TemporaryExposureKey
import org.coralibre.android.sdk.fakegms.tasks.Task
import org.coralibre.android.sdk.fakegms.tasks.Tasks
import java.io.File
import javax.inject.Singleton

@Module
class ENFModule {
    @Provides
    @Singleton
    fun exposureNotificationClient(): ExposureNotificationClient =
        MockExposureNotificationClient()
}

private class MockExposureNotificationClient : ExposureNotificationClient {
    companion object {
        private const val TAG = "MockExposureNotificationClient"
    }

    private var isEnabled: Boolean = true

    override fun isEnabled(): Task<Boolean> {
        Log.w(TAG, "isEnabled()")
        return Tasks.forResult(isEnabled)
    }

    override fun provideDiagnosisKeys(
        keyFiles: List<File>,
        exposureConfiguration: ExposureConfiguration?,
        token: String
    ): Task<Void> {
        Log.w(TAG, "provideDiagnosisKeys()")
        return Tasks.forResult(null)
    }

    override fun getExposureSummary(token: String): Task<ExposureSummary> {
        Log.w(TAG, "getExposureSummary()")

        val builder = ExposureSummary.ExposureSummaryBuilder()

        builder.setAttenuationDurations(
            intArrayOf(
                50,
                10,
                5
            )
        )
        builder.setDaysSinceLastExposure(0)
        builder.setMatchedKeyCount(0)
        builder.setMaximumRiskScore(0)
        builder.setSummationRiskScore(0)

        return Tasks.forResult(builder.build())
    }

    override fun start(): Task<Void> {
        Log.w(TAG, "start()")

        isEnabled = true

        return Tasks.forResult(null)
    }

    override fun stop(): Task<Void> {
        Log.w(TAG, "stop()")

        isEnabled = false

        return Tasks.forResult(null)
    }

    override fun getExposureInformation(token: String): Task<List<ExposureInformation>> {
        Log.w(TAG, "getExposureInformation()")

        return Tasks.forResult(emptyList())
    }

    override fun getTemporaryExposureKeyHistory(): Task<List<TemporaryExposureKey>> {
        Log.w(TAG, "getTemporaryExposureKeyHistory()")

        return Tasks.forResult(emptyList())
    }
}
