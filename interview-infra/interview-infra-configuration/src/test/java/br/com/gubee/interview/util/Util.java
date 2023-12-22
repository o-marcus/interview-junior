package br.com.gubee.interview.util;

import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

public class Util {
    public static UUID toUUID(ResultActions actions) {
        var uri = actions.andReturn().getResponse().getHeader("Location");
        if (uri == null) throw new RuntimeException();
        return UUID.fromString(uri.substring(uri.lastIndexOf("/") + 1));
    }

}
