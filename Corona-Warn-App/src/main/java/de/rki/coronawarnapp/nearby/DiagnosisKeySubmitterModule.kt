package de.rki.coronawarnapp.nearby

import dagger.Module
import dagger.Provides
import de.rki.coronawarnapp.nearby.modules.diagnosiskeyprovider.DefaultDiagnosisKeyProvider
import de.rki.coronawarnapp.nearby.modules.diagnosiskeyprovider.DiagnosisKeyProvider
import javax.inject.Singleton

@Module
class DiagnosisKeySubmitterModule {
    @Singleton
    @Provides
    fun diagnosisKeySubmitter(submitter: DefaultDiagnosisKeyProvider): DiagnosisKeyProvider =
        submitter
}
