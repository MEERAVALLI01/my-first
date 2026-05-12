package com.vishnu.core.Services;

import org.apache.sling.api.resource.ResourceResolver;

public interface PageReviewService {

    int reviewPages(ResourceResolver resolver, String path);

}
