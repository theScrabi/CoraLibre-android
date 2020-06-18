package de.rki.coronawarnapp.nearby

import android.util.Log
import org.coralibre.android.sdk.fakegms.nearby.Nearby
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureConfiguration
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureInformation
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureSummary
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.TemporaryExposureKey
import org.coralibre.android.sdk.fakegms.tasks.OnSuccessListener
import org.coralibre.android.sdk.fakegms.tasks.Task
import java.io.File

object ExposureNotificationClientFactory {
    fun createClient(): Nearby {
        return MockExposureNotificationClient()
    }
}

private class MockExposureNotificationClient : Nearby() {
    companion object {
        private const val TAG = "MockExposureNotificationClient"
    }

    private var enabled: Boolean = false

    override fun isEnabled(): Task<Boolean> {
        Log.w(TAG, "isEnabled()")

        return taskResult(enabled)
    }

    override fun provideDiagnosisKeys(
        p0: MutableList<File>?,
        p1: ExposureConfiguration?,
        p2: String?
    ): Task<Void> {
        Log.w(TAG, "provideDiagnosisKeys()")

        return taskResult(null)
    }

    override fun getExposureSummary(p0: String?): Task<ExposureSummary> {
        Log.w(TAG, "getExposureSummary()")

        val builder = ExposureSummary.ExposureSummaryBuilder()

        builder.setAttenuationDurations(
            intArrayOf(
                50,
                10,
                5
            )
        )
        builder.setDaysSinceLastExposure(1)
        builder.setMatchedKeyCount(1)
        builder.setMaximumRiskScore(50)
        builder.setSummationRiskScore(70)

        return taskResult(builder.build())
    }

    override fun start(): Task<Void> {
        Log.w(TAG, "start()")

        enabled = true

        return taskResult(null)
    }

    override fun stop(): Task<Void> {
        Log.w(TAG, "stop()")

        enabled = false

        return taskResult(null)
    }

    override fun getExposureInformation(p0: String?): Task<MutableList<ExposureInformation>> {
        Log.w(TAG, "getExposureInformation()")

        return taskResult(null)
    }

    override fun getTemporaryExposureKeyHistory(): Task<MutableList<TemporaryExposureKey>> {
        Log.w(TAG, "getTemporaryExposureKeyHistory()")

        return taskResult(mutableListOf())
    }
}

private fun <T> taskResult(result: T?): Task<T> {
    return object : Task<T>() {
        override fun addOnSuccessListener(listener: OnSuccessListener<in T>?) = this.also {
            listener?.onSuccess(result)
        }
    }
}
