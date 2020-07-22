package com.merttutsak.starter.data.remote.service.error

enum class ErrorStatus {

    SUCCESS {
        override fun code() = 0
    },
    UNKNOWN_ERROR {
        override fun code() = 1002
    },
    SERVER_ERROR {
        override fun code() = 1003
    },
    NETWORK_ERROR {
        override fun code() = 1004
    },

    /**
     * 100: Invalid API Key
     * The API key passed was not valid or has expired.
     */
    API_ERROR {
        override fun code() = 100
    },

    /**
     * 105: Service currently unavailable
     * The requested service is temporarily unavailable.
     */
    SERVICE_UNAVAIBLE {
        override fun code() = 105
    },

    /**
     * 106: Write operation failed
     * The requested operation failed due to a temporary issue.
     */
    WRITE_OPERATION {
        override fun code() = 106
    },

    /**
     * 111: Format "xxx" not found
     * The requested response format was not found.
     */
    FORMAT_ERROR {
        override fun code() = 111
    },

    /**
     * 112: Method "xxx" not found
     * The requested method was not found.
     */
    METHOD_ERROR {
        override fun code() = 112
    },

    /**
     * 114: Invalid SOAP envelope
     * The SOAP envelope send in the request could not be parsed.
     */
    INVALID_SOAP_ERROR {
        override fun code() = 114
    },

    /**
     * 115: Invalid XML-RPC Method Call
     * The XML-RPC request document could not be parsed.
     */
    INVALID_XML_RPC_ERROR {
        override fun code() = 115
    },

    /**
     * 116: Bad URL found
     * One or more arguments contained a URL that has been used for abuse on Flickr.
     */
    BAD_URL_ERROR {
        override fun code() = 116
    };

    companion object {
        fun fromCode(code: Int): ErrorStatus =
            values().firstOrNull { code == it.code() } ?: UNKNOWN_ERROR
    }

    abstract fun code(): Int

}