package de.rki.coronawarnapp.exception.reporting

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import de.rki.coronawarnapp.CoronaWarnApplication
import de.rki.coronawarnapp.R
import de.rki.coronawarnapp.exception.ExceptionCategory
import org.coralibre.android.sdk.fakegms.common.api.ApiException
import java.io.PrintWriter
import java.io.StringWriter

fun Throwable.report(exceptionCategory: ExceptionCategory) =
    this.report(exceptionCategory, null, null)

fun Throwable.report(
    exceptionCategory: ExceptionCategory,
    prefix: String?,
    suffix: String?
) {
    val intent = Intent(ReportingConstants.ERROR_REPORT_LOCAL_BROADCAST_CHANNEL)
    intent.putExtra(ReportingConstants.ERROR_REPORT_CATEGORY_EXTRA, exceptionCategory.name)
    intent.putExtra(ReportingConstants.ERROR_REPORT_PREFIX_EXTRA, prefix)
    intent.putExtra(ReportingConstants.ERROR_REPORT_SUFFIX_EXTRA, suffix)
    intent.putExtra(ReportingConstants.ERROR_REPORT_MESSAGE_EXTRA, this.message)

    if (this is ReportedExceptionInterface) {
        intent.putExtra(ReportingConstants.ERROR_REPORT_CODE_EXTRA, this.code)
        this.resId?.let { intent.putExtra(ReportingConstants.ERROR_REPORT_RES_ID, it) }
    }

    var stackExtra = ""

    // override the message with a generic one if it is an ApiException
    if (this is ApiException) {

        val errorMessage = R.string.errors_communication_with_api

        // I use the intent's putExtra(...) method to add the exception message to the errorMessage
        // string, to have as much information as possible (see below).
        // TODO: Add statusCode field to ApiException, implement proper mapping from status
        //  to statusCode when creating an ApiException. Then the original behaviour can be
        //  used, which is commented out above. However, this might not be required.

        intent.putExtra(
            ReportingConstants.ERROR_REPORT_RES_ID,
            errorMessage
        )
        intent.putExtra(ReportingConstants.ERROR_REPORT_CODE_EXTRA, ErrorCodes.API_EXCEPTION.code)

        // Change introduced with CoraLibre:
        // Now, the exception message is added, instead of the statusCode:
        // intent.putExtra(ReportingConstants.ERROR_REPORT_API_EXCEPTION_CODE, this.statusCode)
        intent.putExtra(ReportingConstants.ERROR_REPORT_API_EXCEPTION_CODE, this.message)
    }

    if (stackExtra.isEmpty()) {
        val sw = StringWriter()
        this.printStackTrace()
        this.printStackTrace(PrintWriter(sw))
        stackExtra = sw.toString()
    }

    intent.putExtra(ReportingConstants.ERROR_REPORT_STACK_EXTRA, stackExtra)
    LocalBroadcastManager.getInstance(CoronaWarnApplication.getAppContext()).sendBroadcast(intent)
}

fun reportGeneric(
    stackString: String
) {
    val intent = Intent(ReportingConstants.ERROR_REPORT_LOCAL_BROADCAST_CHANNEL)
    intent.putExtra("category", ExceptionCategory.INTERNAL.name)
    intent.putExtra("stack", stackString)
    LocalBroadcastManager.getInstance(CoronaWarnApplication.getAppContext()).sendBroadcast(intent)
}
