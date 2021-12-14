package com.task.solutiondeveloper.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fasakinternshiptask.R
import com.google.android.material.textfield.TextInputLayout

fun Fragment.toastShort(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showError(editTextLayout: TextInputLayout, message: String) {
    editTextLayout.apply {
        error = message
        setErrorIconDrawable(R.drawable.ic_baseline_error_24)
    }
}

fun Activity.toastShort(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
