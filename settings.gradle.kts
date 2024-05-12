pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        //maven { url = uri("https://jitpack.io") }
        /*jcenter()
        {
            content {
                includeModule("com.theartofdev.edmodo", "android-image-cropper")
            }
        }*/
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HappyTimesKindergarten"
include(":app")
//include("Android-Image-Cropper-master:cropper")