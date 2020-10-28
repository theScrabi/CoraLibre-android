package de.rki.coronawarnapp.nearby

import android.content.Context
import dagger.Module
import dagger.Provides
import de.rki.coronawarnapp.nearby.modules.diagnosiskeyprovider.DefaultDiagnosisKeyProvider
import de.rki.coronawarnapp.nearby.modules.diagnosiskeyprovider.DiagnosisKeyProvider
import org.coralibre.android.sdk.fakegms.nearby.Nearby
import org.coralibre.android.sdk.fakegms.nearby.exposurenotification.ExposureNotificationClient
import javax.inject.Singleton

@Module
class ENFModule {

    @Singleton
    @Provides
    fun exposureNotificationClient(context: Context): ExposureNotificationClient =
        ExposureNotificationClientFactory.createClient()

    @Singleton
    @Provides
    fun diagnosisKeySubmitter(submitter: DefaultDiagnosisKeyProvider): DiagnosisKeyProvider =
        submitter
}
