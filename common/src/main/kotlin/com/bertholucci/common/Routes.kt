package com.bertholucci.common

import android.content.Context
import com.bertholucci.common.extensions.intentForAction

private const val ACTION_SEARCH = "SEARCH"

fun Context.intentToSearch() = intentForAction(ACTION_SEARCH)