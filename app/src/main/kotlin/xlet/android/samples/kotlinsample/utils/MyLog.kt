package xlet.android.samples.kotlinsample.utils

import android.util.Log
import xlet.android.samples.kotlinsample.BuildConfig

class MyLog {
    companion object {
        private val debugFlag: Boolean = BuildConfig.DEBUG
        private val TAG: String = "KotlinSample"

        fun e(tag: String, vararg messages: Any) {
            if (debugFlag) {
                val sb = StringBuilder(getBracketTag(tag))
                for (msg in messages) {
                    sb.append(msg)
                }
                Log.e(TAG, sb.toString())
            }
        }

        fun e(tag: String, msg: String, throwable: Throwable) {
            if (debugFlag) {
                Log.e(TAG, getBracketTag(tag) + msg, throwable)
            }
        }

        fun d(tag: String, msg: String) {
            if (debugFlag) {
                Log.d(TAG, getBracketTag(tag) + msg)
            }
        }

        fun d(tag: String, vararg messages: Any) {
            if (debugFlag) {
                val sb = StringBuilder(getBracketTag(tag))
                for (msg in messages) {
                    sb.append(msg)
                }
                Log.d(TAG, sb.toString())
            }
        }

        fun w(tag: String, vararg messages: Any) {
            if (debugFlag) {
                val sb = StringBuilder(getBracketTag(tag))
                for (msg in messages) {
                    sb.append(msg)
                }
                Log.w(TAG, sb.toString())
            }
        }

        fun w(tag: String, message: String, throwable: Throwable) {
            if (debugFlag) {
                Log.w(TAG, getBracketTag(tag) + message, throwable)
            }
        }

        fun critical(tag: String, vararg messages: Any) {
            if (debugFlag) {
                val sb = StringBuilder(getBracketTag(tag))
                for (msg in messages) {
                    sb.append(msg)
                }
                Log.d(TAG, sb.toString())
            }
        }

        private fun getBracketTag(tag: String): String = "[$tag]"
    }
}