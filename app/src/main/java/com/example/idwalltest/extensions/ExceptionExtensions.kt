package com.example.idwalltest.extensions

import android.util.Log
import java.lang.Exception

fun Exception.log(): String = Log.getStackTraceString(this)