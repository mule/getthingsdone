package dev.muuli.gtd.app.library.android

import android.widget.Toast
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.muuli.gtd.library.android.ToastUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ToastUtilTest {

    @Test
    fun showCorrectToast() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val toast = ToastUtil.showToast(context, "test message")

        assertEquals(Toast.LENGTH_SHORT, toast.duration)
    }
}
