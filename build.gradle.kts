plugins {
    id("com.yausername.youtubedl_android") apply false
}

val versionMajor = 0
val versionMinor = 18
val versionPatch = 1
val versionBuild = 0
val versionCode = versionMajor * 100000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
val versionName = "$versionMajor.$versionMinor.$versionPatch"

extra.apply {
    set("versionCode", versionCode)
    set("versionName", versionName)
    set("appCompatVer", "1.4.2")
    set("junitVer", "4.13.2")
    set("androidJunitVer", "1.1.3")
    set("espressoVer", "3.4.0")
    set("jacksonVer", "2.11.1")
    set("commonsIoVer", "2.5") // supports java 1.6
    set("commonsCompressVer", "1.12") // supports java 1.6
    set("coreKtxVer", "1.8.0")
}

allprojects {
    group = "io.github.alexch33"
    version = versionName
}
