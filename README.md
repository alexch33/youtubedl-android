# youtubedl-android (Lightweight Fork)

This is a minimal fork of the original
[youtubedl-android](https://github.com/yausername/youtubedl-android) library.

All bundled native binaries (**FFmpeg** and **Aria2c**) have been **removed**.
The library relies entirely on **external binaries available via `PATH`**, making it suitable for **F-Droid reproducible builds**.

## Changes

* No bundled FFmpeg
* No bundled Aria2c
* External binaries are resolved via `PATH`

## Installation (F-Droid / Reproducible)

This library **must be built from source**.

### 1. Add as a source dependency

Include this repository as a Git submodule or copy it into your project:

```
<project-root>/youtubedl-android
```

Then include the module in `settings.gradle` / `settings.gradle.kts`:

```
include(":youtubedl-android")
```

### 2. Add dependencies

In your app module:

```
dependencies {
    implementation(project(":youtubedl-android"))
}
```

Provide FFmpeg and (optionally) Aria2c via separate, reproducible packages.

```
implementation("com.antonkarpenko:ffmpeg-kit-full-gpl:2.1.0")
```

### 3. AndroidManifest

```
<application
    android:extractNativeLibs="true" />
```

This is required so external binaries are accessible at runtime.

## Usage

Initialize once:

```
YoutubeDL.getInstance().init(context)
```

All other APIs (`getInfo`, `execute`, etc.) work the same as upstream.
