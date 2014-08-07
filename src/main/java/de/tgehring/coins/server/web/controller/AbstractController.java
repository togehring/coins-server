package de.tgehring.coins.server.web.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class AbstractController {
	protected void setLocationHeader(HttpServletResponse response, String template, Object... ids) {
        URI uri = ServletUriComponentsBuilder.fromUriString(template).build().expand(ids).toUri();
        response.setHeader("Location", uri.getPath());
    }
}
