package com.novibe.dns.next_dns.http;

import com.novibe.common.HttpRequestSender;
import com.novibe.common.exception.ProcessException;

public abstract class AbstractNextDnsHttpClient extends HttpRequestSender {

    protected abstract String path();

    @Override
    protected String apiUrl() {
        return "https://api.nextdns.io/profiles/%s".formatted(dnsProfile.clientId());
    }

    @Override
    protected String authHeaderName() {
        return "X-Api-Key";
    }

    @Override
    protected String authHeaderValue() {
        return dnsProfile.authSecret();
    }

    @Override
    protected final void react401() {
        throw new ProcessException("Invalid api key");
    }

    @Override
    protected void react403() {
        throw new ProcessException("Invalid api key!");
    }
}
