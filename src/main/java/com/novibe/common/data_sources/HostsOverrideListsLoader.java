package com.novibe.common.data_sources;

import com.novibe.common.util.DataParser;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class HostsOverrideListsLoader extends ListLoader<HostsOverrideListsLoader.BypassRoute> {

    public record BypassRoute(String ip, String website) {
    }

    @Override
    protected String listType() {
        return "Override";
    }

    @Override
    protected Predicate<String> filterRelatedLines() {
        return line -> !HostsBlockListsLoader.isBlock(line);
    }

    @Override
    protected BypassRoute toObject(String line) {
        int delimiter = line.indexOf(" ");
        String ip = line.substring(0, delimiter++);
        String website = DataParser.removeWWW(line.substring(delimiter).strip());
        return new BypassRoute(ip, website);
    }

}
