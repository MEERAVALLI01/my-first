package com.vishnu.core.Services;

import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.resource.*;

import java.util.Iterator;

@Component(service = PageReviewService.class)
public class PageReviewServiceImpl implements PageReviewService {

    @Override
    public int reviewPages(ResourceResolver resolver, String path) {

        int count = 0;

        Resource root = resolver.getResource(path);

        if (root != null) {
            Iterator<Resource> pages = root.listChildren();

            while (pages.hasNext()) {
                Resource page = pages.next();

                Resource content = page.getChild("jcr:content");

                if (content != null) {
                    ModifiableValueMap map = content.adaptTo(ModifiableValueMap.class);

                    if (map != null && !map.containsKey("reviewed")) {

                        map.put("reviewed", true);
                        map.put("reviewedBy", "servlet");

                        count++;
                    }
                }
            }

            try {
                resolver.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}