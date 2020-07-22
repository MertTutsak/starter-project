package com.merttutsak.starter.data.remote.service

class Session {
    companion object {

        private var currentSession: Session? = Session()

        val current: Session
            get() {
                if (currentSession == null) {
                    currentSession = Session()
                }
                return currentSession as Session
            }
    }
}