package com.soywiz.korim.format

import com.soywiz.klogger.*
import com.soywiz.korio.async.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import kotlin.test.*

class ImageFormatsNativeTest {
    val formats = ImageFormats(PNG, ICO, SVG)

    @Test
    fun png8() = suspendTest {
        val bitmap = resourcesVfs["kotlin8.png"].readNativeImage()
        assertEquals("AwtNativeImage(190, 190)", bitmap.toString())
        //awtShowImage(bitmap); Thread.sleep(10000L)
    }

    @Test
    fun png24() = suspendTest {
        val bitmap = resourcesVfs["kotlin24.png"].readBitmap(formats = formats)
        assertEquals("AwtNativeImage(190, 190)", bitmap.toString())
        //awtShowImage(bitmap); Thread.sleep(10000L)
    }


    @Test
    fun png32() = suspendTest {
        val bitmap = resourcesVfs["kotlin32.png"].readBitmap(formats = formats)
        assertEquals("AwtNativeImage(190, 190)", bitmap.toString())
        //awtShowImage(bitmap); Thread.sleep(10000L)
    }

    @Test
    fun svg() = suspendTest {
        val logs = Console.capture {
            val bi = resourcesVfs["logo.svg"].readBitmapInfo(formats)!!
            assertEquals(Size(60, 60), bi.size)
            val bitmap = resourcesVfs["logo.svg"].readBitmap(formats = formats)
        }
        assertEquals(
            """
                Couldn't read native image (fallback to non-native decoders): java.lang.IllegalStateException: Can't read image using AWT
            """.trimIndent(),
            logs.joinToString("\n")
        )
        //showImageAndWait(bitmap)
        //File("c:/temp/logosvg.png").toVfs().writeBitmap(bitmap.toBMP32())
    }
}
