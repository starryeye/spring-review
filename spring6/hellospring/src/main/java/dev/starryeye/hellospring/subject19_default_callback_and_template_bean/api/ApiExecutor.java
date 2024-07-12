package dev.starryeye.hellospring.subject19_default_callback_and_template_bean.api;

import java.io.IOException;
import java.net.URI;

public interface ApiExecutor {
    String execute(URI uri) throws IOException;
}
