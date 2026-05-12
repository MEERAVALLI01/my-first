package com.vishnu.core.Services;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.io.*;
import java.net.*;

@Component(service = ApiService.class)
@Designate(ocd = ApiConfig.class)
public class ApiServiceImpl implements ApiService {

    private String apiUrl;

    @Activate
    @Modified
    protected void activate(ApiConfig config) {
        this.apiUrl = config.apiUrl();
    }

    @Override
    public String getApiResponse() {

        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
} 