package com.soywiz.klock.internal

import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.hr.HRTimeSpan

internal expect object KlockInternal {
    val currentTime: Double
    val hrNow: HRTimeSpan
    fun localTimezoneOffsetMinutes(time: DateTime): TimeSpan
    fun sleep(time: HRTimeSpan)
}

expect interface Serializable
