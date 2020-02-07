package tech.iscanner.iscanner

import androidx.core.view.isVisible
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScannableCameraTest {
    private lateinit var camera: ScannableCamera

    @Before
    fun setUp() {
        camera = ScannableCamera(InstrumentationRegistry.getInstrumentation().context)
    }

    @Test
    fun test() {
        assertFalse(camera.isVisible)
    }
}