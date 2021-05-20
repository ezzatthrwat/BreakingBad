package me.ezzattharwat.breakingbad

import android.content.Context
import android.net.*
import javax.inject.Inject

class NetworkConnection @Inject constructor(context: Context) {

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var isNetworkAvailable = false


    init {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback())

    }

    private fun networkCallback() = object: ConnectivityManager.NetworkCallback() {

        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }

        override fun onUnavailable() {
            super.onUnavailable()
            isNetworkAvailable = false
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }

    }


    fun checkNetwork(): Boolean { return isNetworkAvailable }
}