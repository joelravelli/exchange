package com.joel.exchange.service

import java.net.HttpURLConnection

interface HttpInterface {
    var keystorePath: String
    var keystorePassword: String
    var userId: String
    var password: String
    var visaUrlServer: String
}
