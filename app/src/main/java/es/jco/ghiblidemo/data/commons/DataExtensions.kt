package es.jco.ghiblidemo.data.commons

fun String.searchId() = this.split('/').last()