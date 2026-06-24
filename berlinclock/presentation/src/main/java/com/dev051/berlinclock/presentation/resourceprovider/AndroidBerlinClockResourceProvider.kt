package com.dev051.berlinclock.presentation.resourceprovider

import android.content.Context
import com.dev051.berlinclock.domain.resourceprovider.BerlinClockResourceProvider
import com.dev051.berlinclock.presentation.R

class AndroidBerlinClockResourceProvider(
    private val context: Context
) : BerlinClockResourceProvider {
    override val genericErrorMessage: String
        get() = context.getString(R.string.berlinclock_genericError_message)
    override val retryLabel: String
        get() = context.getString(R.string.berlinclock_retry_label)
    override val switchDisplayLabel: String
        get() = context.getString(R.string.berlinclock_switchDisplay_label)
}
