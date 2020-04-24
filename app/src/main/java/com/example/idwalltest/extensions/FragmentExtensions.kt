package com.example.idwalltest.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

val Fragment.appCompatActivity: AppCompatActivity?
    get() = activity as? AppCompatActivity

fun Fragment.requireAppCompatActivity(): AppCompatActivity =
    appCompatActivity
        ?: throw IllegalStateException("Fragment $this not attached to an AppCompatActivity.")