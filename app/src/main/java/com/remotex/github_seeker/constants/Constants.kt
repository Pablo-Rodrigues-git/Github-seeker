package com.remotex.github_seeker.constants

object Constants {
    const val BASE_URL = "https://api.github.com/"
    const val CACHE_CONTROL = "Cache-Control"
    const val CACHE_VALUE_WITH_NETWORK = "public, max-age=" + 5
    const val CACHE_VALUE_WITHOUT_NETWORK =
        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
}