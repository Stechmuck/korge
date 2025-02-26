package com.soywiz.korio.file.std

import com.soywiz.korio.*
import com.soywiz.korio.file.*
import com.soywiz.korio.lang.Environment
import com.soywiz.korio.lang.tempPath

actual val standardVfs: StandardVfs = object : StandardVfs() {
    override val resourcesVfs: VfsFile by lazy { applicationVfs.jail() }
    override val rootLocalVfs: VfsFile by lazy { localVfs(".") }
}

actual val applicationVfs: VfsFile by lazy { localVfs(".") }

private var applicationDataVfsOrNull: VfsFile? = null
actual val applicationDataVfs: VfsFile get() {
    if (applicationDataVfsOrNull == null) applicationDataVfsOrNull = jsRuntime.localStorage().root
    return applicationDataVfsOrNull!!
}
private var cacheVfsOrNull: VfsFile? = null
actual val cacheVfs: VfsFile get() {
    if (cacheVfsOrNull == null) cacheVfsOrNull = MemoryVfs()
    return cacheVfsOrNull!!
}
actual val externalStorageVfs: VfsFile by lazy { localVfs(".") }
actual val userHomeVfs: VfsFile by lazy { localVfs(".") }
private var tempVfsOrNull: VfsFile? = null
actual val tempVfs: VfsFile get() {
    if (tempVfsOrNull == null) tempVfsOrNull = jsRuntime.tempVfs()
    return tempVfsOrNull!!
}
actual fun localVfs(path: String, async: Boolean): VfsFile = jsRuntime.openVfs(path)
