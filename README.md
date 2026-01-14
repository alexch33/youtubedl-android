# youtubedl-android (Lightweight Fork)

This is a minimal fork of the original
[youtubedl-android](https://github.com/yausername/youtubedl-android) library.

All bundled native binaries (**FFmpeg** and **Aria2c**) have been **removed**.
The library relies entirely on **external binaries available via `PATH`**, making it suitable for **F-Droid reproducible builds**.

## Changes

* No bundled FFmpeg
* No bundled Aria2c
* External binaries are resolved via `PATH`

## Installation

### 1. Add dependencies

In your app build.gradle:

```
dependencies {
    implementation("io.github.alexch33:youtubedl-android:0.18.1-alexch33-2")
}
```

Provide FFmpeg(optionally) and Aria2c(optionally) via separate, reproducible packages.

```
implementation("com.antonkarpenko:ffmpeg-kit-full-gpl:2.1.0")
```

This is required so external binaries are accessible at runtime.

## Usage

Initialize once:

```
YoutubeDL.getInstance().init(context)
```

All other APIs (`getInfo`, `execute`, etc.) work the same as upstream.
