package es.jco.ghiblidemo.data.server

// Constants to server

object ServerConstants {
    const val URL_BASE = "https://ghibliapi.herokuapp.com"
    const val ENDPOINT_FILMS = "/films"
    const val ENDPOINT_LOCATIONS = "/locations"
    const val ENDPOINT_PEOPLE = "/people"
    const val ENDPOINT_SPECIES = "/species"
    const val ENDPOINT_VEHICLES = "/vehicles"

    const val HEADER_ACCEPT_TYPE = "Accept: application/json"
    const val HEADER_CONTENT_TYPE = "Content-Type: application/json"

    const val APPLICATION_TYPE = "application/json"

    const val HTTP_CONNECT_TIMEOUT = 30L
    const val HTTP_READ_TIMEOUT = 1L
    const val HTTP_WRITE_TIMEOUT = 1L
}