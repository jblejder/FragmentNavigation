[![](https://jitpack.io/v/jblejder/FragmentNavigation.svg)](https://jitpack.io/#jblejder/FragmentNavigation)
# FragmentNavigation - BETA

Library that provides nice abstraction over fragment managed navigation. Gives possibility to easly create one Activity - Many Fragments application. Uses nested fragments to separate different application parts.

## Usage in shorten 

Library provides 2 main classes `BranchFragment` that is kind of root fragment which pourpose is to store other nested fragments and `BranchFragment` that should contain your logic and have some build in function to help using this library. 

## Example

Example is in this repository just download and check how it works :)

## Downloading

Library is available via JitPack.

Simply add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Then add dependency:
```
dependencies {
    implements 'com.github.jblejder:FragmentNavigation:VERSION'
}
```
